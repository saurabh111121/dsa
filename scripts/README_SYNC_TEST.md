# LeetCode Sync Troubleshooting

This document explains how to troubleshoot the LeetCode sync process when you encounter errors.

## Common Error 1: "submissions is not iterable"

If you're seeing the error `TypeError: response.data.data.submissionList.submissions is not iterable` in your GitHub Actions logs, this typically means that your LeetCode authentication tokens have expired. LeetCode session tokens typically expire after some time (usually a few weeks to months), requiring you to refresh them.

## Common Error 2: "HttpError: Reference cannot be updated"

If you're seeing the error `HttpError: Reference cannot be updated` in your GitHub Actions logs, this is a GitHub permissions issue. This happens when the GitHub Action tries to commit changes to your repository but doesn't have sufficient permissions.

### How to Fix "Reference cannot be updated" Error

1. **Enable Write Permissions for GitHub Actions**:
   - Go to your repository on GitHub
   - Click on "Settings" > "Actions" > "General"
   - Under "Workflow permissions", select "Read and write permissions"
   - Click "Save"

2. **Verify Repository Permissions**:
   - Make sure your workflow has the proper permissions by adding this to your workflow file:
   ```yaml
   permissions:
     contents: write
   ```

3. **Check Branch Protection Rules**:
   - If you have branch protection rules, make sure they allow GitHub Actions to push to the branch
   - Go to Settings > Branches > Branch protection rules
   - Consider adjusting the rules to allow GitHub Actions to push to the branch

4. **Use Personal Access Token** (if above steps don't work):
   - Create a Personal Access Token with `repo` scope
   - Add it as a repository secret (e.g., `PERSONAL_ACCESS_TOKEN`)
   - Use it in your workflow instead of `${{ github.token }}`:
   ```yaml
   github-token: ${{ secrets.PERSONAL_ACCESS_TOKEN }}
   ```

## How to Test Your LeetCode Sync Configuration

We've provided a special test script that mimics the GitHub Action behavior to help you diagnose issues before running the actual workflow:

```bash
# Navigate to your project directory
cd your_project_directory

# Run the test script
python scripts/test_leetcode_sync.py --csrf-token "your_csrf_token" --session-token "your_leetcode_session"
```

### What the Test Script Does

The test script performs these checks:

1. **Authentication Test**: Verifies if your LeetCode CSRF token and session cookie are valid
2. **Submissions API Test**: Checks if the LeetCode API returns your submissions correctly
3. **Validates Response Format**: Ensures the API response has the expected structure that the GitHub Action requires

### Interpreting Test Results

- ✅ **All Tests Pass**: Your tokens are valid and the workflow should sync successfully
- ❌ **Authentication Failure**: Your tokens are invalid or expired
- ❌ **Submissions API Failure**: There's an issue with the LeetCode API or your tokens
- ⚠️ **No Submissions Warning**: Your account has no accepted solutions to sync

## How to Update Your LeetCode Tokens

If the test script shows your tokens are invalid or expired, follow these steps to refresh them:

1. **Log in to LeetCode** in your browser
2. **Open browser developer tools**:
   - Chrome/Edge: Press F12 or right-click > Inspect
   - Firefox: Press F12 or right-click > Inspect Element
   - Safari: Enable developer tools in preferences, then right-click > Inspect Element
3. **Navigate to Storage/Application tab**:
   - Chrome/Edge: Application tab > Cookies > https://leetcode.com
   - Firefox: Storage tab > Cookies > leetcode.com
   - Safari: Storage tab > Cookies
4. **Copy the new token values**:
   - `csrftoken`: This is your CSRF token
   - `LEETCODE_SESSION`: This is your session token
5. **Update GitHub Secrets**:
   - Go to your GitHub repository
   - Click Settings > Secrets and variables > Actions
   - Update the `LEETCODE_CSRF_TOKEN` and `LEETCODE_SESSION` secrets

## Workflow Configuration

Make sure your GitHub Actions workflow is correctly configured:

1. Ensure you're using the correct parameter names:
   - Valid inputs are: `github-token`, `leetcode-csrf-token`, `leetcode-session`, `filter-duplicate-secs`, `destination-folder`, `verbose`, `commit-header`
   - Do NOT use `overwrite` parameter as it's not supported by the action

2. Check that your workflow YAML file has the correct structure:
   ```yaml
   - name: Sync LeetCode Solutions
     uses: joshcai/leetcode-sync@v1.7
     with:
       github-token: ${{ github.token }}
       leetcode-csrf-token: ${{ secrets.LEETCODE_CSRF_TOKEN }}
       leetcode-session: ${{ secrets.LEETCODE_SESSION }}
       destination-folder: src/com/leetcode
       verbose: true
       commit-header: "[LeetCode Sync]"
   ```

## After Fixing the Issues

Once you've updated your tokens and configuration:

1. Run the test script again to verify everything works
2. Go to GitHub Actions tab in your repository
3. Manually trigger the workflow to see if it runs successfully

If you continue to have issues, check the [joshcai/leetcode-sync GitHub repository](https://github.com/joshcai/leetcode-sync) for known issues or updates.
