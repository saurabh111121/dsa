SELECT d.name AS Department,e.name AS Employee,e.salary AS Salary 
FROM Employee e  INNER JOIN Department d ON d.id= e.DepartmentId
WHERE (SELECT COUNT( DISTINCT salary) FROM Employee AS E2 WHERE E2.DepartmentID=d.id AND E2.salary>=e.salary)<=3;
