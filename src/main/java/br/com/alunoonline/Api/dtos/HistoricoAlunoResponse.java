package br.com.alunoonline.Api.dtos;

import lombok.Data;

import java.util.List;

@Data
public class HistoricoAlunoResponse {
    private String nomeAluno;
    private String emailAluno;

    private List<DisciplinaAlunoResponse> disciplinaAlunoResponseList;

}
