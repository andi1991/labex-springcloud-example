GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY 'Aliyun-test';
flush privileges;

create database test;
use test;

create table user
(
    id          bigint auto_increment,
    name        varchar(64)                         not null,
    remark      varchar(64)                         null,
    age         int                                 null,
    country     varchar(32)                         null,
    addr        varchar(64)                         null,
    modify_time timestamp default CURRENT_TIMESTAMP not null,
    create_time timestamp default CURRENT_TIMESTAMP not null,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB;
