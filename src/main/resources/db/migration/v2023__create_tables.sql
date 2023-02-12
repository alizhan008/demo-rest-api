create table users
(
    id       bigserial
        constraint users_pk
            primary key,
    username varchar(100) not null
        unique,
    email    varchar(100) not null,
    password varchar(100) not null,
    enabled  boolean      not null
);

alter table users
    owner to postgres;

create table authorities
(
    id        bigserial
        constraint authorities_pk
            primary key,
    username  varchar(150) not null
        constraint authorities_user_name_fk
            references users (username),
    authority varchar(150) not null
);

alter table authorities
    owner to postgres;

create unique index authorities_id_uindex
    on authorities (id);

create table quotes
(
    id          bigserial
        primary key,
    description varchar(255),
    vote        integer,
    user_id     bigint
        constraint quotes_users_id_fk
            references users
);

alter table quotes
    owner to postgres;
