# Write your MySQL query statement below

-- SELECT e1.name as name
-- FROM Employee e1 
-- LEFT JOIN Employee e2
-- ON e1.id = e2.managerId
-- WHERE e2.managerId IS NOT NULL 
-- GROUP BY e2.managerId
-- HAVING COUNT(e2.managerId) >= 5;


SELECT name
FROM Employee
WHERE id IN 
(SELECT managerID FROM Employee GROUP BY managerID HAVING COUNT(managerId) > 4 );
