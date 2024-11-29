package br.com.alunoonline.Api.dtos;

import br.com.alunoonline.Api.Enums.MatriculaAlunoStatusEnum;
import br.com.alunoonline.Api.model.Professor;
import lombok.Data;

@Data
public class DisciplinaAlunoResponse {
    private String disciplinaNome;
    private Professor nomeProfessor;
    private Double nota1;
    private Double nota2;
    private Double media;
    private MatriculaAlunoStatusEnum status;
}
