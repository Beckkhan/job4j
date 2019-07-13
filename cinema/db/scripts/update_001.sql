create table hall (
    id serial primary key not null,
    row integer not null,
    place integer not null,
    price integer not null,
    sold boolean default false
);

create table visitors (
  id serial primary key,
  visitor_name varchar(100) not null,
  phone varchar(100) not null,
  seat_id integer references hall(id)
);

insert into hall(row, place, price) values
(1, 1, 300), (1, 2, 400), (1, 3, 300),
(2, 1, 400), (2, 2, 600), (2, 3, 400),
(3, 1, 500), (3, 2, 500), (3, 3, 500);