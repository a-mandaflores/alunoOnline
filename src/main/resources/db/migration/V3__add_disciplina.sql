CREATE TABLE disciplina (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    professor_id BIGINT,
    FOREIGN KEY (professor_id) REFERENCES professor(id)
);