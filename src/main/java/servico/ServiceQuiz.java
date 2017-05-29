package servico;

import entidade.Questao;
import entidade.Quiz;
import entidade.Turma;
import entidade.Publicacao;
import enums.StatusTurmaQuiz;
import java.util.ArrayList;
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

    public List<Turma> listTurmasByProfessorByDisciplina(String matricula, int id) throws ITQException {
        try {
            return gatewayQuiz.listTurmasByProfessorByDisciplina(matricula, id);
        } catch (Exception e) {
            throw new ITQException(e.getMessage());
        }
    }

    public List<Quiz> listQuizByDisciplinaByProfessor(String matricula_professor, int disciplina_id) throws ITQException {
        try {
            return gatewayQuiz.listQuizByDisciplinaByProfessor(matricula_professor, disciplina_id);
        } catch (Exception e) {
            throw new ITQException(e.getMessage());
        }
    }

    public RestMessage saveQuiz(Quiz q) throws ITQException {
        try {
            boolean sucesso = gatewayQuiz.saveQuiz(q);
            RestMessage message = new RestMessage();
            if (sucesso) {
                message.setText("Quiz incluído com sucesso");
                message.setType(RestMessageType.SUCCESS);
            } else {
                message.setText("Erro ao incluir Quiz, contate o administrador do sistema");
                message.setType(RestMessageType.ERROR);
            }
            return message;
        } catch (Exception e) {
            throw new ITQException(e.getMessage());
        }
    }

    public RestMessage updateQuiz(Quiz q) throws ITQException {
        try {
            boolean sucesso = gatewayQuiz.updateQuiz(q);
            RestMessage message = new RestMessage();
            if (sucesso) {
                message.setText("Quiz alterado com sucesso");
                message.setType(RestMessageType.SUCCESS);
            } else {
                message.setText("Erro ao alterar Quiz, contate o administrador do sistema");
                message.setType(RestMessageType.ERROR);
            }
            return message;
        } catch (Exception e) {
            throw new ITQException(e.getMessage());
        }
    }

    public List<Questao> listQuestoesByQuiz(int quiz_id) throws ITQException {
        try {
            return gatewayQuiz.listQuestoesByQuiz(quiz_id);
        } catch (Exception e) {
            throw new ITQException(e.getMessage());
        }
    }

    public RestMessage publicarQuiz(Publicacao tq) throws ITQException {
        try {

            RestMessage message = new RestMessage();
            List<Publicacao> publicados = listQuizEmAndamentoByTurma(tq.getTurma().getId());

            if (publicados.size() > 0) {
                message.setText("Esta turma já possui um quiz em andamento");
                message.setType(RestMessageType.WARNING);
            } else {
                tq.setStatus(StatusTurmaQuiz.PUBLICADO);
                boolean sucesso = gatewayQuiz.publicarQuiz(tq);
                if (sucesso) {
                    message.setText("Quiz publicado com sucesso");
                    message.setType(RestMessageType.SUCCESS);
                } else {
                    message.setText("Erro ao publicar Quiz, contate o administrador do sistema");
                    message.setType(RestMessageType.ERROR);
                }
            }

            return message;
        } catch (Exception e) {
            throw new ITQException(e.getMessage());
        }
    }

    public List<Publicacao> listQuizEmAndamentoByTurma(int id) throws ITQException {
        try {
            return gatewayQuiz.listQuizEmAndamentoByTurma(id);
        } catch (Exception e) {
            throw new ITQException(e.getMessage());
        }
    }

    public List<Publicacao> listQuizPublicadoByStatusByTurma(int id, StatusTurmaQuiz status) throws ITQException {
        try {
            return gatewayQuiz.listQuizPublicadoByStatusByTurma(id, status);
        } catch (Exception e) {
            throw new ITQException(e.getMessage());
        }
    }
}
