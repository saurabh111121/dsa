# Write your MySQL query statement below
SELECT t1.firstName AS firstName,
       t1.lastName AS lastName,
       t2.city AS city,
       t2.state AS state
FROM Person t1
LEFT JOIN Address t2
ON t1.personId = t2.personId;
