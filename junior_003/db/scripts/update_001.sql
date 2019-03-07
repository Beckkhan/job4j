create table if not exists items (
    id serial primary key not null,
    item_name varchar(200),
    description text,
    created timestamp,
    comments text
);