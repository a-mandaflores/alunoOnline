package br.com.alunoonline.Api.servece;

import br.com.alunoonline.Api.model.Aluno;
import br.com.alunoonline.Api.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.server.ResponseStatusException;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Service
public class AlunoServece implements Serializable {

    @Autowired
    AlunoRepository alunoRepository;

    public void create(Aluno aluno){
        alunoRepository.save(aluno);
    }

    public List<Aluno> findAll(){
        return alunoRepository.findAll();
    }


    public Optional<Aluno> findById(Long id){
        return alunoRepository.findById(id);
    }

    public void update(Long id, Aluno aluno){
        Optional<Aluno> alunoFromDb = findById(id);

        if (alunoFromDb.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado no banco de dados");
        }

        Aluno alunoUpdatad = alunoFromDb.get();

        alunoUpdatad.setName(aluno.getName());
        alunoUpdatad.setEmail(aluno.getEmail());

        alunoRepository.save(alunoUpdatad);
    }


    public void deleteById(Long id){
        alunoRepository.deleteById(id);
    }

}