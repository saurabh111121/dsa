# LeetCode Sync Configuration

This directory contains the script that enhances LeetCode solutions by adding detailed problem information, official solutions, and links to similar problems.

## Current Approach

The LeetCode sync process uses:

1. A GitHub Action workflow to fetch all your solutions from LeetCode
2. The `leetcode_sync.py` script to enhance solutions with detailed problem information

## How It Works

### Main Sync Process
- The workflow connects to your LeetCode account using CSRF token and session cookie
- It fetches all your accepted solutions and organizes them by problem number
- Solutions are saved directly to the `src/com/leetcode` directory

### Solution Enhancement
<<<<<<< HEAD
- After fetching solutions, a Python script enhances each solution with detailed metadata:
  - Full problem description from LeetCode
  - Difficulty level (Easy, Medium, Hard)
  - Topic tags related to the problem
=======
- After fetching solutions, a Python script enhances each solution with comprehensive metadata:
  - Full problem description from LeetCode
  - Difficulty level (Easy, Medium, Hard) and problem category
  - Topic tags related to the problem
  - Problem statistics (acceptance rate, submission counts)
  - Hints provided by LeetCode (when available)
  - Code templates for starting your solution
  - Example test cases for validation
>>>>>>> 08162721dcdfaed440fb36c8117ac54adec1b141
  - Official solution from LeetCode (for non-premium problems)
  - Links to similar problems with their difficulty levels
- This information is stored in a README.md file in each problem's directory

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
    ├── leetcode/                     # All solutions directly in this directory
        ├── 1-two-sum/
        │   ├── Solution.java
        │   └── README.md             # Contains problem details and similar questions
        ├── 2-add-two-numbers/
        │   ├── Solution.java
        │   └── README.md
        └── ...
```

Each solution README.md contains:
- Full problem description
<<<<<<< HEAD
- Difficulty level
- Topic tags
=======
- Difficulty level and category
- Topic tags
- Problem statistics (acceptance rate, submission counts)
- Hints (when available)
- Code templates in Java
- Example test cases
>>>>>>> 08162721dcdfaed440fb36c8117ac54adec1b141
- Official solution from LeetCode (where available)
- Similar questions with links and difficulty levels
