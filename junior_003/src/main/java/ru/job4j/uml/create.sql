create database simpletracker;

create table role (
		id serial primary key,
		description text
);

create table rolerights (
		id serial primary key,
		description text
);

create table rorirelation (
		id serial primary key,
		role_id integer references role(id),
		rolerights_id integer references rolerights(id)
);

create table users (
		id serial primary key,
		name varchar (2000),
		login character varying (2000),
		password character varying (2000),
		create_date timestamp,
		role_id integer references role(id)
);

create table item_category (
		id serial primary key,
		description text
);

create table item_status (
		id serial primary key,
		description text
);

create table items (
		id serial primary key,
		description text,
		user_id integer references users(id),
		item_category_id integer references item_category(id),
		item_status_id integer references item_status(id)
);

create table comments (
		id serial primary key,
		description text,
		item_id integer references items(id)
);

create table attachments (
		id serial primary key,
		description text,
		location varchar(2000),
		item_id integer references items(id)
);

insert into role(description) values('Java Developer');

insert into rolerights(description) values('Professional development at the expense of the company');

insert into rorirelation(role_id, rolerights_id) values(1, 1);

insert into users(name, login, password, create_date, role_id) values('Slava', 'postgres', 'password', '2019-02-28 21:45:07', 1);

insert into item_category(description) values('Urgent');

insert into item_status(description) values('Being processed');

insert into items(description, user_id, item_category_id, item_status_id) values('Test application', 1, 1, 1);

insert into comments(description, item_id) values('This is a first Item', 1);

insert into attachments(description, location, item_id) values('This Item has an attached file', 'localhost', 1);