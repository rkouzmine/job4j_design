CREATE TABLE company (
    id integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person (
    id integer NOT NULL,
    name character varying,
    company_id integer references company(id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

INSERT INTO company (id, name) VALUES
(1, 'company1'),
(2, 'company2'),
(3, 'company3'),
(4, 'company4'),
(5, 'company5');

INSERT INTO person (id, name, company_id) VALUES
(1, 'name1', 1),
(2, 'name2', 1),
(3, 'name3', 2),
(4, 'name4', 2),
(5, 'name5', 3),
(6, 'name6', 3),
(7, 'name7', 3),
(8, 'name8', 4),
(9, 'name9', 4),
(10, 'name10', 4),
(11, 'name11', 4),
(12, 'name12', 4),
(13, 'name13', 5),
(14, 'name14', 5),
(15, 'name15', 5),
(16, 'name16', 5),
(17, 'name17', 5);

SELECT p.name, c.name
FROM company AS c
INNER JOIN person AS p ON c.id = p.company_id
WHERE p.company_id != 5;

SELECT c.name AS "company name", COUNT(p.id) AS "person count"
FROM company c
INNER JOIN person p ON c.id = p.company_id
GROUP BY c.name
HAVING COUNT(p.id) = (
  			SELECT COUNT(p2.id)
    		FROM company c2
    		INNER JOIN person p2 ON c2.id = p2.company_id
    		GROUP BY c2.name
			ORDER BY c2.name DESC
			LIMIT 1
            );
