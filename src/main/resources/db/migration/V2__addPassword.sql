ALTER TABLE funcionario ADD COLUMN senha varchar(255) NOT NULL default 'ALTERARSENHA';

CREATE TABLE historico_usuarios(
    funcionario_id int NOT NULL references funcionario(id),
    data timestamp,
    acao varchar(11)
    --Acao - LOGIN | REDEFINICAO | CRIACAO
);