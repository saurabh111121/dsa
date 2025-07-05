#!/usr/bin/env python3
"""
LeetCode Solution Sync Script
This script enhances the LeetCode solutions that have been synced to the repository
by adding detailed problem information, official solutions, and similar questions.
It works with solutions fetched by the joshcai/leetcode-sync GitHub Action.
"""

import os
import json
import requests
from bs4 import BeautifulSoup, MarkupResemblesLocatorWarning
from pathlib import Path
import argparse
import logging
import warnings
import time

# Suppress specific BeautifulSoup warnings that might cause noise
warnings.filterwarnings("ignore", category=MarkupResemblesLocatorWarning)

# Set up logging
logging.basicConfig(
    level=logging.INFO,
    format='%(asctime)s - %(name)s - %(levelname)s - %(message)s'
)
logger = logging.getLogger('leetcode_sync')

def enhance_solutions(leetcode_dir, csrf_token, session_token):
    """
    Enhance LeetCode solutions with problem details, solutions, and similar questions.
    
    Args:
        leetcode_dir (str): Path to the directory containing LeetCode solutions
        csrf_token (str): LeetCode CSRF token
        session_token (str): LeetCode session token
    """
    leetcode_dir = Path(leetcode_dir)
    logger.info(f"Processing solutions in {leetcode_dir}")
    
    # LeetCode GraphQL endpoint
    graphql_url = "https://leetcode.com/graphql"
    
    # Set headers with the session cookies
    cookies = {
        'csrftoken': csrf_token,
        'LEETCODE_SESSION': session_token,
    }
    
    headers = {
        'Content-Type': 'application/json',
        'X-CSRFToken': csrf_token,
        'Referer': 'https://leetcode.com/problems/',
    }
    
    # GraphQL query for problem details and solution with expanded data
    query = """
    query questionData($titleSlug: String!) {
      question(titleSlug: $titleSlug) {
        questionId
        title
        titleSlug
        content
        difficulty
        stats
        likes
        dislikes
        categoryTitle
        topicTags {
          name
          slug
        }
        codeSnippets {
          lang
          langSlug
          code
        }
        hints
        similarQuestions
        solution {
          content
          isPaidOnly
        }
        exampleTestcases
        metaData
        sampleTestCase
        submitStats {
          acRate
          totalSubmissionCount
          totalAcceptedCount
        }
      }
    }
    """
    
    solutions_processed = 0
    solutions_enhanced = 0
    errors = 0
    
    # List all files and directories to see what's available
    logger.info("Listing contents of leetcode directory:")
    all_files = list(leetcode_dir.glob("*"))
    for file_path in all_files:
        if file_path.is_dir():
            logger.info(f"DIR: {file_path}")
        else:
            logger.info(f"FILE: {file_path}")
    
    # First, check if we have solution files directly in the leetcode directory
    java_files = list(leetcode_dir.glob("*.java"))
    if java_files:
        logger.info(f"Found {len(java_files)} Java files directly in the leetcode directory")
        # If we find Java files directly in the leetcode directory, we'll create folders for them
        for java_file in java_files:
            # Skip the Solutions.java file if it exists
            if java_file.name == "Solutions.java":
                continue
                
            # Extract the problem name from the file name
            file_name = java_file.stem  # Get file name without extension
            try:
                # Try to extract problem number and name
                if "-" in file_name:
                    # Already in the expected format
                    problem_dir = leetcode_dir / file_name
                elif "_" in file_name:
                    # Convert underscore format to dash format
                    parts = file_name.split("_", 1)
                    if len(parts) == 2:
                        problem_dir = leetcode_dir / f"{parts[0]}-{parts[1]}"
                    else:
                        problem_dir = leetcode_dir / file_name
                else:
                    # Just use the file name as is
                    problem_dir = leetcode_dir / file_name
                
                # Create the directory if it doesn't exist
                problem_dir.mkdir(exist_ok=True)
                
                # Copy the Java file into the new directory
                with open(java_file, 'r') as source_file:
                    content = source_file.read()
                
                target_file = problem_dir / "Solution.java"
                with open(target_file, 'w') as dest_file:
                    dest_file.write(content)
                
                logger.info(f"Created directory and moved solution for {file_name}")
                
            except Exception as e:
                logger.error(f"Error processing Java file {file_name}: {str(e)}")
                continue
    
    # Process each problem folder - look for directories with hyphen or any directory containing Java files
    potential_problem_dirs = []
    
    # Add directories with hyphen format
    for dir_path in leetcode_dir.glob("*-*"):
        if dir_path.is_dir():
            potential_problem_dirs.append(dir_path)
    
    # Add other directories that contain Java files
    for dir_path in leetcode_dir.glob("*"):
        if dir_path.is_dir() and dir_path not in potential_problem_dirs:
            if list(dir_path.glob("*.java")):
                potential_problem_dirs.append(dir_path)
    
    logger.info(f"Found {len(potential_problem_dirs)} potential problem directories")
    
    # Process each problem directory
    for problem_dir in potential_problem_dirs:
            
        solutions_processed += 1
            
        # Extract problem slug from directory name
        dir_name = problem_dir.name
        try:
            # Handle different naming conventions
            if "-" in dir_name:
                # Standard format: 1-two-sum
                parts = dir_name.split("-", 1)
                if len(parts) == 2:
                    problem_slug = parts[1]
                else:
                    problem_slug = dir_name
            elif "_" in dir_name:
                # Alternate format: 1_two_sum
                parts = dir_name.split("_", 1)
                if len(parts) == 2:
                    problem_slug = parts[1].replace("_", "-")
                else:
                    problem_slug = dir_name.replace("_", "-")
            else:
                # No separator, just use as is
                problem_slug = dir_name
        except Exception as e:
            logger.error(f"Could not parse slug from directory: {dir_name}")
            errors += 1
            continue
            
        logger.info(f"Processing problem: {problem_slug}")
        
        # Query LeetCode for problem details
        try:
            # Add retries for API requests
            max_retries = 3
            retry_delay = 2
            
            for retry in range(max_retries):
                try:
                    response = requests.post(
                        graphql_url,
                        headers=headers,
                        cookies=cookies,
                        json={'query': query, 'variables': {'titleSlug': problem_slug}},
                        timeout=30  # Add timeout to prevent hanging
                    )
                    break
                except requests.exceptions.RequestException as e:
                    if retry < max_retries - 1:
                        logger.warning(f"Request failed for {problem_slug}, retrying in {retry_delay}s: {str(e)}")
                        time.sleep(retry_delay)
                        retry_delay *= 2  # Exponential backoff
                    else:
                        raise
            
            if response.status_code != 200:
                logger.error(f"Failed to fetch details for {problem_slug}: {response.status_code}")
                errors += 1
                continue
                
            data = response.json()
            problem_data = data.get('data', {}).get('question', {})
            
            if not problem_data:
                logger.error(f"No problem data found for {problem_slug}")
                errors += 1
                continue
                
            # Extract problem details with graceful fallbacks
            try:
                title = problem_data.get('title', 'Unknown Title')
                problem_id = problem_data.get('questionId', '0')
                difficulty = problem_data.get('difficulty', 'Unknown')
                category = problem_data.get('categoryTitle', '')
                
                # Process content if available
                content = "No problem description available."
                if problem_data.get('content'):
                    try:
                        # Use lxml parser if available, fallback to html.parser
                        try:
                            content = BeautifulSoup(problem_data.get('content', ''), 'lxml').get_text()
                        except:
                            content = BeautifulSoup(problem_data.get('content', ''), 'html.parser').get_text()
                    except Exception as e:
                        logger.warning(f"Could not parse problem description for {problem_slug}: {str(e)}")
                
                # Process stats
                stats_json = {}
                try:
                    if problem_data.get('stats'):
                        stats_json = json.loads(problem_data.get('stats', '{}'))
                except Exception as e:
                    logger.warning(f"Could not parse stats for {problem_slug}: {str(e)}")
                
                # Process submit stats
                submit_stats = problem_data.get('submitStats', {})
                ac_rate = submit_stats.get('acRate', 'N/A')
                total_submissions = submit_stats.get('totalSubmissionCount', 'N/A')
                total_accepted = submit_stats.get('totalAcceptedCount', 'N/A')
                
                # Process hints
                hints = problem_data.get('hints', [])
                hints_str = ""
                if hints:
                    hints_list = [f"- {hint}" for hint in hints]
                    hints_str = "\n".join(hints_list)
                
                # Process example testcases
                example_testcases = problem_data.get('exampleTestcases', '')
                
                # Get code snippets
                code_snippets = problem_data.get('codeSnippets', [])
                java_snippet = None
                for snippet in code_snippets:
                    if snippet.get('lang') == 'Java':
                        java_snippet = snippet.get('code', '')
                        break
            except Exception as e:
                logger.warning(f"Error processing basic problem metadata for {problem_slug}: {str(e)}")
                title = f"Problem {problem_slug}"
                problem_id = '0'
                difficulty = 'Unknown'
                content = "Failed to retrieve problem description."
            
            # Get tags with graceful fallbacks
            tags_str = ""
            try:
                tags = [tag.get('name', '') for tag in problem_data.get('topicTags', []) if tag and tag.get('name')]
                if tags:
                    tags_str = '\n'.join([f'- {tag}' for tag in tags])
                else:
                    tags_str = "No tags available."
            except Exception as e:
                logger.warning(f"Error processing tags for {problem_slug}: {str(e)}")
                tags_str = "Failed to retrieve tags."
            
            # Parse similar questions with graceful fallbacks
            similar_questions_content = ""
            try:
                similar_questions = []
                similar_questions_raw = problem_data.get('similarQuestions', '')
                if similar_questions_raw:
                    try:
                        similar_questions_data = json.loads(similar_questions_raw)
                        for question in similar_questions_data:
                            if question:
                                similar_questions.append({
                                    'title': question.get('title', 'Unknown'),
                                    'titleSlug': question.get('titleSlug', ''),
                                    'difficulty': question.get('difficulty', 'Unknown')
                                })
                    except json.JSONDecodeError:
                        logger.warning(f"Failed to parse similar questions JSON for problem {problem_id}")
                
                # Format similar questions section
                if similar_questions:
                    similar_questions_list = []
                    for q in similar_questions:
                        q_title = q.get('title', 'Unknown')
                        q_slug = q.get('titleSlug', '')
                        q_difficulty = q.get('difficulty', 'Unknown')
                        # Only add if we have a slug to link to
                        if q_slug:
                            similar_questions_list.append(f"- [{q_title}](https://leetcode.com/problems/{q_slug}/) ({q_difficulty})")
                    if similar_questions_list:
                        similar_questions_content = "\n\n## Similar Questions\n" + "\n".join(similar_questions_list)
            except Exception as e:
                logger.warning(f"Error processing similar questions for {problem_slug}: {str(e)}")
            
            # Process solution content if available, with graceful fallbacks
            solution_content = ""
            try:
                solution_data = problem_data.get('solution', {})
                if solution_data:
                    is_paid_only = solution_data.get('isPaidOnly', True)
                    if not is_paid_only and solution_data.get('content'):
                        # Parse HTML solution to markdown
                        try:
                            solution_html = solution_data.get('content', '')
                            # Use lxml parser if available, fallback to html.parser
                            try:
                                solution_text = BeautifulSoup(solution_html, 'lxml').get_text()
                            except:
                                solution_text = BeautifulSoup(solution_html, 'html.parser').get_text()
                            if solution_text.strip():
                                solution_content = f"\n\n## Official Solution\n{solution_text}"
                            else:
                                solution_content = "\n\n## Official Solution\n*No solution content available.*"
                        except Exception as e:
                            logger.warning(f"Error parsing solution HTML for {problem_slug}: {str(e)}")
                            solution_content = "\n\n## Official Solution\n*Error parsing solution content.*"
                    elif is_paid_only:
                        solution_content = "\n\n## Official Solution\n*This is a premium-only solution. Subscribe to LeetCode Premium for access.*"
            except Exception as e:
                logger.warning(f"Error processing solution data for {problem_slug}: {str(e)}")
            
            # Create problem stats section
            stats_section = ""
            if ac_rate != 'N/A' or total_accepted != 'N/A':
                stats_section = f"""
## Problem Stats
- **Acceptance Rate**: {ac_rate}
- **Total Submissions**: {total_submissions}
- **Accepted Submissions**: {total_accepted}
"""

            # Create hints section
            hints_section = ""
            if hints_str:
                hints_section = f"""
## Hints
{hints_str}
"""

            # Create code template section
            code_template = ""
            if java_snippet:
                code_template = f"""
## Code Template
```java
{java_snippet}
```
"""

            # Create example testcases section
            test_cases = ""
            if example_testcases:
                test_cases = f"""
## Example Test Cases
```
{example_testcases}
```
"""

            # Create README content with enhanced information
            readme_content = f"""# [{problem_id}] {title}

## Problem Description
{content}

## Difficulty: {difficulty}{' | ' + category if category else ''}

## Tags
{tags_str}{stats_section}{hints_section}{code_template}{test_cases}

## Approach
<!-- Add your approach explanation here -->

## Complexity Analysis
- Time Complexity: <!-- Add time complexity -->
- Space Complexity: <!-- Add space complexity -->{solution_content}
{similar_questions_content}
"""
            
            # Save to README.md
            readme_path = problem_dir / "README.md"
            with open(readme_path, 'w', encoding='utf-8') as f:
                f.write(readme_content)
            
            solutions_enhanced += 1
            logger.info(f"Added detailed README for {title}")
            
        except Exception as e:
            logger.error(f"Error processing {problem_slug}: {str(e)}")
            errors += 1
    
    logger.info(f"Enhancement completed. Processed: {solutions_processed}, Enhanced: {solutions_enhanced}, Errors: {errors}")
    logger.info("Note: Some problems may have partial data if certain fields couldn't be fetched, but the script continued processing.")
    return solutions_enhanced

