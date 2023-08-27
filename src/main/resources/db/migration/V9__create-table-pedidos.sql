create table pedidos (
    id varchar(255) not null,
    data datetime,
    total decimal(10, 2),

    primary key(id)
);