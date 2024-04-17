package br.com.alunoonline.Api.service;

import br.com.alunoonline.Api.model.Professor;
import br.com.alunoonline.Api.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService implements Serializable {

    @Autowired
    ProfessorRepository professorRepository;

    public void create(Professor professor){
        professorRepository.save(professor);
    }

    public List<Professor> findAll(){
        return professorRepository.findAll();
    }

    public Optional<Professor> findById(Long id){
        return professorRepository.findById(id);
    }

    public void updated(Long id, Professor professor){
        Optional<Professor> professorFromBd = findById(id);

        if (professorFromBd.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Professor não existe no banco de dados");
        }

        Professor professorUpadated = professorFromBd.get();

        professorUpadated.setName(professor.getName());
        professorUpadated.setEmail(professor.getEmail());

        professorRepository.save(professorUpadated);
    }

    public void deleteById(Long id){
        professorRepository.deleteById(id);
    }


}
