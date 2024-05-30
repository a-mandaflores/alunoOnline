package br.com.alunoonline.Api.repository;

import br.com.alunoonline.Api.model.Fatura;
import br.com.alunoonline.Api.model.FinanceiroAluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface FaturaRepository extends JpaRepository<Fatura, Long> {
    boolean existsByStudentFinancialAndDueDate(FinanceiroAluno financeiroAluno, LocalDateTime localDateTime);
}
