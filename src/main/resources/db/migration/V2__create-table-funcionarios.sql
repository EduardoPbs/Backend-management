create table funcionario(

    id bigint not null auto_increment,
    nome varchar(100) not null,
    cpf varchar(100) not null unique,
    senha varchar(20) not null,
    rua varchar(100) not null,
    bairro varchar(100) not null,
    numero varchar(6),
    complemento varchar(100),

    primary key(id)

);