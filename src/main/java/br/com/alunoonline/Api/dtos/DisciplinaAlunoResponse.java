package br.com.alunoonline.Api.dtos;

import br.com.alunoonline.Api.Enums.MatriculaAlunoStatusEnum;
import lombok.Data;

@Data
public class DisciplinaAlunoResponse {
    private String disciplinaNome;
    private String nomeProfessor;
    private Double nota1;
    private Double nota2;
    private Double media;
    private MatriculaAlunoStatusEnum status;
}
