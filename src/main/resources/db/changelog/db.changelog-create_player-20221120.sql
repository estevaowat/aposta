create table if not exists player
(
    id   int          not null primary key auto_increment,
    name varchar(100) not null unique
);