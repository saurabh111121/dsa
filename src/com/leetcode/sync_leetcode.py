import os
import json
import requests
from github import Github
from datetime import datetime
import time
import logging
from typing import Dict, List, Optional

# Language to file extension mapping
EXTENSIONS = {
    'python': 'py', 'python3': 'py', 'java': 'java',
    'c': 'c', 'cpp': 'cpp', 'c++': 'cpp', 'javascript': 'js',
    'typescript': 'ts', 'golang': 'go', 'ruby': 'rb',
    'swift': 'swift', 'kotlin': 'kt', 'rust': 'rs',
    'scala': 'scala', 'php': 'php'
}

# API endpoints
LEETCODE_GRAPHQL_URL = "https://leetcode.com/graphql"
LEETCODE_SUBMISSIONS_URL = "https://leetcode.com/api/submissions/"
CACHE_FILE = "solutions_cache.json"

class LeetCodeGitHubSync:
    """Class to handle synchronization of LeetCode solutions to GitHub."""
    
    def __init__(self, github_token: str, github_repo: str, leetcode_session: str):
        """Initialize with required credentials."""
        if not all([github_token, github_repo, leetcode_session]):
            raise ValueError("Missing required credentials")

        self.github = Github(github_token)
        self.repo = self.github.get_repo(github_repo)
        self.headers = {
            'Cookie': f'LEETCODE_SESSION={leetcode_session}',
            'User-Agent': 'Mozilla/5.0',
            'Referer': 'https://leetcode.com'
        }
        self.solutions_cache = self.load_cache()
        
    def load_cache(self) -> Dict:
        """Load the solutions cache from the repository."""
        try:
            contents = self.repo.get_contents(CACHE_FILE)
            cache_content = contents.decoded_content.decode('utf-8')
            logger.info("Loaded existing cache file")
            return json.loads(cache_content)
        except:
            logger.info("No cache file found, creating new cache")
            return {}
    
    def save_cache(self) -> None:
        """Save the solutions cache to the repository."""
        try:
            cache_content = json.dumps(self.solutions_cache, indent=2, sort_keys=True)
            
            try:
                contents = self.repo.get_contents(CACHE_FILE)
                current_content = contents.decoded_content.decode('utf-8')
                
                if current_content.strip() != cache_content.strip():
                    self.repo.update_file(
                        CACHE_FILE,
                        "chore: Update solutions cache",
                        cache_content,
                        contents.sha
                    )
                    logger.info("Cache file updated")
            except:
                self.repo.create_file(
                    CACHE_FILE,
                    "chore: Create solutions cache",
                    cache_content
                )
                logger.info("Cache file created")
        except Exception as e:
            logger.error(f"Error saving cache: {e}")
    
    def fetch_all_submissions(self) -> List[Dict]:
        """Fetch all successful submissions from LeetCode."""
        offset, limit = 0, 20
        all_submissions = []
        
        while True:
            url = f"{LEETCODE_SUBMISSIONS_URL}?offset={offset}&limit={limit}"
            response = requests.get(url, headers=self.headers)
            
            if response.status_code != 200:
                logger.error(f"Failed to fetch submissions: {response.status_code}")
                break
            
            data = response.json()
            submissions = data.get('submissions_dump', [])
            
            if not submissions:
                break
                
            # Filter only accepted submissions
            accepted = [s for s in submissions if s.get('status_display') == 'Accepted']
            all_submissions.extend(accepted)
            
            if len(submissions) < limit:
                break
                
            offset += limit
            time.sleep(1)  # Avoid rate limiting
            
        return all_submissions
    
    def get_problem_details(self, titleSlug: str) -> Dict:
        """Get problem details using LeetCode GraphQL API."""
        query = """
        query questionData($titleSlug: String!) {
            question(titleSlug: $titleSlug) {
                questionId
                title
                titleSlug
                difficulty
                topicTags {
                    name
                    slug
                }
                content
            }
        }
        """
        
        response = requests.post(
            LEETCODE_GRAPHQL_URL,
            headers=self.headers,
            json={'query': query, 'variables': {'titleSlug': titleSlug}}
        )
        
        if response.status_code != 200:
            logger.error(f"Failed to fetch problem details: {response.status_code}")
            return {}
            
        data = response.json()
        return data.get('data', {}).get('question', {})
    
    def sync_solutions(self) -> None:
        """Sync LeetCode solutions to GitHub repository."""
        submissions = self.fetch_all_submissions()
        logger.info(f"Found {len(submissions)} accepted submissions")
        
        for submission in submissions:
            problem_slug = submission.get('title_slug')
            lang = submission.get('lang')
            code = submission.get('code')
            timestamp = submission.get('timestamp')
            
            if not all([problem_slug, lang, code, timestamp]):
                continue
                
            # Skip if we've already processed this submission (based on timestamp)
            cache_key = f"{problem_slug}_{lang}"
            if cache_key in self.solutions_cache and self.solutions_cache[cache_key] >= timestamp:
                continue
                
            # Get file extension for language
            ext = EXTENSIONS.get(lang.lower(), 'txt')
            
            # Get problem details
            problem_details = self.get_problem_details(problem_slug)
            problem_id = problem_details.get('questionId', '')
            
            if not problem_id:
                logger.warning(f"Could not get problem ID for {problem_slug}")
                continue
                
            # Format filename
            filename = f"{problem_id}.{problem_slug}.{ext}"
            file_path = f"src/com/leetcode/{filename}"
            
            # Add comment header with problem info
            header = f"""/*
 * Problem: {problem_id}. {problem_details.get('title', problem_slug)}
 * Difficulty: {problem_details.get('difficulty', 'Unknown')}
 * Link: https://leetcode.com/problems/{problem_slug}/
 * Synced: {datetime.now().strftime('%Y-%m-%d %H:%M:%S')}
 */

"""
            # Don't add header for Python files, use #
            if ext == 'py':
                header = f"""# Problem: {problem_id}. {problem_details.get('title', problem_slug)}
# Difficulty: {problem_details.get('difficulty', 'Unknown')}
# Link: https://leetcode.com/problems/{problem_slug}/
# Synced: {datetime.now().strftime('%Y-%m-%d %H:%M:%S')}

"""
            
            content = header + code
            
            try:
                # Check if file exists
                try:
                    file = self.repo.get_contents(file_path)
                    self.repo.update_file(
                        file_path,
                        f"feat: Update LeetCode solution for problem {problem_id}",
                        content,
                        file.sha
                    )
                    logger.info(f"Updated solution for problem {problem_id}")
                except:
                    self.repo.create_file(
                        file_path,
                        f"feat: Add LeetCode solution for problem {problem_id}",
                        content
                    )
                    logger.info(f"Added new solution for problem {problem_id}")
                
                # Update cache
                self.solutions_cache[cache_key] = timestamp
                time.sleep(1)  # Avoid rate limiting
                
            except Exception as e:
                logger.error(f"Error syncing {filename}: {e}")
        
        # Save updated cache
        self.save_cache()
        
if __name__ == "__main__":
    # Configure logging
    logging.basicConfig(
        level=logging.INFO,
        format='%(asctime)s - %(levelname)s - %(message)s'
    )
    logger = logging.getLogger(__name__)
    
    # Get credentials from environment variables
    github_token = os.environ.get("GITHUB_TOKEN")
    github_repo = os.environ.get("GITHUB_REPOSITORY")
    leetcode_session = os.environ.get("LEETCODE_SESSION")
    
    try:
        syncer = LeetCodeGitHubSync(github_token, github_repo, leetcode_session)
        syncer.sync_solutions()
        logger.info("Sync completed successfully!")
    except Exception as e:
        logger.error(f"Sync failed: {e}")
        exit(1)
