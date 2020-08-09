CREATE USER test WITH password 'test';

CREATE DATABASE converter;
WITH
OWNER = test
TABLESPACE = pg_default
CONNECTION LIMIT = -1;

\c converter;

create table public.currency_rates
(
    id        serial                    not null
        constraint currency_pk
            primary key,
    valute_id varchar                   not null,
    num_code  varchar                   not null,
    char_code varchar                   not null,
    nominal   integer                   not null,
    name      varchar,
    value     double precision          not null,
    date      date default CURRENT_DATE not null
);

alter table public.currency_rates
    owner to test;

create unique index currency_id_uindex
    on currency_rates (id);

create table history
(
    id                   serial                    not null
        constraint history_pk
            primary key,
    source_currency      varchar                   not null,
    target_currency      varchar                   not null,
    original_amount      double precision          not null,
    received_amount      double precision          not null,
    date                 date default CURRENT_DATE not null,
    source_currency_name varchar,
    target_currency_name varchar
);

alter table history
    owner to test;

create unique index history_id_uindex
    on history (id);
