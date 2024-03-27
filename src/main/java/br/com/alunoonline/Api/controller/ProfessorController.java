package br.com.alunoonline.Api.controller;

import br.com.alunoonline.Api.model.Professor;
import br.com.alunoonline.Api.servece.ProfessorServece;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    ProfessorServece professorServece;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Professor professor){
        professorServece.create(professor);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Professor> findAll(){
        return professorServece.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Professor> findById(@PathVariable Long id){
        return professorServece.findById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @RequestBody Professor professor){
        professorServece.updated(id, professor);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        professorServece.deleteById(id);
    }
}
