package servico;

import entidade.Aluno;
import entidade.Desempenho;
import entidade.Treino;
import entidade.Turma;
import entidade.TurmaQuiz;
import enums.StatusTurmaQuiz;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistencia.GatewayDesempenho;
import util.ITQException;
import util.InteleQuizUtil;

@Service
public class ServiceDesempenho {

    @Autowired
    private GatewayDesempenho gatewayDesempenho;

    @Autowired
    private ServiceQuiz serviceQuiz;

    @Autowired
    private ServiceTreino serviceTreino;

    @Autowired
    ServiceAluno serviceAluno;

    public Desempenho getDesempenhoByTurma(Integer id) throws ITQException {
        Desempenho desempenho = new Desempenho();
        try {

            List<Aluno> alunos = serviceAluno.listAlunoByTurma(id);
            List<TurmaQuiz> publicacoes = serviceQuiz.listQuizPublicadoByStatusByTurma(id, StatusTurmaQuiz.ENCERRADO);

            double auxAproveitamento = 0;
            double auxEnvolvimento = 0;

            for (TurmaQuiz publicacao : publicacoes) {
                List<Treino> treinos = serviceTreino.listTreinoByPublicacao(publicacao.getId());

                for (Treino treino : treinos) {
                    auxAproveitamento += treino.getAproveitamento();
                }

                desempenho.getEncerramentos().add(InteleQuizUtil.formataData(publicacao.getTsEncerramento()));
                if (treinos.size() > 0) {
                    desempenho.getAproveitamentos().add(auxAproveitamento / treinos.size());
                    desempenho.getEnvolvimentos().add((double) (treinos.size() * 100) / alunos.size());
                } else {
                    desempenho.getAproveitamentos().add(0d);
                }
                auxAproveitamento = 0;
            }

            return desempenho;
        } catch (Exception e) {
            throw new ITQException(e.getMessage());
        }
    }
}
