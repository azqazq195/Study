drop table if exists account_book cascade;

drop table if exists tb_user cascade;

create table account_book
(
    id             bigint       not null auto_increment,
    amount         integer      not null,
    created_at     timestamp    not null default current_timestamp,
    deleted_at     timestamp    null,
    modified_at    timestamp    not null default current_timestamp,
    note           varchar(255) not null,
    created_by_id  bigint,
    modified_by_id bigint,
    primary key (id)
);

create table tb_user
(
    id          bigint       not null auto_increment,
    created_at  timestamp    not null default current_timestamp,
    deleted_at  timestamp    null,
    modified_at timestamp    not null default current_timestamp,
    email       varchar(255) not null,
    password    varchar(255) not null,
    primary key (id)
);

alter table tb_user
    add constraint UK_4vih17mube9j7cqyjlfbcrk4m unique (email);

alter table account_book
    add constraint FK4m8mono1dfmr2uuavr9oyc77p
        foreign key (created_by_id)
            references tb_user (id);

alter table account_book
    add constraint FKnkimttw21xsde4ak0bw5lu2eb
        foreign key (modified_by_id)
            references tb_user (id);