#!/usr/bin/env python3
"""
LeetCode Solution Sync Script
This script automatically fetches LeetCode solutions from a specified user account
and synchronizes them to the GitHub repository with proper formatting and organization.
"""

import os
import re
import json
import time
import logging
import datetime
import requests
from pathlib import Path
from bs4 import BeautifulSoup

# Set up logging
logging.basicConfig(
    level=logging.INFO,
    format='%(asctime)s - %(name)s - %(levelname)s - %(message)s'
)
logger = logging.getLogger('leetcode_sync')

# Configuration
LEETCODE_USERNAME = os.environ.get('LEETCODE_USERNAME')
LEETCODE_PASSWORD = os.environ.get('LEETCODE_PASSWORD')
USER_PROFILE = '111121saurabh'  # LeetCode user profile to sync
REPO_ROOT = Path(os.path.dirname(os.path.dirname(os.path.abspath(__file__))))
SOLUTIONS_DIR = REPO_ROOT / 'src' / 'com' / 'leetcode' / 'solutions'
DAILY_CHALLENGE_DIR = REPO_ROOT / 'src' / 'com' / 'leetcode' / 'daily_challenge'

# LeetCode URLs
BASE_URL = 'https://leetcode.com'
LOGIN_URL = f'{BASE_URL}/accounts/login/'
GRAPHQL_URL = f'{BASE_URL}/graphql'
USER_SOLUTIONS_URL = f'{BASE_URL}/api/submissions/?username={USER_PROFILE}&limit=100'

class LeetCodeSync:
    def __init__(self):
        """Initialize the LeetCode Sync tool."""
        self.session = requests.Session()
        self.solutions_count = 0
        self.problems_data = {}  # Cache for problem data
    
    def login(self):
        """Log in to LeetCode using the provided credentials with direct API approach."""
        logger.info("Logging in to LeetCode...")
        
        try:
            # Set up common headers
            headers = {
                'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36',
                'Referer': f'{BASE_URL}/',
                'Origin': BASE_URL
            }
            self.session.headers.update(headers)
            
            # First get the login page to retrieve the CSRF token
            response = self.session.get(LOGIN_URL)
            soup = BeautifulSoup(response.text, 'html.parser')
            
            # Find the CSRF token
            csrf_token = None
            for meta in soup.find_all('meta'):
                if meta.get('name') == 'csrf-token':
                    csrf_token = meta.get('content')
                    break
                    
            if not csrf_token:
                # Try to find it in input fields if not in meta
                csrf_input = soup.find('input', {'name': 'csrfmiddlewaretoken'})
                if csrf_input:
                    csrf_token = csrf_input.get('value')
            
            if not csrf_token:
                logger.error("Could not find CSRF token")
                return False
                
            # Prepare login data
            login_data = {
                'login': LEETCODE_USERNAME,
                'password': LEETCODE_PASSWORD,
                'csrfmiddlewaretoken': csrf_token,
                'next': '/'
            }
            
            # Add CSRF token to headers
            headers['X-CSRFToken'] = csrf_token
            headers['Referer'] = LOGIN_URL
            
            # Perform login
            response = self.session.post(
                LOGIN_URL,
                data=login_data,
                headers=headers,
                allow_redirects=True
            )
            
            # Check if login was successful
            if 'leetcode.com/accounts/logout' in response.text or response.url.endswith('/problems'):
                logger.info("Successfully logged in to LeetCode")
                return True
            else:
                logger.error("Login failed, incorrect response")
                return False
            
        except Exception as e:
            logger.error(f"Login failed: {str(e)}")
            return False
    
    def get_user_solutions(self):
        """Fetch all solutions for the specified user."""
        logger.info(f"Fetching solutions for user: {USER_PROFILE}")
        
        all_submissions = []
        offset = 0
        limit = 100  # Max limit per request
        
        while True:
            url = f'{USER_SOLUTIONS_URL}&offset={offset}'
            response = self.session.get(url)
            
            if response.status_code != 200:
                logger.error(f"Failed to fetch submissions: {response.status_code}")
                break
            
            data = response.json()
            submissions = data.get('submissions_dump', [])
            
            if not submissions:
                break
                
            all_submissions.extend(submissions)
            
            if len(submissions) < limit:
                break
                
            offset += limit
            time.sleep(1)  # Be nice to LeetCode API
        
        logger.info(f"Found {len(all_submissions)} submissions")
        
        # Filter to only accepted submissions, and get the latest one for each problem
        accepted_solutions = {}
        for submission in all_submissions:
            if submission['status_display'] == 'Accepted':
                problem_id = submission['question_id']
                problem_title = submission['title']
                
                if problem_id not in accepted_solutions or \
                   submission['timestamp'] > accepted_solutions[problem_id]['timestamp']:
                    accepted_solutions[problem_id] = submission
        
        logger.info(f"Found {len(accepted_solutions)} unique accepted solutions")
        return list(accepted_solutions.values())
    
    def get_problem_details(self, slug):
        """Fetch problem details using GraphQL API."""
        if slug in self.problems_data:
            return self.problems_data[slug]
            
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
            codeSnippets {
              lang
              langSlug
              code
            }
            similarQuestions
          }
        }
        """
        
        variables = {"titleSlug": slug}
        
        headers = {
            'Content-Type': 'application/json',
            'Referer': f'{BASE_URL}/problems/{slug}/'
        }
        
        response = self.session.post(
            GRAPHQL_URL,
            headers=headers,
            json={'query': query, 'variables': variables}
        )
        
        if response.status_code != 200:
            logger.error(f"Failed to fetch problem details for {slug}: {response.status_code}")
            return None
            
        data = response.json()
        problem_data = data.get('data', {}).get('question', {})
        
        if not problem_data:
            logger.error(f"No problem data found for {slug}")
            return None
            
        self.problems_data[slug] = problem_data
        return problem_data
    
    def format_solution(self, submission, problem_data):
        """Format the solution with proper structure and comments."""
        code = submission['code']
        lang = submission['lang'].lower()
        extension = 'java'  # Default to Java
        
        # Extract solution class code
        solution_code = self.extract_solution_code(code, lang)
        
        # Create formatted solution
        header = f"""/*
 * @lc app=leetcode id={problem_data['questionId']} lang=java
 *
 * [{problem_data['questionId']}] {problem_data['title']}
 */