def main():
    parser = argparse.ArgumentParser(description='Enhance LeetCode solutions with problem details and solutions')
    parser.add_argument('--dir', default='src/com/leetcode', help='Directory containing LeetCode solutions')
    parser.add_argument('--csrf-token', required=True, help='LeetCode CSRF token')
    parser.add_argument('--session-token', required=True, help='LeetCode session token')
    parser.add_argument('--verbose', action='store_true', help='Enable verbose logging')
    args = parser.parse_args()

    # Set logging level based on verbosity
    if args.verbose:
        logging.getLogger('leetcode_sync').setLevel(logging.DEBUG)
    
    # Ensure the directory exists
    leetcode_dir = Path(args.dir)
    if not leetcode_dir.exists():
        logger.info(f"Creating LeetCode directory: {leetcode_dir}")
        leetcode_dir.mkdir(parents=True, exist_ok=True)

    logger.info("Starting LeetCode solution enhancement")
    try:
        enhanced_count = enhance_solutions(args.dir, args.csrf_token, args.session_token)
        logger.info(f"Successfully enhanced {enhanced_count} solutions")
    except Exception as e:
        logger.error(f"Fatal error in main process: {str(e)}")
        import traceback
        logger.error(traceback.format_exc())
        exit(1)

if __name__ == '__main__':
    main()
