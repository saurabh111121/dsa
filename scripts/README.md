# LeetCode Sync Scripts

This directory contains scripts for automatically synchronizing LeetCode solutions to this GitHub repository.

## Main Script: `leetcode_sync.py`

The `leetcode_sync.py` script is responsible for:

1. Logging into LeetCode using provided credentials
2. Fetching all accepted solutions for the specified user (111121saurabh)
3. Retrieving detailed problem information for each solution
4. Formatting the code with proper structure and comments
5. Organizing solutions into a logical directory structure
6. Generating comprehensive README files for each solution with problem description, difficulty, tags, similar questions, etc.
7. Tracking daily challenges in a separate directory

## How It Works

The script uses a combination of:

- Selenium WebDriver for LeetCode authentication
- LeetCode's GraphQL API for fetching problem details
- Beautiful Soup for parsing HTML content
- GitHub Actions for scheduling daily runs

## Requirements

The script requires the following Python packages:
- requests
- beautifulsoup4
- selenium
- webdriver-manager

These are automatically installed by the GitHub Actions workflow.

## GitHub Actions Configuration

The sync process runs automatically every day at midnight UTC (5:30 AM IST) via GitHub Actions. The workflow configuration is defined in `.github/workflows/leetcode-sync.yml`.

## Setting Up GitHub Secrets

For this automation to work, you need to add the following secrets to your GitHub repository:

1. `LEETCODE_USERNAME`: Your LeetCode username
2. `LEETCODE_PASSWORD`: Your LeetCode password

To add these secrets:
1. Go to your GitHub repository
2. Click on "Settings" > "Secrets and variables" > "Actions"
3. Click "New repository secret"
4. Add each secret with its corresponding value

## Manual Execution

You can manually trigger the sync process by:
1. Going to the "Actions" tab in your GitHub repository
2. Selecting the "LeetCode Sync" workflow
3. Clicking "Run workflow"

## Directory Structure

The script organizes LeetCode solutions as follows:

```
src/
├── com/
    ├── leetcode/
        ├── solutions/                 # All solutions by problem number
        │   ├── p0001_two_sum/
        │   │   ├── Solution.java
        │   │   └── README.md          
        │   ├── p0002_add_two_numbers/
        │   │   ├── Solution.java
        │   │   └── README.md
        │   └── ...
        │
        └── daily_challenge/           # Daily challenges by date
            ├── 2025-05-07/
            │   ├── Solution.java
            │   └── README.md
            └── ...
```

Each solution includes:
- The complete solution code with proper formatting
- A README.md file with problem description, difficulty, tags, similar questions, etc.
