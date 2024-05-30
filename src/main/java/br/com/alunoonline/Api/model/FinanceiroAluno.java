package br.com.alunoonline.Api.model;

import br.com.alunoonline.Api.Enums.FinanceiroStatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class FinanceiroAluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @OneToOne
    @JoinColumn(name = "student_id")
    private Aluno aluno;

    private Double discount;
    private Integer dueDate;

    @Enumerated(EnumType.STRING)
    private FinanceiroStatusEnum status;
}
