create table produtos(
    id varchar(255),
    nome varchar(100) not null,
    codigo varchar(100) not null unique,
    valor varchar(100),
    categoria varchar(100),

    primary key(id)
);