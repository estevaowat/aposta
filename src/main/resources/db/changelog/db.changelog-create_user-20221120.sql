create table if not exists user
(
    id    int          not null primary key,
    name  varchar(100) not null,
    email varchar(300) not null unique
)