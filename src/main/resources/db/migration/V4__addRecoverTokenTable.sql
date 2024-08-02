CREATE TABLE token_recuperacao(
    id SERIAL PRIMARY KEY,
    funcionario_id INT REFERENCES funcionario(id) NOT NULL,
    data_expiracao TIMESTAMP NOT NULL,
    criado_em TIMESTAMP NOT NULL
)