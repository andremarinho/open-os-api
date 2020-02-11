CREATE TABLE produto(
	
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome varchar(50) NOT NULL

)engine innoDB charset=UTF8;

INSERT INTO produto (nome) VALUES ('Mala');
INSERT INTO produto (nome) VALUES ('bolsa');
INSERT INTO produto (nome) VALUES ('carteira');
INSERT INTO produto (nome) VALUES ('mochilete');
INSERT INTO produto (nome) VALUES ('frasqueira');