// @lc code=start
"""
        
        footer = """
// @lc code=end
"""
        
        formatted_solution = f"{header}{solution_code}{footer}"
        return formatted_solution, extension
    
    def extract_solution_code(self, code, lang):
        """Extract the core solution code from the submission."""
        # For Java, we want to keep the Solution class but remove any package declarations
        if lang == 'java':
            # Remove package declarations
            code = re.sub(r'package\s+[\w\.]+;', '', code)
            
            # Make sure imports are included
            # We're keeping this simple, but you could enhance to clean up imports as well
            return code.strip()
        
        return code.strip()
    
    def generate_readme(self, problem_data, submission):
        """Generate a README.md file with problem details and solution approach."""
        title = problem_data['title']
        problem_id = problem_data['questionId']
        difficulty = problem_data['difficulty']
        content = BeautifulSoup(problem_data['content'], 'html.parser').get_text()
        
        # Get tags
        tags = [tag['name'] for tag in problem_data.get('topicTags', [])]
        tags_str = '\n'.join([f'- {tag}' for tag in tags])
        
        # Parse submission stats
        runtime = submission.get('runtime', 'N/A')
        memory = submission.get('memory', 'N/A')
        
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
        
        readme_content = f"""# [{problem_id}] {title}

## Problem Description
{content}

## Difficulty: {difficulty}

## Tags
{tags_str}

## Approach
<!-- Add your approach explanation here -->

## Complexity Analysis
- Time Complexity: <!-- Add time complexity -->
- Space Complexity: <!-- Add space complexity -->

## Submission Stats
- Runtime: {runtime}
- Memory Usage: {memory}{similar_questions_content}
"""
        return readme_content
    
    def save_solution(self, submission, problem_data):
        """Save the solution to the appropriate location in the repository."""
        problem_id = int(problem_data['questionId'])
        title_slug = problem_data['titleSlug']
        formatted_id = f"p{problem_id:04d}"  # Format as p0001, p0002, etc.
        
        # Create directory structure
        solution_dir = SOLUTIONS_DIR / f"{formatted_id}_{title_slug}"
        solution_dir.mkdir(parents=True, exist_ok=True)
        
        # Format and save solution code
        formatted_code, extension = self.format_solution(submission, problem_data)
        solution_file = solution_dir / f"Solution.{extension}"
        solution_file.write_text(formatted_code)
        
        # Generate and save README
        readme_content = self.generate_readme(problem_data, submission)
        readme_file = solution_dir / "README.md"
        readme_file.write_text(readme_content)
        
        # Check if this is a daily challenge and create a symlink or copy
        submission_date = datetime.datetime.fromtimestamp(submission['timestamp']).strftime('%Y-%m-%d')
        daily_dir = DAILY_CHALLENGE_DIR / submission_date
        
        # For now, we'll just copy it to the daily challenge directory if it was submitted today
        today = datetime.datetime.now().strftime('%Y-%m-%d')
        if submission_date == today:
            daily_dir.mkdir(parents=True, exist_ok=True)
            daily_solution_file = daily_dir / f"Solution.{extension}"
            daily_readme_file = daily_dir / "README.md"
            
            # Copy files
            daily_solution_file.write_text(formatted_code)
            daily_readme_file.write_text(readme_content)
            
            logger.info(f"Saved daily challenge solution for {submission_date}: {title_slug}")
        
        self.solutions_count += 1
        logger.info(f"Saved solution {self.solutions_count}: {formatted_id}_{title_slug}")
    
    def sync(self):
        """Main synchronization process."""
        logger.info("Starting LeetCode solution sync")
        
        try:
            # Create required directories
            SOLUTIONS_DIR.mkdir(parents=True, exist_ok=True)
            DAILY_CHALLENGE_DIR.mkdir(parents=True, exist_ok=True)
            
            # Log in to LeetCode
            if not self.login():
                logger.error("Login failed. Aborting sync process.")
                return False
            
            # Fetch all user solutions
            solutions = self.get_user_solutions()
            
            # Process each solution
            for solution in solutions:
                try:
                    # Get problem details
                    problem_slug = solution['title_slug']
                    problem_data = self.get_problem_details(problem_slug)
                    
                    if problem_data:
                        # Save the solution
                        self.save_solution(solution, problem_data)
                    
                    time.sleep(0.5)  # Be nice to LeetCode API
                    
                except Exception as e:
                    logger.error(f"Error processing solution {solution.get('title', 'unknown')}: {str(e)}")
            
            logger.info(f"Successfully synced {self.solutions_count} solutions")
            return True
            
        except Exception as e:
            logger.error(f"Sync process failed: {str(e)}")
            return False
    
if __name__ == '__main__':
    sync_tool = LeetCodeSync()
    success = sync_tool.sync()
    exit(0 if success else 1)
