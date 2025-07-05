# LeetCode Sync Configuration

This directory originally contained custom Python scripts for synchronizing LeetCode solutions, but we have now switched to using the `joshcai/leetcode-sync` GitHub Action for more reliable operation.

## Current Approach

The LeetCode sync process now uses:

1. The `joshcai/leetcode-sync` GitHub Action to fetch all your solutions from LeetCode
2. A small Python helper script embedded in the workflow to copy solutions to the daily challenge folder

## How It Works

### Main Sync Process
- The `joshcai/leetcode-sync` action connects to your LeetCode account using CSRF token and session cookie
- It fetches all your accepted solutions and organizes them by problem number
- Solutions are saved to the `src/com/leetcode/solutions` directory

### Daily Challenge Organization
- A Python script copies the most recent solutions to a date-based folder
- This maintains the organization of solutions by both problem number and date

## GitHub Actions Configuration

The sync process runs automatically every day at midnight UTC (5:30 AM IST) via GitHub Actions. The workflow configuration is defined in `.github/workflows/leetcode-sync.yml`.

## Setting Up GitHub Secrets

For this automation to work, you need to add the following secrets to your GitHub repository:

1. `LEETCODE_CSRF_TOKEN`: Your LeetCode CSRF token
2. `LEETCODE_SESSION`: Your LeetCode session cookie

To obtain these values:
1. Log in to LeetCode in your browser
2. Open browser developer tools (F12 or right-click > Inspect)
3. Go to the Application tab (or Storage tab in Firefox)
4. Look under Cookies > leetcode.com
5. Find the `csrftoken` and `LEETCODE_SESSION` values

To add these as secrets:
1. Go to your GitHub repository
2. Click on "Settings" > "Secrets and variables" > "Actions"
3. Click "New repository secret"
4. Add each secret with its corresponding value

Additionally, make sure GitHub Actions has write permissions:
1. Go to Settings > Actions > General
2. Under "Workflow permissions", select "Read and write permissions"

## Manual Execution

You can manually trigger the sync process by:
1. Going to the "Actions" tab in your GitHub repository
2. Selecting the "Sync LeetCode" workflow
3. Clicking "Run workflow"

## Directory Structure

The system organizes LeetCode solutions as follows:

```
src/
├── com/
    ├── leetcode/
        ├── solutions/                 # All solutions by problem number
        │   ├── 1-two-sum/
        │   │   └── Solution.java
        │   ├── 2-add-two-numbers/
        │   │   └── Solution.java
        │   └── ...
        │
        └── daily_challenge/           # Daily challenges by date
            ├── 2025-05-07/
            │   ├── Solution.java
            │   └── README.md
            └── ...
```
