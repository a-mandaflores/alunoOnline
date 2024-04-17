package br.com.alunoonline.Api.service;

import br.com.alunoonline.Api.Enums.MatriculaAlunoStatusEnum;
import br.com.alunoonline.Api.dtos.AtualizarNotasRequest;
import br.com.alunoonline.Api.model.MatriculaAluno;
import br.com.alunoonline.Api.repository.MatriculaAlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.event.DocumentListener;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Service
public class MatriculaAlunoService implements Serializable {

    public static final double GRADE_AVG_TO_APROVE = 7.0;
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

    public void updateGrades(Long matriculaId, AtualizarNotasRequest atualizarNotasRequest){
        MatriculaAluno matriculaAluno =
                matriculaAlunoRepository.findById(matriculaId)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Matricula não encontrada"));


    }

    public void updateStatusGrades(MatriculaAluno matriculaAluno, AtualizarNotasRequest atualizarNotasRequest){
        if(atualizarNotasRequest.getNota1() != null){
            matriculaAluno.setNota1(atualizarNotasRequest.getNota1());
        }

        if (atualizarNotasRequest.getNota2() != null){
            matriculaAluno.setNota2(atualizarNotasRequest.getNota2());
        }
    }

    public void updateStudentStatus(MatriculaAluno matriculaAluno) {
       Double nota1 = matriculaAluno.getNota1();
       Double nota2 = matriculaAluno.getNota2();

       if (nota1 != null && nota2 != null){
           Double avarage = (nota1 + nota2) / 2;
           matriculaAluno.setStatus(avarage >= GRADE_AVG_TO_APROVE ? MatriculaAlunoStatusEnum.APROVADO: MatriculaAlunoStatusEnum.REPROVADO);
       }
    }
}
