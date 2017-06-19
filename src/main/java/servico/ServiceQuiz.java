package servico;

import entidade.Questao;
import entidade.Quiz;
import entidade.Treino;
import enums.StatusQuizQuestao;
import java.io.IOError;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistencia.GatewayQuiz;
import util.ITQException;
import util.InteleQuizUtil;
import util.RestMessage;
import util.RestMessageType;

@Service
public class ServiceQuiz {

    @Autowired
    private GatewayQuiz gatewayQuiz;

    @Autowired
    private ServiceQuestao serviceQuestao;

    public RestMessage saveQuiz(Quiz quiz) throws ITQException {
        validarQuiz(quiz);
        try {

            int id = gatewayQuiz.saveQuiz(quiz);
            RestMessage message = new RestMessage();
            if (id != 0) {
                for (Questao questao : quiz.getQuestoes()) {
                    questao.setStatus(StatusQuizQuestao.VINCULADO);
                    serviceQuestao.updateQuestao(questao);
                }
                message.setText("Quiz incluído com sucesso");
                message.setType(RestMessageType.SUCCESS);
            } else {
                throw new Exception("Erro não previsto ao incluir quiz");
            }
            return message;
        } catch (ITQException i) {
            throw new ITQException(i.getMessage());
        } catch (Exception e) {
            InteleQuizUtil.printExceptionLog(e);
            throw new ITQException("Erro não previsto ao incluir quiz");
        }
    }

    public RestMessage updateQuiz(Quiz quiz) throws ITQException {
        validarQuiz(quiz);
        try {
            Quiz quizOld = gatewayQuiz.getQuizById(quiz.getId());
            if (quizOld.getStatus() == StatusQuizQuestao.VINCULADO) {
                throw new ITQException("Quiz vinculado a um treino não pode ser alterado");
            }
            boolean sucesso = gatewayQuiz.updateQuiz(quiz);
            RestMessage message = new RestMessage();
            if (sucesso) {
                message.setText("Quiz alterado com sucesso");
                message.setType(RestMessageType.SUCCESS);
            } else {
                throw new Exception("Erro não previsto ao alterar quiz");
            }
            return message;
        } catch (ITQException i) {
            throw new ITQException(i.getMessage());
        } catch (Exception e) {
            InteleQuizUtil.printExceptionLog(e);
            throw new ITQException("Erro não previsto ao alterar quiz");
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
            throw new ITQException("Informe um id para a disciplina");
        }
        try {
            List<Quiz> quizzes = gatewayQuiz.listQuizByDisciplinaByProfessor(matricula, id);
            return quizzes;
        } catch (Exception e) {
            InteleQuizUtil.printExceptionLog(e);
            throw new ITQException("Erro não previsto ao listar quiz por disciplina e professor");
        }
    }

    public Quiz getQuizByTreino(Treino treino) throws ITQException {
        if (treino == null || treino.getId() <= 0) {
            throw new ITQException("Informe um treino para recuperar o quiz");
        }
        try {
            Quiz quiz = gatewayQuiz.getQuizByTreino(treino);
            return quiz;
        } catch (Exception e) {
            InteleQuizUtil.printExceptionLog(e);
            throw new ITQException("Erro não previsto ao recuperar quiz pelo treino");
        }
    }

    public Quiz getQuizById(int id) throws ITQException {
        if (id <= 0) {
            throw new ITQException("Informe um id para o quiz");
        }
        try {
            Quiz quiz = gatewayQuiz.getQuizById(id);
            return quiz;
        } catch (Exception e) {
            InteleQuizUtil.printExceptionLog(e);
            throw new ITQException("Erro não previsto ao recuperar quiz pelo id");
        }
    }

}
