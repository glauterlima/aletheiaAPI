CREATE SEQUENCE sistema_seq;

CREATE TABLE sistema (
	codigo BIGINT PRIMARY KEY DEFAULT NEXTVAL ('sistema_seq'),
	nome VARCHAR(50) NOT NULL
) ;

INSERT INTO sistema (nome) values ('SARH');
