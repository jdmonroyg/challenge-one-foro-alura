create table if not exists usuarios (
    id bigint not null auto_increment,
    nombre varchar(100) not null,
    email varchar (100) not null unique,
    password varchar(100) not null,

    primary key(id)
);