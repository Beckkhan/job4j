create table if not exists vacancies (
    id serial primary key not null,
    vacancy_name character varying(2000),
    description text,
    link text,
    actual_date Timestamp
);