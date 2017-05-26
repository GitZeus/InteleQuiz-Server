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
            desempenho.setPublicacoes(publicacoes);

            double auxAproveitamento = 0;
            double auxMedAproveitamento = 0;
            double auxMedEnvolvimento = 0;

            for (TurmaQuiz publicacao : publicacoes) {
                List<Treino> treinos = serviceTreino.listTreinoByPublicacao(publicacao.getId());

                for (Treino treino : treinos) {
                    auxAproveitamento += treino.getAproveitamento();
                }
                desempenho.getEncerramentos().add(InteleQuizUtil.formatData(publicacao.getTsEncerramento()));
                if (treinos.size() > 0) {
                    double aprov = InteleQuizUtil.formatDecimal(auxAproveitamento / treinos.size());
                    System.out.println("aprov: " + aprov);

                    double envolv = InteleQuizUtil.formatDecimal((double) (treinos.size() * 100) / alunos.size());
                    System.out.println("envolv: " + envolv);

                    desempenho.getAproveitamentos().add(aprov);
                    desempenho.getEnvolvimentos().add(envolv);

                    auxMedAproveitamento += aprov;
                    auxMedEnvolvimento += envolv;
                } else {
                    System.out.println("0d: " + 0d);
                    desempenho.getAproveitamentos().add(InteleQuizUtil.formatDecimal(0d));
                }
                auxAproveitamento = 0;
            }
            if (publicacoes.size() > 0) {
                desempenho.setMedAproveitamento(InteleQuizUtil.formatDecimal(auxMedAproveitamento / publicacoes.size()));
                System.out.println("auxMedAproveitamento: " + auxMedAproveitamento);
                desempenho.setMedEnvolvimento(InteleQuizUtil.formatDecimal(auxMedEnvolvimento / publicacoes.size()));
                System.out.println("auxMedEnvolvimento: " + auxMedEnvolvimento);
            }

            return desempenho;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ITQException(e.getMessage());
        }
    }
}
