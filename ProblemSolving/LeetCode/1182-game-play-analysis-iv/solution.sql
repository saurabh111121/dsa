# Write your MySQL query statement below
SELECT ROUND(
    COUNT(next_day.player_id) / COUNT(DISTINCT first_day.player_id), 2
) AS fraction
FROM (
    SELECT player_id, MIN(event_date) AS first_login
    FROM Activity
    GROUP BY player_id
) AS first_day
LEFT JOIN Activity AS next_day
  ON first_day.player_id = next_day.player_id
 AND next_day.event_date = DATE_ADD(first_day.first_login, INTERVAL 1 DAY);

