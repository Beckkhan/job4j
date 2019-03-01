create database carwarehouse;

create table bodytype (
		id serial primary key,
		name varchar(200)
);

create table engine (
		id serial primary key,
		name varchar(200)
);

create table transmission (
		id serial primary key,
		name varchar(200)
);

create table cars (
		id serial primary key,
		brand varchar(200),
		bodytype_id integer references bodytype(id),
		engine_id integer references engine(id),
		transmission_id integer references transmission(id)
);

insert into bodytype(name) values
('sedan'),
('suv'),
('liftback'),
('pickup'),
('hatchback');

insert into engine(name) values
('gasoline'),
('diesel'),
('gas'),
('electric'),
('hydrogen');

insert into transmission(name) values
('manual'),
('auto'),
('robot'),
('variator');

insert into cars(brand, bodytype_id, engine_id, transmission_id) values
('UAZ HUNTER', 2, 1, 1),
('LADA 2107', 1, 1, 1),
('FORD F-150', 4, 1, 2),
('TOYOTA PRIUS', 3, 4, 2),
('SKODA OCTAVIA', 3, 1, 3);

--1. Вывести список всех машин и все привязанные к ним детали.
select c.brand, b.name, e.name, t.name from cars as c
left outer join bodytype as b on c.bodytype_id = b.id
left outer join engine as e on c.engine_id = e.id
left outer join transmission as t on c.transmission_id = t.id;

--2. Вывести отдельно детали, которые не используются в машине: кузова, двигатели, коробки передач.
select b.name from bodytype as b
left outer join cars as c on c.bodytype_id = b.id where c.brand is null;

select e.name from cars as c
right outer join engine as e on e.id = c.engine_id where c.brand is null;

select t.name from transmission as t
left outer join cars as c on c.transmission_id = t.id where c.brand is null;