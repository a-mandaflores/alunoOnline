package br.com.alunoonline.Api.service;

import br.com.alunoonline.Api.Enums.CriarAlunoRequest;
import br.com.alunoonline.Api.model.Aluno;
import br.com.alunoonline.Api.model.Curso;
import br.com.alunoonline.Api.repository.AlunoRepository;
import br.com.alunoonline.Api.repository.CursoRepository;
import br.com.alunoonline.Api.repository.FinanceiroAlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Service
public class AlunoService implements Serializable {

    @Autowired
    AlunoRepository alunoRepository;

    @Autowired
    FinanceiroAlunoRepository financeiroAlunoRepository

    @Autowired
    CursoRepository cursoRepository

    public void create(CriarAlunoRequest criarAlunoRequest){
        Curso curso = cursoRepository.findById(criarAlunoRequest.getCourseId())
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND));

        Aluno alunoSaved = alunoRepository.save(
                new Aluno(
                        null,
                        criarAlunoRequest.getName(),
                        criarAlunoRequest.getEmail(),
                        curso
                )
        );




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