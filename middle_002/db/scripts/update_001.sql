create table if not exists users (
    id serial primary key not null,
    name varchar(50),
    login varchar(50) not null unique,
    password varchar(50) not null,
    email varchar(50),
    created timestamp,
    role varchar(50) not null DEFAULT USER
);