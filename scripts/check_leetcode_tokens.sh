#!/bin/bash
# Simple script to check LeetCode tokens using curl
# This replicates the functionality of test_leetcode_sync.py without Python dependencies

# Parameters
CSRF_TOKEN="$1"
SESSION_TOKEN="$2"

if [ -z "$CSRF_TOKEN" ] || [ -z "$SESSION_TOKEN" ]; then
    echo "Usage: $0 <csrf_token> <session_token>"
    echo "Example: $0 'your_csrf_token' 'your_leetcode_session'"
    exit 1
fi

echo "============================================================"
echo "LeetCode Sync Token Test"
echo "============================================================"
echo "Testing if your LeetCode tokens will work with the sync workflow"
echo "============================================================"
echo ""

# Test 1: Authentication Test
echo "Testing LeetCode authentication..."
AUTH_RESPONSE=$(curl -s -X POST "https://leetcode.com/graphql" \
    -H "Content-Type: application/json" \
    -H "X-CSRFToken: $CSRF_TOKEN" \
    -H "Referer: https://leetcode.com/problems/" \
    -H "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36" \
    --cookie "csrftoken=$CSRF_TOKEN; LEETCODE_SESSION=$SESSION_TOKEN" \
    -d '{"query":"query { userStatus { username isSignedIn } }"}')

# Parse authentication response
IS_SIGNED_IN=$(echo "$AUTH_RESPONSE" | grep -o '"isSignedIn":true' || echo "")
USERNAME=$(echo "$AUTH_RESPONSE" | grep -o '"username":"[^"]*"' | cut -d ":" -f2 | tr -d '"')

if [ -z "$IS_SIGNED_IN" ]; then
    echo "❌ Authentication test failed!"
    echo "Response: $AUTH_RESPONSE"
    echo "You are not signed in to LeetCode. Your tokens may be invalid or expired."
    exit 1
else
    echo "✅ Successfully authenticated as: $USERNAME"
    echo ""
fi

# Test 2: Submissions API Test - Using a simpler query that's less likely to break
echo "Testing LeetCode submissions API..."
SUBMISSION_RESPONSE=$(curl -s -X POST "https://leetcode.com/graphql" \
    -H "Content-Type: application/json" \
    -H "X-CSRFToken: $CSRF_TOKEN" \
    -H "Referer: https://leetcode.com/problems/" \
    -H "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36" \
    --cookie "csrftoken=$CSRF_TOKEN; LEETCODE_SESSION=$SESSION_TOKEN" \
    -d '{"query":"query submissionList($offset: Int!, $limit: Int!) { submissionList(offset: $offset, limit: $limit) { lastKey hasNext submissions { id title titleSlug status statusDisplay lang langName runtime timestamp url isPending memory hasNotes } } }","variables":{"offset":0,"limit":20}}')

# Check if we have submissions - improved parsing logic
# First check for errors
ERRORS=$(echo "$SUBMISSION_RESPONSE" | grep -o '"errors":\[[^\]]*\]' || echo "")

# Check for null submissions
SUBMISSIONS_NULL=$(echo "$SUBMISSION_RESPONSE" | grep -o '"submissions":null' || echo "")

# Check if data and submissionList exist
HAS_DATA=$(echo "$SUBMISSION_RESPONSE" | grep -o '"data":{' || echo "")
HAS_SUBMISSION_LIST=$(echo "$SUBMISSION_RESPONSE" | grep -o '"submissionList":{' || echo "")
HAS_SUBMISSIONS=$(echo "$SUBMISSION_RESPONSE" | grep -o '"submissions":\[' || echo "")

if [ -n "$ERRORS" ]; then
    echo "❌ LeetCode API returned errors - the API schema may have changed"
    echo "Response: $SUBMISSION_RESPONSE"
    
    # Use a more basic query as a fallback
    echo ""
    echo "Trying a more basic query as fallback..."
    SUBMISSION_RESPONSE=$(curl -s -X POST "https://leetcode.com/graphql" \
        -H "Content-Type: application/json" \
        -H "X-CSRFToken: $CSRF_TOKEN" \
        -H "Referer: https://leetcode.com/problems/" \
        -H "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36" \
        --cookie "csrftoken=$CSRF_TOKEN; LEETCODE_SESSION=$SESSION_TOKEN" \
        -d '{"query":"query { recentSubmissionList { title titleSlug timestamp status } }"}')
    
    RECENT_DATA=$(echo "$SUBMISSION_RESPONSE" | grep -o '"recentSubmissionList":\[[^\]]*\]' || echo "")
    if [ -z "$RECENT_DATA" ]; then
        echo "❌ Fallback query also failed"
        echo "This suggests your tokens are valid but the LeetCode API has changed significantly"
        echo "The GitHub workflow may encounter errors due to these API changes"
        echo ""
        echo "Recommendation: Check if there's an updated version of joshcai/leetcode-sync available"
    else
        echo "✅ Basic API query successful - tokens are valid"
        echo ""
        echo "Your tokens are valid, but the LeetCode API schema has changed"
        echo "The GitHub workflow may encounter errors due to these API changes"
        echo ""
        echo "Recommendation: Check if there's an updated version of joshcai/leetcode-sync available"
    fi
elif [ -n "$SUBMISSIONS_NULL" ]; then
    echo "❌ Submissions is null - likely expired session token"
    echo "This would cause 'submissions is not iterable' error in the GitHub action"
    echo "Please refresh your LeetCode CSRF and session tokens"
    exit 1
