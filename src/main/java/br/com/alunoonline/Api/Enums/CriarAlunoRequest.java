package br.com.alunoonline.Api.Enums;

import lombok.Data;

@Data
public class CriarAlunoRequest {
    private String name;
    private String email;
    private Long courseId;
    private Double discount;
    private Integer dueDate;
}
