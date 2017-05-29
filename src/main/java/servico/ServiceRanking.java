package servico;

import entidade.Aluno;
import entidade.Treino;
import entidade.Turma;
import entidade.Publicacao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistencia.GatewayQuiz;
import persistencia.GatewayRanking;
import persistencia.GatewayTreino;
import util.ITQException;

@Service
public class ServiceRanking {

    @Autowired
    private GatewayRanking gatewayRanking;

    @Autowired
    private GatewayTreino gatewayTreino;
    
    @Autowired
    private GatewayQuiz gatewayQuiz;

    public List<Turma> listRankingTurmasByAluno(String ra) throws ITQException {
        try {
            List<Turma> turmas = gatewayTreino.listTurmasByAluno(ra);

            for (Turma turma : turmas) {
                List<Publicacao> publicacoes = gatewayRanking.listQuizPublicadoByTurma(turma.getId());

                double pontuacao = 0;
                double aproveitamento = 0;
                int qtdTreinos = 0;

                for (Publicacao publicacao : publicacoes) {
                    List<Treino> treinos = gatewayTreino.listTreinoByPublicacao(publicacao.getId());

                    for (Treino treino : treinos) {
                        qtdTreinos++;
                        pontuacao += treino.getPontuacao();
                        aproveitamento += treino.getAproveitamento();
                    }
                }
                turma.setPontuacao(pontuacao);
                if (qtdTreinos > 0) {
                    turma.setAproveitamento(aproveitamento / qtdTreinos);
                } else {
                    turma.setAproveitamento(0d);
                }
            }

            return turmas;
        } catch (Exception e) {
            throw new ITQException(e.getMessage());
        }
    }
    
    public List<Turma> listRankingTurmasByProfessor(String matricula) throws ITQException {
        try {
            List<Turma> turmas = gatewayQuiz.listTurmasByProfessor(matricula);

            for (Turma turma : turmas) {
                List<Publicacao> publicacoes = gatewayRanking.listQuizPublicadoByTurma(turma.getId());

                double pontuacao = 0;
                double aproveitamento = 0;
                int qtdTreinos = 0;

                for (Publicacao publicacao : publicacoes) {
                    List<Treino> treinos = gatewayTreino.listTreinoByPublicacao(publicacao.getId());

                    for (Treino treino : treinos) {
                        qtdTreinos++;
                        pontuacao += treino.getPontuacao();
                        aproveitamento += treino.getAproveitamento();
                    }
                }
                turma.setPontuacao(pontuacao);
                if (qtdTreinos > 0) {
                    turma.setAproveitamento(aproveitamento / qtdTreinos);
                } else {
                    turma.setAproveitamento(0d);
                }
            }

            return turmas;
        } catch (Exception e) {
            throw new ITQException(e.getMessage());
        }
    }

    public List<Aluno> listRankingAlunosByTurma(int id) throws ITQException {
        try {
            List<Aluno> alunos = gatewayRanking.listAlunosByTurma(id);

            for (Aluno aluno : alunos) {
                List<Treino> treinos = gatewayRanking.listTreinoByAlunoByTurma(aluno.getRa(), id);

                double pontuacao = 0;
                double aproveitamento = 0;
                int qtdTreinos = 0;

                for (Treino treino : treinos) {
                    qtdTreinos++;
                    pontuacao += treino.getPontuacao();
                    aproveitamento += treino.getAproveitamento();
                }

                aluno.setPontuacao(pontuacao);
                if (qtdTreinos > 0) {
                    aluno.setAproveitamento(aproveitamento / qtdTreinos);
                } else {
                    aluno.setAproveitamento(0d);
                }
            }

            return alunos;
        } catch (Exception e) {
            throw new ITQException(e.getMessage());
        }
    }
}
