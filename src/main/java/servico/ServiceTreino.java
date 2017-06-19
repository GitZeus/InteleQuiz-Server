package servico;

import entidade.Aluno;
import entidade.Gabarito;
import entidade.Publicacao;
import entidade.Questao;
import entidade.Quiz;
import entidade.Resposta;
import entidade.Treino;
import enums.StatusQuizQuestao;
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

    @Autowired
    private ServiceAluno serviceAluno;

    @Autowired
    private ServicePublicacao servicePublicacao;

    @Autowired
    private ServiceQuestao serviceQuestao;

    @Autowired
    private ServiceQuiz serviceQuiz;

    @Autowired
    private ServiceGabarito serviceGabarito;

    public Treino saveTreino(String ra, int id) throws ITQException {
        if (ra == null || ra.length() == 0) {
            throw new ITQException("Erro ao recuperar o registro acadêmico do aluno");
        }
        if (id <= 0) {
            throw new ITQException("Informe um id válido para a publicação");
        }
        try {
            Aluno aluno = serviceAluno.getAlunoByRa(ra);
            Publicacao publicacao = servicePublicacao.getPublicacaoById(id);
            List<Questao> questoes = serviceQuestao.listQuestaoByQuiz(publicacao.getQuiz().getId());
            Treino treino = new Treino();
            treino.setAluno(aluno);
            treino.setPublicacao(publicacao);

            int idNovoTreino = gatewayTreino.saveTreino(treino);

            for (int i = 0; i < questoes.size(); i++) {
                Gabarito gabarito = new Gabarito(questoes.get(i).getId(), idNovoTreino);
                if (i == 0) {
                    gabarito.setVisualizada(true);
                }
                serviceGabarito.saveGabarito(gabarito);
            }
            treino = gatewayTreino.getTreinoById(idNovoTreino);
            Quiz quiz = serviceQuiz.getQuizById(publicacao.getQuiz().getId());
            if (quiz.getStatus() == StatusQuizQuestao.CADASTRADO) {
                quiz.setStatus(StatusQuizQuestao.VINCULADO);
                serviceQuiz.updateQuiz(quiz);
            }
            return treino;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ITQException("Erro ao incluir um novo treino");
        }
    }

    public Treino updateTreino(Gabarito gabaritoAux) throws ITQException {
        try {
            Gabarito gabarito = gatewayTreino.getGabaritoByID(gabaritoAux);
            gabarito.setResposta_id(gabaritoAux.getResposta_id());

            Treino treino = getTreinoById(gabarito.getTreino_id());
            Quiz quiz = serviceQuiz.getQuizByTreino(treino);
            treino.setPontuacao(treino.getPontuacao() + recuperarPontuacaoResposta(gabarito));
            treino.setAproveitamento(calcularAproveitamentoTreino(treino, quiz));

            Date currentTimestamp = new Date();

            gabarito.setTs_resposta(currentTimestamp);
            serviceGabarito.updateGabarito(gabarito);

            Gabarito nextGabarito = gatewayTreino.getNextGabarito(gabarito);
            if (nextGabarito != null) {
                nextGabarito.setVisualizada(true);
                serviceGabarito.updateGabarito(nextGabarito);
            }
            
            if (gatewayTreino.getQtdRespostasTreino(treino) == quiz.getQuestoes().size()) {
                treino.setTsFim(currentTimestamp);
            }
            treino = gatewayTreino.updateTreino(treino);

            return treino;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ITQException("Erro ao atualizar o treino");
        }
    }

    private int recuperarPontuacaoResposta(Gabarito gabarito) throws ITQException {
        int pontuacao = 0;
        Resposta resposta = serviceQuestao.getRespostaById(gabarito.getResposta_id());
        if (resposta.getCerta() == true) {
            Questao q = serviceQuestao.getQuestaoByResposta(resposta);
            pontuacao = q.getNivel().getPonto();
        }
        return pontuacao;
    }

    private double calcularAproveitamentoTreino(Treino treino, Quiz quiz) throws ITQException {
        double aproveitamento = 0;
        double valorDoQuiz = 0;
        for (Questao q : quiz.getQuestoes()) {
            valorDoQuiz += q.getNivel().getPonto();
        }
        aproveitamento = (treino.getPontuacao() * 100) / valorDoQuiz;
        return aproveitamento;
    }

    public Treino getTreinoById(int id) throws ITQException {
        if (id <= 0) {
            throw new ITQException("Informe um id válido para o treino");
        }
        try {
            Treino treino = gatewayTreino.getTreinoById(id);
            return treino;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ITQException("Erro ao recuperar treino por id");
        }
    }

    public List<Treino> listTreinoByPublicacao(int id) throws ITQException {
        if (id <= 0) {
            throw new ITQException("Informe um id válido para a publicação");
        }
        try {
            List<Treino> treinos = gatewayTreino.listTreinoByPublicacao(id);
            return treinos;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ITQException("Erro ao listar treinos por publicação");
        }
    }

    public List<Treino> listTreinoByTurmaByAluno(int id, String ra) throws ITQException {
        if (id <= 0) {
            throw new ITQException("Informe um id válido para a turma");
        }
        if (ra == null || ra.length() == 0) {
            throw new ITQException("Erro ao recuperar o registro acadêmico do aluno");
        }
        try {
            List<Treino> treinos = gatewayTreino.listTreinoByTurmaByAluno(id, ra);
            return treinos;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ITQException("Erro ao listar treinos por turma e aluno");
        }
    }

    public List<Treino> listTreinoByPublicacaoByAluno(int id, String ra) throws ITQException {
        if (ra == null || ra.length() == 0) {
            throw new ITQException("Erro ao recuperar o registro acadêmico do aluno");
        }
        if (id <= 0) {
            throw new ITQException("Informe um id válido para a publicacao");
        }
        try {
            List<Treino> treinos = gatewayTreino.listTreinoByPublicacaoByAluno(id, ra);
            return treinos;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ITQException("Erro ao listar treinos por publicação e aluno");
        }
    }

    public List<Gabarito> listGabaritoByTreino(int id) throws ITQException {
        if (id <= 0) {
            throw new ITQException("Informe um id válido para o treino");
        }
        try {
            List<Gabarito> gabaritos = gatewayTreino.listGabaritoByTreino(id);
            return gabaritos;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ITQException("Erro ao listar treinos por publicação e aluno");
        }
    }
}
