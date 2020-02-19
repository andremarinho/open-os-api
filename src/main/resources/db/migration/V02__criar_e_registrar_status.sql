CREATE TABLE status(
	
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome varchar(50) NOT NULL

)engine innoDB charset=UTF8;

INSERT INTO status (nome) VALUES ('Aberto');
INSERT INTO status (nome) VALUES ('Assistencia Local');
INSERT INTO status (nome) VALUES ('Assistencia Fornecedor');
INSERT INTO status (nome) VALUES ('Pendente');
INSERT INTO status (nome) VALUES ('Resolvido');
