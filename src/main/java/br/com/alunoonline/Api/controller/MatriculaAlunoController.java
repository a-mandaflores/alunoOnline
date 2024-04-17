package br.com.alunoonline.Api.controller;

import br.com.alunoonline.Api.model.MatriculaAluno;
import br.com.alunoonline.Api.service.MatriculaAlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/matricula-aluno")
public class MatriculaAlunoController {

    @Autowired
    MatriculaAlunoService matriculaAlunoServece;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody MatriculaAluno matriculaAluno){
        matriculaAlunoServece.create(matriculaAluno);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<MatriculaAluno> findAll(){
        return matriculaAlunoServece.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<MatriculaAluno> findById(@PathVariable Long id){
        return matriculaAlunoServece.findById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @RequestBody MatriculaAluno matriculaAluno){
        matriculaAlunoServece.update(id, matriculaAluno);
    }

    @PatchMapping("/nota/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateNotas(@PathVariable Long id, @RequestBody MatriculaAluno matriculaAluno){
        matriculaAlunoServece.updateNotas(id, matriculaAluno);
    }

    @PatchMapping("/matricula/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTrancarMatricula(@PathVariable Long id, @RequestBody MatriculaAluno matriculaAluno){

        matriculaAlunoServece.updateTrancarMatricula(id, matriculaAluno);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id){
        matriculaAlunoServece.deleteById(id);
    }


}
