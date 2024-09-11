create table `board`
(
    `id`      bigint       not null auto_increment,
    `title`   varchar(255) not null,
    `content` tinytext     not null,
    primary key (`id`)
) engine = InnoDB;

create table `comment`
(
    `board_id` bigint   not null,
    `id`       bigint   not null auto_increment,
    `content`  tinytext not null,
    primary key (`id`)
) engine = InnoDB;

alter table if exists `comment`
    add constraint `FKa4c86tfj8m0x641s0geuhyorn`
        foreign key (`board_id`)
            references `board` (`id`);


