#!/usr/bin/env python3
"""
LeetCode Solution Enhancement Script
This script enhances the LeetCode solutions that have been synced to the repository
by adding detailed problem information, official solutions, and similar questions.
It works with solutions fetched by the joshcai/leetcode-sync GitHub Action.
"""

import os
import json
import requests
from bs4 import BeautifulSoup
from pathlib import Path
import argparse
import logging

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
    
    # Process each problem folder
    for problem_dir in leetcode_dir.glob("*-*"):
        if not problem_dir.is_dir():
            continue
            
        solutions_processed += 1
            
        # Extract problem slug from directory name
        dir_name = problem_dir.name
        try:
            problem_slug = "-".join(dir_name.split("-")[1:])
        except:
            logger.error(f"Could not parse slug from directory: {dir_name}")
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
            content = BeautifulSoup(problem_data.get('content', ''), 'html.parser').get_text()
            
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

## Difficulty: {difficulty}

## Tags
{tags_str}

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
