package br.com.alunoonline.Api.repository;

import br.com.alunoonline.Api.model.FinanceiroAluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinanceiroAlunoRepository extends JpaRepository<FinanceiroAluno, Long> {
}
