package servico;

import entidade.Aluno;
import entidade.Questao;
import entidade.Quiz;
import entidade.Resposta;
import entidade.Treino;
import entidade.Turma;
import entidade.TurmaQuiz;
import enums.NivelQuestao;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistencia.GatewayTreino;
import util.ITQException;

@Service
public class ServiceTreino {

    @Autowired
    private GatewayTreino gatewayTreino;

    public List<Turma> listTurmasByAluno(String ra) throws ITQException {
        try {
            return gatewayTreino.listTurmasByAluno(ra);
        } catch (Exception e) {
            throw new ITQException(e.getMessage());
        }
    }

    public Treino startNewTreino(String ra, Integer turma_quiz_id) throws ITQException {
        try {
            return gatewayTreino.startNewTreino(ra, turma_quiz_id);
        } catch (Exception e) {
            throw new ITQException(e.getMessage());
        }
    }

    public Treino updateTreino(Treino treino) throws ITQException {
        try {

            Resposta ultimaRespostaAdicionada = treino.getRespostas().get(treino.getRespostas().size() - 1);
            if (ultimaRespostaAdicionada.getCerta() == true) {
                Questao q = gatewayTreino.getQuestaoByResposta(ultimaRespostaAdicionada);
                System.out.println("NÍVEL: " + q.getNivel());
                double valor = 0;
                if (q.getNivel() == NivelQuestao.FACIL) {
                    valor = 1;
                } else if (q.getNivel() == NivelQuestao.MEDIO) {
                    valor = 2;
                } else if (q.getNivel() == NivelQuestao.DIFICIL) {
                    valor = 3;
                }
                if (treino.getPontuacao() == null) {
                    treino.setPontuacao(valor);
                } else {
                    treino.setPontuacao(treino.getPontuacao() + valor);
                }
            }

            Quiz quiz = gatewayTreino.getQuizByTreino(treino);
            if (treino.getRespostas().size() == quiz.getQuestoes().size()) {
                treino.setTsFim(new Date());
                double valorDoQuiz = 0;
                for (Questao q : quiz.getQuestoes()) {
                    if (q.getNivel() == NivelQuestao.FACIL) {
                        valorDoQuiz += 1;
                    } else if (q.getNivel() == NivelQuestao.MEDIO) {
                        valorDoQuiz += 2;
                    } else if (q.getNivel() == NivelQuestao.DIFICIL) {
                        valorDoQuiz += 3;
                    }
                }
                double aproveitamento = (treino.getPontuacao() * 100) / valorDoQuiz;
                treino.setAproveitamento(aproveitamento);
            }

            return gatewayTreino.updateTreino(treino);
        } catch (Exception e) {
            throw new ITQException(e.getMessage());
        }
    }

    public Treino getTreino(Integer id) throws ITQException {
        try {
            return gatewayTreino.getTreino(id);
        } catch (Exception e) {
            throw new ITQException(e.getMessage());
        }
    }

    public List<Treino> listTreinoByPublicacao(Integer id) throws ITQException {
        try {
            return gatewayTreino.listTreinoByPublicacao(id);
        } catch (Exception e) {
            throw new ITQException(e.getMessage());
        }
    }

}
