CREATE TABLE cargo(
                      id SERIAL PRIMARY KEY NOT NULL UNIQUE,
                      nome varchar(100) NOT NULL,
                      salario_base numeric(10,2) NOT NULL,
                      carga_horaria int NOT NULL
);

CREATE TABLE funcionario(
                            id SERIAL PRIMARY KEY,
                            nome varchar(255) NOT NULL,
                            cpf varchar(11) NOT NULL UNIQUE,
                            data_nascimento date NOT NULL,
                            sexo varchar(9) NOT NULL,
                            telefone varchar(14) NOT NULL UNIQUE,
                            email varchar(100) NOT NULL UNIQUE,
                            salario numeric(10,2) NOT NULL,
                            cargo_id int NOT NULL references cargo(id)
);

CREATE TABLE ponto(
                      id SERIAL PRIMARY KEY,
                      horario_entrada timestamp,
                      horario_saida timestamp,
                      carga_horaria interval,
                      funcionario_id int NOT NULL references funcionario(id)

                          CHECK(horario_saida > horario_entrada)
);

CREATE TABLE torre(
                      id SERIAL PRIMARY KEY,
                      nome varchar(100) NOT NULL,
                      andares int NOT NULL,
                      qtd_unidades int NOT NULL
);

CREATE TABLE unidade(
                        id SERIAL PRIMARY KEY,
                        numero int NOT NULL,
                        torre_id int NOT NULL references torre(id)
);

CREATE TABLE morador(
                        id SERIAL PRIMARY KEY,
                        nome varchar(255) NOT NULL,
                        cpf varchar(11) NOT NULL UNIQUE,
                        data_nascimento date NOT NULL,
                        sexo varchar(9) NOT NULL,
                        unidade_id int NOT NULL references unidade(id)
);

CREATE TABLE visitante(
                          id SERIAL PRIMARY KEY,
                          nome varchar(255) NOT NULL,
                          cpf varchar(11) NOT NULL UNIQUE,
                          data_nascimento date NOT NULL,
                          sexo varchar(9) NOT NULL
);

CREATE TABLE acessos(
                        id SERIAL PRIMARY KEY,
                        morador_id int NOT NULL references morador(id),
                        visitante_id int NOT NULL references visitante(id),
                        data_entrada timestamp NOT NULL,
                        data_saida timestamp NOT NULL
);

CREATE TABLE encomenda(
                          id SERIAL PRIMARY KEY,
                          descricao varchar(255) NOT NULL,
                          peso float NOT NULL,
                          altura float NOT NULL,
                          largura float NOT NULL,
                          comprimento float NOT NULL,
                          data_recepcao timestamp NOT NULL,
                          data_entrega timestamp,
                          entregue boolean,
                          morador_id int references morador(id),
                          funcionario_id int NOT NULL references funcionario(id)

                              check(data_entrega > data_recepcao)

);

CREATE OR REPLACE FUNCTION atualizar_carga_horaria()
    RETURNS TRIGGER AS $$
BEGIN
    IF NEW.horario_saida IS NOT NULL THEN
        NEW.carga_horaria := NEW.horario_saida - NEW.horario_entrada;
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_atualizar_carga_horaria
    BEFORE UPDATE ON ponto
    FOR EACH ROW
EXECUTE FUNCTION atualizar_carga_horaria();


CREATE OR REPLACE FUNCTION verificar_salario_funcionario()
RETURNS TRIGGER AS $$
BEGIN
IF NEW.salario < (SELECT salario_base FROM cargo WHERE id = NEW.cargo_id) THEN
RAISE EXCEPTION 'O Salário Não pode ser menor que o salário minimo do cargo';
END IF;
RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_check_salario
    AFTER INSERT OR UPDATE ON funcionario
                        FOR EACH ROW
                        EXECUTE FUNCTION verificar_salario_funcionario();

