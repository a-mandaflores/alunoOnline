package br.com.alunoonline.Api.servece;

import br.com.alunoonline.Api.model.Disciplina;
import br.com.alunoonline.Api.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Service
public class DisciplinaServece implements Serializable {

    @Autowired
    DisciplinaRepository disciplinaRepository;

    public void create(Disciplina disciplina){
        disciplinaRepository.save(disciplina);
    }

    public List<Disciplina> findAll(){
        return disciplinaRepository.findAll();
    }

    public Optional<Disciplina> findById(Long id){
        return disciplinaRepository.findById(id);
    }

    public void update(Long id, Disciplina disciplina){
        Optional<Disciplina> displinaFromDb = findById(id);

        if (displinaFromDb.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Disciplina displinaUpdate = displinaFromDb.get();

        displinaUpdate.setName(disciplina.getName());
        displinaUpdate.setProfessor(disciplina.getProfessor());

        disciplinaRepository.save(displinaUpdate);
    }

    public void deleteById(Long id){
        disciplinaRepository.deleteById(id);
    }
}
