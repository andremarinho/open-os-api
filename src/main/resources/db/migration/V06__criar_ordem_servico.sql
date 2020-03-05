CREATE TABLE os (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	data_abertura DATE NOT NULL,
	num_ordem_servico BIGINT(20),
	observacao VARCHAR(100),
	cod_produto BIGINT(20),
	cod_cliente BIGINT(20),
	cod_status BIGINT(20),
	FOREIGN KEY (cod_produto) REFERENCES produto(codigo),
	FOREIGN KEY (cod_cliente) REFERENCES cliente(codigo),
	FOREIGN KEY (cod_status) REFERENCES status(codigo)

)ENGINE=Innodb DEFAULT CHARSET=utf8