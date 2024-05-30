package br.com.alunoonline.Api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Fatura  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_financial_id")
    private FinanceiroAluno studentFiancial;

    private LocalDateTime dueDate;
    private LocalDateTime paidOn;
    private LocalDateTime createdAt;

}
