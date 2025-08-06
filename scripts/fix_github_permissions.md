# Fixing GitHub "Reference cannot be updated" Error

The error `HttpError: Reference cannot be updated` is a GitHub permissions issue that can occur even when your workflow has `permissions: contents: write` configured correctly.

## Solution Options

Try these solutions in order:

### 1. Update Branch Protection Rules

If you have branch protection rules enabled for your repository, they might be preventing the GitHub Action from pushing commits.

1. Go to your GitHub repository
2. Click on "Settings" > "Branches"
3. Under "Branch protection rules", find your main branch protection rule
4. Make sure "Allow GitHub Actions to create and approve pull requests" is enabled
5. Consider temporarily disabling "Require pull request reviews before merging" 
6. Consider adding an exception for the GitHub Actions bot

### 2. Use a Personal Access Token

Replace the default `github.token` with a Personal Access Token that has full repo scope:

1. Go to your GitHub account settings
2. Click "Developer settings" > "Personal access tokens" > "Generate new token"
3. Give it a name like "LeetCode Sync"
4. Select the "repo" scope
5. Click "Generate token" and copy the token
6. Go to your repository "Settings" > "Secrets and variables" > "Actions"
7. Add a new secret named `PERSONAL_ACCESS_TOKEN` with your token value
8. Update your workflow file:

```yaml
- name: Sync LeetCode Solutions
  uses: joshcai/leetcode-sync@v1.7
  with:
    github-token: ${{ secrets.PERSONAL_ACCESS_TOKEN }}  # Use this instead of github.token
    leetcode-csrf-token: ${{ secrets.LEETCODE_CSRF_TOKEN }}
    leetcode-session: ${{ secrets.LEETCODE_SESSION }}
    destination-folder: src/com/leetcode
    verbose: true
    commit-header: "[LeetCode Sync]"
```

### 3. Use a Dedicated Branch

Try using a dedicated branch for the synced solutions:

1. Create a new branch called `leetcode-solutions`
2. Configure the workflow to use this branch
3. Later, you can merge this branch to main through a pull request

```yaml
- name: Checkout repository
  uses: actions/checkout@v3
  with:
    ref: leetcode-solutions  # Use a dedicated branch
    fetch-depth: 0
```

### 4. Split Commits into Smaller Batches

If the issue is related to the size of the commits, you can modify your workflow to commit fewer solutions at once by adding a custom parameter:

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
    batch-size: 5  # Commit in smaller batches
```

## After Making Changes

After making any of these changes:

1. Go to the GitHub Actions tab in your repository
2. Manually run the LeetCode sync workflow
3. Check the logs for any errors

If the issue persists, try contacting GitHub support or opening an issue in the joshcai/leetcode-sync repository.
