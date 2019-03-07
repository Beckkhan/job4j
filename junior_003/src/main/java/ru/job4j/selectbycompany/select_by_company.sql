--	CREATE TABLE company
--	(
--	id integer NOT NULL,
--	name character varying,
--	CONSTRAINT company_pkey PRIMARY KEY (id)
--	);
--
--	CREATE TABLE person
--	(
--	id integer NOT NULL,
--	name character varying,
--	company_id integer,
--	CONSTRAINT person_pkey PRIMARY KEY (id)
--	);

--	1) Retrieve in a single query:
--		- names of all persons that are NOT in the company with id = 5
--		- company name for each person

select p.name, c.name from person as p
inner join company as c on c.id = p.company_id
where c.id != 5;

--	2) Select the name of the company with the maximum number of persons + number of persons in this company

select c.name, count(p.id) from company as c
inner join person as p on c.id = p.company_id
where count(p.id) = (select max(count(p.id) from p);