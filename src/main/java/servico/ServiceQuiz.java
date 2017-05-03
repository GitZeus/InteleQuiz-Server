package servico;

import entidade.Quiz;
import entidade.Turma;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistencia.GatewayQuiz;
import util.ITQException;

@Service
public class ServiceQuiz {

    @Autowired
    private GatewayQuiz gatewayQuiz;

    public List<Turma> listTurmasByProfessor(String matricula) throws ITQException {
        try {
            return gatewayQuiz.listTurmasByProfessor(matricula);
        } catch (Exception e) {
            throw new ITQException(e.getMessage());
        }
    }
    
    public List<Quiz> listQuizByDisciplinaByProfessor(String matricula_professor, Integer disciplina_id) throws ITQException {
        try {
            return gatewayQuiz.listQuizByDisciplinaByProfessor(matricula_professor, disciplina_id);
        } catch (Exception e) {
            throw new ITQException(e.getMessage());
        }
    }
}
