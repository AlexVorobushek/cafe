create table if not exists client
(
    id         serial
        primary key,
    name       varchar(200) not null,
    email      varchar(254) not null,
    birth_date date         not null
);

alter table client
    owner to root;


create table if not exists visit
(
    id        serial
        primary key,
    client_id serial,
    datetime  date not null
);

alter table visit
    owner to root;

