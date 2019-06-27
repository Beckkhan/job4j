create table if not exists users (
    id serial primary key not null,
    name varchar(200),
    login varchar(200),
    email varchar(200),
    created timestamp
);