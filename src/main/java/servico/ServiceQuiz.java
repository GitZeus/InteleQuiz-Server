package servico;

import entidade.Questao;
import entidade.Quiz;
import entidade.Turma;
import entidade.TurmaQuiz;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistencia.GatewayQuiz;
import util.ITQException;
import util.RestMessage;
import util.RestMessageType;

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
    
    public List<Turma> listTurmasByProfessorByDisciplina(String matricula, Integer id) throws ITQException {
        try {
            return gatewayQuiz.listTurmasByProfessorByDisciplina(matricula, id);
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
    
    public RestMessage saveQuiz(Quiz q) throws ITQException{
        try {
            boolean sucesso = gatewayQuiz.saveQuiz(q);
            RestMessage message = new RestMessage();
            if(sucesso){
                message.setText("Quiz incluído com sucesso");
                message.setType(RestMessageType.SUCCESS);
            }else{
                message.setText("Erro ao incluir Quiz, contate o administrador do sistema");
                message.setType(RestMessageType.ERROR);
            }
            return message;
        } catch (Exception e) {
            throw new ITQException(e.getMessage());
        }
    }
    
    public RestMessage updateQuiz(Quiz q) throws ITQException{
        try {
            boolean sucesso = gatewayQuiz.updateQuiz(q);
            RestMessage message = new RestMessage();
            if(sucesso){
                message.setText("Quiz alterado com sucesso");
                message.setType(RestMessageType.SUCCESS);
            }else{
                message.setText("Erro ao alterar Quiz, contate o administrador do sistema");
                message.setType(RestMessageType.ERROR);
            }
            return message;
        } catch (Exception e) {
            throw new ITQException(e.getMessage());
        }
    }
    
    public List<Questao> listQuestoesByQuiz(Integer quiz_id) throws ITQException {
        try {
            return gatewayQuiz.listQuestoesByQuiz(quiz_id);
        } catch (Exception e) {
            throw new ITQException(e.getMessage());
        }
    }
    
    public RestMessage publicarQuiz(TurmaQuiz tq) throws ITQException{
        try {
            boolean sucesso = gatewayQuiz.publicarQuiz(tq);
            RestMessage message = new RestMessage();
            if(sucesso){
                message.setText("Quiz publicado com sucesso");
                message.setType(RestMessageType.SUCCESS);
            }else{
                message.setText("Erro ao publicar Quiz, contate o administrador do sistema");
                message.setType(RestMessageType.ERROR);
            }
            return message;
        } catch (Exception e) {
            throw new ITQException(e.getMessage());
        }
    }
}
