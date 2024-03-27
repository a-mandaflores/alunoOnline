package br.com.alunoonline.Api.repository;

import br.com.alunoonline.Api.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long >{
}