elif [ -z "$HAS_DATA" ] || [ -z "$HAS_SUBMISSION_LIST" ] || [ -z "$HAS_SUBMISSIONS" ]; then
    echo "❌ Failed to get submission list data from LeetCode API"
    echo "Response structure doesn't match expected format"
    echo "Response: $SUBMISSION_RESPONSE"
    exit 1
else
    echo "✅ Successfully retrieved submission data"
    
    # Extract submission data using a better approach - extract entire array first
    # We'll look for titles and statuses
    SUBMISSION_TITLES=$(echo "$SUBMISSION_RESPONSE" | grep -o '"title":"[^"]*"' | cut -d ':' -f2 | tr -d '"')
    SUBMISSION_STATUSES=$(echo "$SUBMISSION_RESPONSE" | grep -o '"statusDisplay":"[^"]*"' | cut -d ':' -f2 | tr -d '"')
    
    # Count titles to get submission count
    SUBMISSION_COUNT=$(echo "$SUBMISSION_TITLES" | wc -l | tr -d ' ')
    
    # Count accepted submissions
    ACCEPTED_COUNT=$(echo "$SUBMISSION_STATUSES" | grep -c "Accepted" || echo "0")
    
    if [ "$SUBMISSION_COUNT" -eq 0 ]; then
        echo "⚠️ No submissions found in your LeetCode account"
        echo "The sync action will run but won't find any solutions to sync"
    else
        if [ "$ACCEPTED_COUNT" -eq 0 ]; then
            echo "⚠️ Found $SUBMISSION_COUNT submissions, but none are 'Accepted'"
            echo "The GitHub Action only syncs accepted solutions"
        else
            echo "✅ Found $ACCEPTED_COUNT accepted submissions out of $SUBMISSION_COUNT total"
            
            # Display a few submission titles and their status
            echo ""
            echo "Recent submissions:"
            
            # Show up to 3 submissions
            COUNT=0
            while IFS= read -r title <&3 && IFS= read -r status <&4; do
                if [ "$COUNT" -lt 3 ]; then
                    echo "  $((COUNT+1)). $title ($status)"
                    COUNT=$((COUNT+1))
                else
                    break
                fi
            done 3< <(echo "$SUBMISSION_TITLES") 4< <(echo "$SUBMISSION_STATUSES")
        fi
    fi
fi

echo ""
echo "============================================================"
echo "LeetCode Token Test Results"
echo "============================================================"
echo "1. Authentication: ✅ Successful (as user: $USERNAME)"

if [ -n "$ERRORS" ] || [ -n "$SUBMISSIONS_NULL" ] || [ -z "$HAS_DATA" ] || [ -z "$HAS_SUBMISSION_LIST" ] || [ -z "$HAS_SUBMISSIONS" ]; then
    echo "2. API Access: ❌ Issues detected"
    echo "3. Accepted Solutions: ❌ Could not verify"
else
    echo "2. API Access: ✅ Successful"
    if [ "$ACCEPTED_COUNT" -gt 0 ]; then
        echo "3. Accepted Solutions: ✅ Found $ACCEPTED_COUNT solutions"
    else
        echo "3. Accepted Solutions: ❌ None found"
    fi
fi

echo ""
echo "GitHub Workflow Recommendations:"

if [ -z "$ERRORS" ] && [ -z "$SUBMISSIONS_NULL" ] && [ -n "$HAS_DATA" ] && [ -n "$HAS_SUBMISSION_LIST" ] && [ -n "$HAS_SUBMISSIONS" ] && [ "$ACCEPTED_COUNT" -gt 0 ]; then
    echo "✅ Your tokens are valid and should work with the GitHub workflow"
    echo ""
    echo "To fix the 'HttpError: Reference cannot be updated' issue:"
    echo "1. Make sure GitHub Actions has write permissions to your repository:"
    echo "   Settings > Actions > General > Workflow permissions > Read and write permissions"
    echo ""
    echo "2. Add this to your GitHub workflow file (before the leetcode-sync step):"
    echo "   permissions:"
    echo "     contents: write"
else
    echo "❌ Your GitHub workflow will likely fail with these tokens"
    if [ -n "$ERRORS" ]; then
        echo "   • The LeetCode API schema appears to have changed"
        echo "   • Check for updates to the joshcai/leetcode-sync action"
    fi
    if [ -n "$SUBMISSIONS_NULL" ]; then
        echo "   • Your session token appears to be expired"
        echo "   • Please refresh your LeetCode tokens"
    fi
    if [ "$ACCEPTED_COUNT" -eq 0 ]; then
        echo "   • No accepted solutions were found to sync"
    fi
fi

echo ""
echo "To use the GitHub workflow:"
echo "1. Make sure GitHub Actions has write permissions to your repository:"
echo "   Settings > Actions > General > Workflow permissions > Read and write permissions"
echo "2. Add your tokens as repository secrets:"
echo "   Settings > Secrets and variables > Actions > New repository secret"
echo "   - Name: LEETCODE_CSRF_TOKEN, Value: [your CSRF token]" 
echo "   - Name: LEETCODE_SESSION, Value: [your session token]"
echo "3. Run the workflow manually from the Actions tab"
echo "   or wait for it to run on its scheduled time"

exit 0
