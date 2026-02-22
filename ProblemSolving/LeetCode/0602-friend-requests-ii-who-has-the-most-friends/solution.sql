with new as (
(select accepter_id as id, count(*) as num from RequestAccepted
group by 1)
union all
(select requester_id as id, count(*) as num from RequestAccepted
group by 1)
)

select id, sum(num) as num from new
group by id
order by 2 desc limit 1
