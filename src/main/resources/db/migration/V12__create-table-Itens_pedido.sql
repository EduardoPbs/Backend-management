create table itens_pedido(
    id varchar(255),
    valor_unidade decimal(10, 2),
    quantidade int,
    pedido_id varchar(255),
    produto_id varchar(255),
    foreign key (pedido_id) references pedidos(id),
    foreign key (produto_id) references produtos(id)
);