CREATE TABLE demanda (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	codigo_sistema BIGINT(20) NOT NULL,
	codigo_lote BIGINT(20) NOT NULL,
	plataforma VARCHAR(30) NOT NULL,
	tipo VARCHAR(20) NOT NULL,
	codigo_contagem BIGINT(20),
	data DATE NOT NULL,
	status VARCHAR(20) NOT NULL,
	observacao VARCHAR(200),	
	valor DECIMAL(10,2) NOT NULL,	
	FOREIGN KEY (codigo_sistema) REFERENCES sistema(codigo),
	FOREIGN KEY (codigo_pessoa) REFERENCES pessoa(codigo),
	FOREIGN KEY (codigo_lote) REFERENCES lote(codigo),
	FOREIGN KEY (codigo_contagem) REFERENCES contagem(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO demanda (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) values ('Salário mensal', '2017-06-10', null, 6500.00, 'Distribuição de lucros', 'RECEITA', 1, 1);
