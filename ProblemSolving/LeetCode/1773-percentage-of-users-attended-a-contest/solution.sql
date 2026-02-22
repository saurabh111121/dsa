# Write your MySQL query statement below
-- SELECT r.contest_id, ROUND(COUNT(DISTINCT r.user_id) * 100 / (SELECT COUNT(*) FROM Users), 2) AS Percentage
-- FROM Users u
-- CROSS JOIN Register r
-- GROUP BY r.contest_id
-- ORDER BY percentage DESC, r.contest_id ASC;


SELECT contest_id, ROUND(COUNT(user_id) * 100 /  (SELECT COUNT(user_id) FROM Users), 2 ) AS percentage
FROM Register
GROUP BY contest_id
ORDER BY percentage DESC, contest_id;



