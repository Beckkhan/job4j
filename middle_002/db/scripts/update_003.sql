alter table users add column country varchar(50);
alter table users add column city varchar(50);

create table country(
  id serial primary key not null,
  title varchar(50) unique
);

create table city(
  id serial primary key not null,
  title varchar(50) unique,
  country_id integer references country(id)
);

insert into country (title) values
('Russia'),
('Canada'),
('China'),
('USA'),
('Brazil'),
('Australia'),
('India'),
('Argentina'),
('Kazakhstan'),
('Japan'),
('Germany'),
('United Kingdom'),
('France'),
('Italy'),
('Mexico'),
('South Korea'),
('Spain'),
('Turkey'),
('Netherlands'),
('Saudi Arabia');

insert into city (title, country_id) values
('Moscow', 1),
('Saint-Petersburg', 1),
('Ekaterinburg', 1),
('Novosibirsk', 1),
('Vladivostok', 1),
('Krasnodar', 1),
('Ottawa', 2),
('Montreal', 2),
('Toronto', 2),
('Vancouver', 2),
('Beijing', 3),
('Shanghai', 3),
('Guangzhou', 3),
('New York', 4),
('Los Angeles', 4),
('San Francisco', 4),
('Rio de Janeiro', 5),
('Brasilia', 5),
('Sydney', 6),
('Melbourne', 6),
('Brisbane', 6),
('New Delhi', 7),
('Mumbai', 7),
('Buenos Aires', 8),
('Mendoza', 8),
('Nursultan', 9),
('Almaty', 9),
('Tokio', 10),
('Kyoto', 10),
('Osaka', 10),
('Berlin', 11),
('Munich', 11),
('London', 12),
('Birmingham', 12),
('Paris', 13),
('Marseille', 13),
('Rome', 14),
('Milan', 14),
('Mexico City', 15),
('Guadalajara', 15),
('Seoul', 16),
('Busan', 16),
('Barcelona', 17),
('Madrid', 17),
('Istanbul', 18),
('Antalya', 18),
('Amsterdam', 19),
('Rotterdam', 19),
('Mecca', 20),
('Medina', 20);

insert into users (name, login, password, email, created, role, country, city)
values ('Admin', 'admin', 'admin', 'beckkhan@mail.ru', now(), 'ADMIN', 'Russia', 'Saint-Petersburg');