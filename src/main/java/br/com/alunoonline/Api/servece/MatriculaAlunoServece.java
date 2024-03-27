package br.com.alunoonline.Api.servece;

import br.com.alunoonline.Api.Enums.MatriculaAlunoStatusEnum;
import br.com.alunoonline.Api.model.MatriculaAluno;
import br.com.alunoonline.Api.repository.MatriculaAlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Service
public class MatriculaAlunoServece implements Serializable {
    @Autowired
    MatriculaAlunoRepository matriculaAlunoRepository;

    public void create(MatriculaAluno matriculaAluno){
        matriculaAluno.setStatus(MatriculaAlunoStatusEnum.MATRICULADO);
        matriculaAlunoRepository.save(matriculaAluno);
    }


    public List<MatriculaAluno> findAll(){
        return matriculaAlunoRepository.findAll();
    }

    public Optional<MatriculaAluno> findById(Long id){
        return matriculaAlunoRepository.findById(id);
    }

    public void update(Long id, MatriculaAluno matriculaAluno){
        Optional<MatriculaAluno> maticulaAlunoDb = findById(id);

        if (maticulaAlunoDb.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Matricula não encontrada no banco de dados");
        }

        MatriculaAluno matriculaAlunoUpdate = maticulaAlunoDb.get();

        matriculaAlunoUpdate.setAluno(matriculaAluno.getAluno());
        matriculaAlunoUpdate.setDisciplina(matriculaAluno.getDisciplina());
        matriculaAlunoUpdate.setNota1(matriculaAluno.getNota1());
        matriculaAlunoUpdate.setNota2(matriculaAluno.getNota2());

        matriculaAlunoRepository.save(matriculaAlunoUpdate);
    }

    public void updateNotas(Long id, MatriculaAluno matriculaAluno){
        Optional<MatriculaAluno> matriculaAlunoDb = findById(id);

        if (matriculaAlunoDb.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nota não econtrada no banco de dados");

        }

        MatriculaAluno matriculaAlunoUpdated = matriculaAlunoDb.get();

        matriculaAlunoUpdated.setNota1(matriculaAluno.getNota1());
        matriculaAlunoUpdated.setNota2(matriculaAluno.getNota2());

        Double verifyStatus = (matriculaAluno.getNota1() + matriculaAluno.getNota2()) / 2;

        if (verifyStatus >= 7){
            matriculaAlunoUpdated.setStatus(MatriculaAlunoStatusEnum.APROVADO);
        }else{
            matriculaAlunoUpdated.setStatus(MatriculaAlunoStatusEnum.REPROVADO);
        }

        matriculaAlunoRepository.save(matriculaAlunoUpdated);

    }

    public void updateTrancarMatricula(Long id, MatriculaAluno matriculaAluno){
        Optional<MatriculaAluno> matriculaAlunoDb = findById(id);

        if (matriculaAlunoDb.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nota não econtrada no banco de dados");

        }

        MatriculaAluno matriculaAlunoUpdated = matriculaAlunoDb.get();

        if (matriculaAluno.getStatus() != MatriculaAlunoStatusEnum.TRANCADO){
            matriculaAlunoUpdated.setStatus(MatriculaAlunoStatusEnum.TRANCADO);
            matriculaAlunoRepository.save(matriculaAlunoUpdated);
        }else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A matricula ja esta trancada");
        }


    }

    public void deleteById(Long id){
        matriculaAlunoRepository.deleteById(id);
    }
}
