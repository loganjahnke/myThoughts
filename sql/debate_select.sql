SELECT * FROM person;

SELECT dt.title, p.firstname, p.lastname, dc.name
FROM debate_topic dt
JOIN person p
	ON dt.user_id = p.id
JOIN topic_category tc
	ON dt.id = tc.topic_id
JOIN debate_category dc
	ON tc.category_id = dc.id;