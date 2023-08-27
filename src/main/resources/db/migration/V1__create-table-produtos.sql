create table produtos(

    id bigint not null auto_increment,
    nome varchar(100) not null,
    codigo varchar(100) not null unique,
    valor varchar(100) not null,
    categoria varchar(100) not null,

    primary key(id)

);