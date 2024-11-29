CREATE TABLE matricula_aluno (
    id BIGSERIAL PRIMARY KEY,
    nota1 DOUBLE PRECISION,
    nota2 DOUBLE PRECISION,
    aluno_id BIGINT NOT NULL,
    disciplina_id BIGINT NOT NULL,
    status VARCHAR(255),
    FOREIGN KEY (aluno_id) REFERENCES aluno(id),
    FOREIGN KEY (disciplina_id) REFERENCES disciplina(id)
);