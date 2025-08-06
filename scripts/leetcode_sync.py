#!/usr/bin/env python3
"""
LeetCode Solution Enhancement Script
This script enhances the LeetCode solutions that have been synced to the repository
by adding detailed problem information, official solutions, and similar questions.
It works with solutions fetched by the joshcai/leetcode-sync GitHub Action.
"""

import os
import re
import json
import requests
from bs4 import BeautifulSoup
from pathlib import Path
import argparse
import logging
import time

# Set up logging
logging.basicConfig(
    level=logging.INFO,
    format='%(asctime)s - %(name)s - %(levelname)s - %(message)s'
)
logger = logging.getLogger('leetcode_sync')

def test_leetcode_api(graphql_url, headers, cookies):
    """
    Test the LeetCode API to verify credentials and API availability.
    
    Args:
        graphql_url (str): LeetCode GraphQL endpoint
        headers (dict): HTTP headers for the request
        cookies (dict): Cookies for authentication
        
    Returns:
        bool: True if API is working and credentials are valid, False otherwise
    """
    # Get recent submissions query
    recent_submissions_query = """
    query recentSubmissions {
      submissionList(limit: 1) {
        submissions {
          id
          title
          titleSlug
          status
          statusDisplay
          lang
          timestamp
        }
      }
    }
    """
    
    try:
        logger.info("Testing LeetCode API access...")
        response = requests.post(
            graphql_url,
            headers=headers,
            cookies=cookies,
            json={'query': recent_submissions_query},
            timeout=30
        )
        
        # Check response status
        if response.status_code != 200:
            logger.error(f"API test failed with status code: {response.status_code}")
            logger.error(f"Response: {response.text}")
            return False
            
        # Check if response has expected structure
        data = response.json()
        
        # Check if data is properly structured
        if not data.get('data'):
            logger.error("API response missing data field")
            logger.error(f"Response: {json.dumps(data)}")
            return False
            
        if not data.get('data', {}).get('submissionList'):
            logger.error("API response missing submissionList field")
            logger.error(f"Response: {json.dumps(data)}")
            return False
            
        submissions = data.get('data', {}).get('submissionList', {}).get('submissions')
        if submissions is None:
            logger.error("API response has null submissions field - likely expired session token")
            logger.error("Please refresh your LeetCode CSRF and session tokens")
            return False
            
        if not isinstance(submissions, list):
            logger.error(f"API response submissions field is not a list: {type(submissions)}")
            logger.error(f"Response: {json.dumps(data)}")
            return False
            
        # Log success
        if len(submissions) > 0:
            submission = submissions[0]
            logger.info(f"Successfully retrieved submission for '{submission.get('title', 'Unknown')}' in {submission.get('lang', 'Unknown')}")
        else:
            logger.info("API accessible but no recent submissions found")
            
        return True
        
    except Exception as e:
        logger.error(f"Error testing LeetCode API: {str(e)}")
        return False

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
    
    # Create the directory if it doesn't exist
    leetcode_dir.mkdir(parents=True, exist_ok=True)
    
    # List directory contents
    dir_contents = list(leetcode_dir.glob("*"))
    logger.info(f"Directory contents ({len(dir_contents)} items):")
    for item in dir_contents:
        if item.is_dir():
            logger.info(f"  DIR: {item.name}")
        else:
            logger.info(f"  FILE: {item.name}")
    
    if not dir_contents:
        logger.warning("The directory is empty. No solutions to process.")
        logger.info("This could indicate that the joshcai/leetcode-sync action didn't fetch any solutions.")
        logger.info("Possible causes:")
        logger.info("  1. Authentication issues (expired or incorrect tokens)")
        logger.info("  2. No accepted solutions on LeetCode account")
        logger.info("  3. Network or connectivity issues")
        logger.info("  4. LeetCode API changes")
        logger.info("Please check your GitHub Actions logs for more details.")
    
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
        'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'
    }
    
    # Test authentication with a simple query
    test_query = """
    query {
        userStatus {
            username
            isSignedIn
        }
    }
    """
    
    logger.info("Testing LeetCode authentication...")
    try:
        test_response = requests.post(
            graphql_url,
            headers=headers,
            cookies=cookies,
            json={'query': test_query},
            timeout=30
        )
        
        if test_response.status_code != 200:
            logger.error(f"Authentication test failed with status code: {test_response.status_code}")
            logger.error(f"Response: {test_response.text}")
            return 0
        else:
            test_data = test_response.json()
            user_status = test_data.get('data', {}).get('userStatus', {})
            is_signed_in = user_status.get('isSignedIn', False)
            username = user_status.get('username', 'Unknown')
            
            if is_signed_in:
                logger.info(f"Successfully authenticated as: {username}")
                
                # Test the API specifically for submissions endpoint
                if not test_leetcode_api(graphql_url, headers, cookies):
                    logger.error("LeetCode API test failed - cannot proceed with enhancement")
                    logger.error("Please check your LEETCODE_CSRF_TOKEN and LEETCODE_SESSION values")
                    logger.error("These tokens may have expired and need to be refreshed")
                    return 0
            else:
                logger.error("Not signed in to LeetCode. Check your credentials.")
                return 0
    except Exception as e:
        logger.error(f"Error testing authentication: {str(e)}")
        return 0
    
    # GraphQL query for problem details and solution
    query = """
    query questionData($titleSlug: String!) {
      question(titleSlug: $titleSlug) {
        questionId
        title
        titleSlug
        content
        difficulty
        topicTags {
          name
          slug
        }
        similarQuestions
        solution {
          content
          isPaidOnly
        }
      }
    }
    """
    
    solutions_processed = 0
    solutions_enhanced = 0
    errors = 0
    
    # Look for any problem directories or Java files
    all_problem_dirs = []
    
    # First look for standard format directories (e.g., "1-two-sum")
    for problem_dir in leetcode_dir.glob("*-*"):
        if problem_dir.is_dir():
            all_problem_dirs.append(problem_dir)
    
    # If we didn't find any in the standard format, look for any directories with Java files
    if not all_problem_dirs:
        for directory in leetcode_dir.glob("*"):
            if directory.is_dir() and list(directory.glob("*.java")):
                all_problem_dirs.append(directory)
    
    # Also look for Java files directly in the leetcode_dir
    java_files = list(leetcode_dir.glob("*.java"))
    if java_files and not all_problem_dirs:
        logger.info(f"Found {len(java_files)} Java files directly in the leetcode directory.")
        logger.info("Creating problem directories for these files...")
        
        for java_file in java_files:
            if java_file.name.lower() == 'solution.java' or java_file.name.lower() == 'solutions.java':
                continue
                
            # Try to extract the problem name from the file name
            file_name = java_file.stem  # Get file name without extension
            problem_dir = leetcode_dir / file_name
            problem_dir.mkdir(exist_ok=True)
            
            # Copy the file to the new directory
            with open(java_file, 'r') as source_file:
                content = source_file.read()
            
            target_file = problem_dir / "Solution.java"
            with open(target_file, 'w') as dest_file:
                dest_file.write(content)
            
            all_problem_dirs.append(problem_dir)
    
    if not all_problem_dirs:
        logger.warning("No problem directories found to process!")
        return 0
        
    logger.info(f"Found {len(all_problem_dirs)} problem directories to process")
    
    # Process each problem folder
    for problem_dir in all_problem_dirs:
            
        solutions_processed += 1
            
        # Extract problem slug from directory name
        dir_name = problem_dir.name
        problem_slug = None
        
        try:
            # Handle different directory naming formats
            if "-" in dir_name:
                # Standard format: 1-two-sum
                parts = dir_name.split("-", 1)
                if len(parts) == 2:
                    problem_slug = parts[1]
                else:
                    problem_slug = dir_name
            elif "_" in dir_name:
                # Alternative format: 1_two_sum or two_sum
                parts = dir_name.split("_", 1)
                if len(parts) == 2 and parts[0].isdigit():
                    problem_slug = parts[1].replace("_", "-")
                else:
                    problem_slug = dir_name.replace("_", "-")
            else:
                # Try to find slug in solution file
                solution_files = list(problem_dir.glob("*.java"))
                if solution_files:
                    with open(solution_files[0], 'r') as f:
                        content = f.read()
                        # Look for URL pattern in comments
                        url_match = re.search(r'(?:leetcode\.com/problems/|@lc.*id=)([a-zA-Z0-9\-]+)', content)
                        if url_match:
                            problem_slug = url_match.group(1)
                        else:
                            # Extract title from filename or directory if no URL found
                            problem_slug = dir_name.lower().replace(" ", "-")
        except Exception as e:
            logger.error(f"Could not parse slug from directory: {dir_name}: {str(e)}")
            errors += 1
            continue
        
        if not problem_slug:
            logger.error(f"Could not determine problem slug from directory: {dir_name}")
            errors += 1
            continue
            
        logger.info(f"Processing problem: {problem_slug}")
        
        # Query LeetCode for problem details
        try:
            response = requests.post(
                graphql_url,
                headers=headers,
                cookies=cookies,
                json={'query': query, 'variables': {'titleSlug': problem_slug}}
            )
            
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
                
            # Extract problem details
            title = problem_data.get('title', 'Unknown Title')
            problem_id = problem_data.get('questionId', '0')
            difficulty = problem_data.get('difficulty', 'Unknown')
            category = ""  # Initialize empty category
            content = BeautifulSoup(problem_data.get('content', ''), 'html.parser').get_text()
            
            # Initialize empty sections
            stats_section = ""
            hints_section = ""
            code_template = ""
            test_cases = ""
            
            # Get tags
            tags = [tag.get('name', '') for tag in problem_data.get('topicTags', [])]
            tags_str = '\n'.join([f'- {tag}' for tag in tags if tag])
            
            # Parse similar questions
            similar_questions = []
            similar_questions_raw = problem_data.get('similarQuestions', '')
            if similar_questions_raw:
                try:
                    similar_questions_data = json.loads(similar_questions_raw)
                    for question in similar_questions_data:
                        similar_questions.append({
                            'title': question.get('title', ''),
                            'titleSlug': question.get('titleSlug', ''),
                            'difficulty': question.get('difficulty', '')
                        })
                except json.JSONDecodeError:
                    logger.error(f"Failed to parse similar questions for problem {problem_id}")
            
            # Format similar questions section
            similar_questions_content = ""
            if similar_questions:
                similar_questions_list = []
                for q in similar_questions:
                    q_title = q.get('title', '')
                    q_slug = q.get('titleSlug', '')
                    q_difficulty = q.get('difficulty', '')
                    similar_questions_list.append(f"- [{q_title}](https://leetcode.com/problems/{q_slug}/) ({q_difficulty})")
                similar_questions_content = "\n\n## Similar Questions\n" + "\n".join(similar_questions_list)
            
            # Process solution content if available
            solution_content = ""
            solution_data = problem_data.get('solution', {})
            if solution_data:
                is_paid_only = solution_data.get('isPaidOnly', True)
                if not is_paid_only and solution_data.get('content'):
                    # Parse HTML solution to markdown
                    solution_html = solution_data.get('content', '')
                    solution_text = BeautifulSoup(solution_html, 'html.parser').get_text()
                    solution_content = f"\n\n## Official Solution\n{solution_text}"
                elif is_paid_only:
                    solution_content = "\n\n## Official Solution\n*This is a premium-only solution. Subscribe to LeetCode Premium for access.*"
            
            # Create README content
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
    return solutions_enhanced

def main():
    parser = argparse.ArgumentParser(description='Enhance LeetCode solutions with problem details and solutions')
    parser.add_argument('--dir', default='src/com/leetcode', help='Directory containing LeetCode solutions')
    parser.add_argument('--csrf-token', required=True, help='LeetCode CSRF token')
    parser.add_argument('--session-token', required=True, help='LeetCode session token')
    parser.add_argument('--verbose', action='store_true', help='Enable verbose logging')
    parser.add_argument('--check-auth', action='store_true', help='Only check authentication without processing solutions')
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
    
    # Check if we're only testing authentication
    if args.check_auth:
        # LeetCode GraphQL endpoint
        graphql_url = "https://leetcode.com/graphql"
        
        # Set headers with the session cookies
        cookies = {
            'csrftoken': args.csrf_token,
            'LEETCODE_SESSION': args.session_token,
        }
        
        headers = {
            'Content-Type': 'application/json',
            'X-CSRFToken': args.csrf_token,
            'Referer': 'https://leetcode.com/problems/',
            'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'
        }
        
        test_leetcode_api(graphql_url, headers, cookies)
        logger.info("Authentication check completed.")
        exit(0)
    
    # Otherwise, proceed with enhancement
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
