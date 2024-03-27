package br.com.alunoonline.Api.controller;

import br.com.alunoonline.Api.model.Aluno;
import br.com.alunoonline.Api.servece.AlunoServece;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    AlunoServece alunoServece;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Aluno aluno){
        alunoServece.create(aluno);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Aluno> findAll(){
        return alunoServece.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Aluno> findById(@PathVariable Long id){
        return alunoServece.findById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Aluno aluno, @PathVariable Long id){
        alunoServece.update(id, aluno);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id){
        alunoServece.deleteById(id);
    }

}
