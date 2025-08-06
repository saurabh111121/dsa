#!/usr/bin/env python3
"""
Test script for LeetCode sync configuration

This script tests whether your LeetCode authentication tokens are working properly
and if the GitHub Action workflow will be able to sync your solutions.
It performs the same API calls that the joshcai/leetcode-sync action uses
to validate your setup before running the actual workflow.
"""

import argparse
import json
import requests
import sys

def test_leetcode_auth(csrf_token, session_token):
    """Test basic LeetCode authentication"""
    print("Testing LeetCode authentication...")
    
    headers = {
        'Content-Type': 'application/json',
        'X-CSRFToken': csrf_token,
        'Referer': 'https://leetcode.com/problems/',
        'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'
    }
    
    cookies = {
        'csrftoken': csrf_token,
        'LEETCODE_SESSION': session_token,
    }
    
    try:
        # Test user status query
        user_query = """
        query {
            userStatus {
                username
                isSignedIn
            }
        }
        """
        
        response = requests.post(
            "https://leetcode.com/graphql",
            headers=headers,
            cookies=cookies,
            json={'query': user_query},
            timeout=30
        )
        
        if response.status_code != 200:
            print(f"❌ Authentication test failed with status code: {response.status_code}")
            print(f"Response: {response.text}")
            return False
        
        data = response.json()
        user_status = data.get('data', {}).get('userStatus', {})
        is_signed_in = user_status.get('isSignedIn', False)
        username = user_status.get('username', 'Unknown')
        
        if not is_signed_in:
            print("❌ Not signed in to LeetCode. Your tokens may be invalid or expired.")
            return False
        
        print(f"✅ Successfully authenticated as: {username}")
        return True
    
    except Exception as e:
        print(f"❌ Error during authentication: {str(e)}")
        return False

def test_submissions_api(csrf_token, session_token):
    """Test the LeetCode submissions API"""
    print("\nTesting LeetCode submissions API...")
    
    headers = {
        'Content-Type': 'application/json',
        'X-CSRFToken': csrf_token,
        'Referer': 'https://leetcode.com/problems/',
        'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'
    }
    
    cookies = {
        'csrftoken': csrf_token,
        'LEETCODE_SESSION': session_token,
    }
    
    try:
        # This is the exact API call that joshcai/leetcode-sync uses
        submissions_query = """
        query submissionList($offset: Int!, $limit: Int!, $lastKey: String, $questionSlug: String, $lang: String, $status: String) {
          submissionList(offset: $offset, limit: $limit, lastKey: $lastKey, questionSlug: $questionSlug, lang: $lang, status: $status) {
            lastKey
            hasNext
            submissions {
              id
              title
              titleSlug
              status
              statusDisplay
              lang
              langName
              runtime
              timestamp
              url
              isPending
              memory
              hasNotes
              notes
              flagType
              code
            }
          }
        }
        """
        
        # First page of submissions
        variables = {
            'offset': 0,
            'limit': 20,  # Typically we fetch in batches of 20
            'status': 'Accepted'  # We're only interested in accepted solutions
        }
        
        response = requests.post(
            "https://leetcode.com/graphql",
            headers=headers,
            cookies=cookies,
            json={'query': submissions_query, 'variables': variables},
            timeout=30
        )
        
        if response.status_code != 200:
            print(f"❌ Submissions API test failed with status code: {response.status_code}")
            print(f"Response: {response.text}")
            return False
        
        data = response.json()
        
        # Check if submissionList exists
        submission_list = data.get('data', {}).get('submissionList', {})
        if not submission_list:
            print("❌ Failed to get submission list data from LeetCode API")
            print(f"Response: {json.dumps(data, indent=2)}")
            return False
        
        # Check if submissions array exists and is iterable
        submissions = submission_list.get('submissions')
        if submissions is None:
            print("❌ Submissions is null - likely expired session token")
            print("This would cause 'submissions is not iterable' error in the GitHub action")
            print("Please refresh your LeetCode CSRF and session tokens")
            return False
        
        if not isinstance(submissions, list):
            print(f"❌ Submissions is not a list: {type(submissions)}")
            print("This would cause 'submissions is not iterable' error in the GitHub action")
            return False
        
        # Check if we have any submissions
        if len(submissions) == 0:
            print("⚠️ No accepted submissions found in your LeetCode account")
            print("The sync action will run but won't find any solutions to sync")
        else:
            print(f"✅ Found {len(submissions)} accepted submissions")
            print("\nFirst few submissions:")
            for i, submission in enumerate(submissions[:3]):
                print(f"  {i+1}. {submission.get('title', 'Unknown')} ({submission.get('lang', 'Unknown')})")
        
        return True
    
    except Exception as e:
        print(f"❌ Error accessing submissions API: {str(e)}")
        return False

def main():
    parser = argparse.ArgumentParser(description='Test LeetCode sync workflow configuration')
    parser.add_argument('--csrf-token', required=True, help='LeetCode CSRF token')
    parser.add_argument('--session-token', required=True, help='LeetCode session token')
    
    args = parser.parse_args()
    
    print("=" * 60)
    print("LeetCode Sync Workflow Test")
    print("=" * 60)
    print("This script tests if your LeetCode tokens will work with the sync workflow")
    print("=" * 60 + "\n")
    
    # Test authentication
    auth_success = test_leetcode_auth(args.csrf_token, args.session_token)
    if not auth_success:
        print("\nAuthentication failed! The GitHub workflow will fail.")
        sys.exit(1)
    
    # Test submissions API
    api_success = test_submissions_api(args.csrf_token, args.session_token)
    if not api_success:
        print("\nSubmissions API test failed! The GitHub workflow will fail.")
        sys.exit(1)
    
    print("\n" + "=" * 60)
    print("✅ All tests passed! Your GitHub workflow should sync successfully.")
    print("=" * 60)
    print("\nTo use the GitHub workflow:")
    print("1. Ensure you've added your tokens as repository secrets")
    print("2. Run the workflow manually from the Actions tab")
    print("   or wait for it to run on its scheduled time")

if __name__ == '__main__':
    main()
