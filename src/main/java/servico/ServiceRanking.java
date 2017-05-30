package servico;

import entidade.Aluno;
import entidade.Treino;
import entidade.Turma;
import entidade.Publicacao;
import enums.StatusTurmaQuiz;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.ITQException;

@Service
public class ServiceRanking {

    @Autowired
    private ServiceTurma serviceTurma;

    @Autowired
    private ServiceTreino serviceTreino;

    @Autowired
    private ServiceAluno serviceAluno;

    @Autowired
    private ServicePublicacao servicePublicacao;

    public List<Turma> getRankingTurmaByAluno(String ra) throws ITQException {
        try {
            List<Turma> turmas = serviceTurma.listTurmaByAluno(ra);

            for (Turma turma : turmas) {
                List<Publicacao> publicacoes = servicePublicacao.listPublicacaoByStatusByTurma(turma.getId(), StatusTurmaQuiz.ENCERRADO);

                double pontuacao = 0;
                double aproveitamento = 0;
                int qtdTreinos = 0;

                for (Publicacao publicacao : publicacoes) {
                    List<Treino> treinos = serviceTreino.listTreinoByPublicacao(publicacao.getId());

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

    public List<Turma> getRankingTurmaByProfessor(String matricula) throws ITQException {
        try {
            List<Turma> turmas = serviceTurma.listTurmaByProfessor(matricula);

            for (Turma turma : turmas) {
                List<Publicacao> publicacoes = servicePublicacao.listPublicacaoByStatusByTurma(turma.getId(), StatusTurmaQuiz.ENCERRADO);

                double pontuacao = 0;
                double aproveitamento = 0;
                int qtdTreinos = 0;

                for (Publicacao publicacao : publicacoes) {
                    List<Treino> treinos = serviceTreino.listTreinoByPublicacao(publicacao.getId());

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

    public List<Aluno> getRankingAlunoByTurma(int id) throws ITQException {
        try {
            List<Aluno> alunos = serviceAluno.listAlunoByTurma(id);

            for (Aluno aluno : alunos) {
                List<Treino> treinos = serviceTreino.listTreinoByTurmaByAluno(id, aluno.getRa());

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
