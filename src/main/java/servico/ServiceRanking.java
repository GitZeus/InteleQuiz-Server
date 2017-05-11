package servico;

import entidade.Aluno;
import entidade.Treino;
import entidade.Turma;
import entidade.TurmaQuiz;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistencia.GatewayRanking;
import persistencia.GatewayTreino;
import util.ITQException;

@Service
public class ServiceRanking {

    @Autowired
    private GatewayRanking gatewayRanking;

    @Autowired
    private GatewayTreino gatewayTreino;

    public List<Turma> listRankingTurmasByAluno(String ra) throws ITQException {
        try {
            List<Turma> turmas = gatewayTreino.listTurmasByAluno(ra);

            for (Turma turma : turmas) {
                List<TurmaQuiz> publicacoes = gatewayRanking.listQuizPublicadoByTurma(turma.getId());

                double pontuacao = 0;
                double aproveitamento = 0;
                int qtdTreinos = 0;

                for (TurmaQuiz publicacao : publicacoes) {
                    List<Treino> treinos = gatewayTreino.listTreinoByPublicacao(publicacao.getId());

                    for (Treino treino : treinos) {
                        qtdTreinos++;
                        pontuacao += treino.getPontuacao();
                        aproveitamento += treino.getAproveitamento();
                    }
                }
                turma.setPontuacao(pontuacao);
                if(qtdTreinos > 0){
                    turma.setAproveitamento(aproveitamento / qtdTreinos);
                }else{
                    turma.setAproveitamento(0d);
                }
            }

            return turmas;
        } catch (Exception e) {
            throw new ITQException(e.getMessage());
        }
    }
}
