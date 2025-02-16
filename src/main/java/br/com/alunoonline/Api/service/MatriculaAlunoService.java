package br.com.alunoonline.Api.service;

import br.com.alunoonline.Api.Enums.MatriculaAlunoStatusEnum;
import br.com.alunoonline.Api.dtos.AtualizarNotasRequest;
import br.com.alunoonline.Api.dtos.DisciplinaAlunoResponse;
import br.com.alunoonline.Api.dtos.HistoricoAlunoResponse;
import br.com.alunoonline.Api.model.MatriculaAluno;
import br.com.alunoonline.Api.repository.MatriculaAlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.Serializable;
import java.util.ArrayList;
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

    public List<MatriculaAluno> findByStudent(Long alunoId){
        return matriculaAlunoRepository.findByAlunoId(alunoId);
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


        updateStatusGrades(matriculaAluno, atualizarNotasRequest);
        updateStudentStatus(matriculaAluno);

        matriculaAlunoRepository.save(matriculaAluno);


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

    public void upsateStatusToBreak(Long matriculaId){
        MatriculaAluno matriculaAluno =
            matriculaAlunoRepository.findById(matriculaId)
                    .orElseThrow(() ->
                            new ResponseStatusException(HttpStatus.NOT_FOUND, "Matirucla aluno"));

        if (!MatriculaAlunoStatusEnum.MATRICULADO.equals(matriculaAluno.getStatus())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Só é possivel trncar se tiver matriculado");
        }

    }
    public  void changeStatus(MatriculaAluno matriculaAluno, MatriculaAlunoStatusEnum matriculaAlunoStatusEnum){
        matriculaAluno.setStatus(matriculaAlunoStatusEnum);
        matriculaAlunoRepository.save(matriculaAluno);
    };


    public HistoricoAlunoResponse getAcademicTranscript(Long alunoId){
        List<MatriculaAluno> matriculaDoAluno= matriculaAlunoRepository.findByAlunoId((alunoId));

        if (matriculaDoAluno.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Essa pessoa nao esta cadastrada");

        }


        HistoricoAlunoResponse hisotirco = new HistoricoAlunoResponse();

        hisotirco.setNomeAluno(matriculaDoAluno.get(0).getAluno().getName());
        hisotirco.setEmailAluno(matriculaDoAluno.get(0).getAluno().getEmail());

        List<DisciplinaAlunoResponse> disciplinaList = new ArrayList<>();

        for (MatriculaAluno matriculaAluno: matriculaDoAluno){
            DisciplinaAlunoResponse disciplinaAlunoResponse = new DisciplinaAlunoResponse();
            disciplinaAlunoResponse.setDisciplinaNome(matriculaAluno.getDisciplina().getName());

            if (matriculaAluno.getNota1() != null && matriculaAluno.getNota2() != null){
                    disciplinaAlunoResponse.setMedia((matriculaAluno.getNota1() + matriculaAluno.getNota2()) / 2.0);
            }else{
                disciplinaAlunoResponse.setMedia(null);
            }

            disciplinaAlunoResponse.setStatus(matriculaAluno.getStatus());
            disciplinaList.add(disciplinaAlunoResponse);

        }
        return hisotirco;

    }
}
