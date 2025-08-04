INSERT INTO tb_pedido(nome, cpf, data, status, valor_total) VALUES ('Renato do Pneu', '12345678901', '0225-04-28', 'REALIZADO', 790.0);
INSERT INTO tb_item_do_pedido(quantidade, descricao, valor_unitario, pedido_id) VALUES (2, 'Mouse da silva', 250, 1);
INSERT INTO tb_item_do_pedido(quantidade, descricao, valor_unitario, pedido_id) VALUES (1, 'Teclado gamer', 290, 1);

INSERT INTO tb_pedido(nome, cpf, data, status) VALUES ('Luís Calota', '12345678902', '0225-04-28', 'REALIZADO');
INSERT INTO tb_item_do_pedido(quantidade, descricao, valor_unitario, pedido_id) VALUES (1, 'Monitor gamer', 790, 2);