package br.com.alunoonline.Api.repository;

import br.com.alunoonline.Api.model.MatriculaAluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatriculaAlunoRepository extends JpaRepository<MatriculaAluno, Long> {
    List<MatriculaAluno> findByAlunoId(Long alunoId);
}
