create table costumer
(
    id    int          not null primary key auto_increment,
    name  varchar(100) not null,
    email varchar(300) not null unique
)