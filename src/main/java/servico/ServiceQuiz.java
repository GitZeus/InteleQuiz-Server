package servico;

import entidade.Quiz;
import entidade.Treino;
import java.io.IOError;
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

    public RestMessage saveQuiz(Quiz quiz) throws ITQException {
        validarQuiz(quiz);
        try {
            int id = gatewayQuiz.saveQuiz(quiz);
            RestMessage message = new RestMessage();
            if (id != 0) {
                message.setText("Quiz incluído com sucesso");
                message.setType(RestMessageType.SUCCESS);
            } else {
                message.setText("Erro ao incluir Quiz, contate o administrador do sistema");
                message.setType(RestMessageType.ERROR);
            }
            return message;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ITQException("Erro ao incluir Quiz, contate o administrador do sistema");
        }
    }

    public RestMessage updateQuiz(Quiz quiz) throws ITQException {
        validarQuiz(quiz);
        try {
            boolean sucesso = gatewayQuiz.updateQuiz(quiz);
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
            e.printStackTrace();
            throw new ITQException("Erro ao alterar Quiz, contate o administrador do sistema");
        }
    }

    private void validarQuiz(Quiz quiz) throws ITQException {
        if (quiz.getDescricao() == null || quiz.getDescricao().length() == 0) {
            throw new ITQException("Informe uma descrição para o quiz");
        }
        if (quiz.getDisciplina() == null || quiz.getDisciplina().getId() == 0) {
            throw new ITQException("Informe uma disciplina para o quiz");
        }
        if (quiz.getQuestoes() == null || quiz.getQuestoes().isEmpty()) {
            throw new ITQException("Informe ao menos uma questão para o quiz");
        }
        if (quiz.getProfessor() == null || quiz.getProfessor().getMatricula() == null) {
            throw new ITQException("Erro ao recuperar a matricula do professor");
        }
    }

    public List<Quiz> listQuizByDisciplinaByProfessor(String matricula, int id) throws ITQException {
        if (matricula == null || matricula.length() == 0) {
            throw new ITQException("Erro ao recuperar a matricula do professor");
        }
        if (id <= 0) {
            throw new ITQException("Id de disciplina inválido");
        }
        try {
            List<Quiz> quizzes = gatewayQuiz.listQuizByDisciplinaByProfessor(matricula, id);
            return quizzes;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ITQException("Erro ao listar quiz por disciplina e professor");
        }
    }

    public Quiz getQuizByTreino(Treino treino) throws ITQException {
        if (treino == null) {
            throw new ITQException("Informe um treino válido para recuperar o quiz");
        }
        try {
            Quiz quiz = gatewayQuiz.getQuizByTreino(treino);
            return quiz;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ITQException("Erro ao recuperar quiz pelo treino");
        }
    }
}
