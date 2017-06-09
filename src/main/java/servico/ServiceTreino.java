package servico;

import entidade.Aluno;
import entidade.Gabarito;
import entidade.Publicacao;
import entidade.Questao;
import entidade.Quiz;
import entidade.Treino;
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

            for (Questao questao : questoes) {
                Gabarito gabarito = new Gabarito(questao.getId(), idNovoTreino);
                serviceGabarito.saveGabarito(gabarito);
            }
            treino = gatewayTreino.getTreinoById(idNovoTreino);
            return treino;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ITQException("Erro ao incluir um novo treino");
        }
    }

    public Treino updateTreino(Treino treino) throws ITQException {
        if (treino.getAluno() == null) {
            throw new ITQException("Erro ao recuperar o aluno para o treino");
        }
        if (treino.getPublicacao() == null) {
            throw new ITQException("Erro ao recuperar a publicação do quiz");
        }
//        if (treino.getRespostas() == null || treino.getRespostas().size() == 0) {
//            throw new ITQException("Informe ao menos uma resposta para atualizar o treino");
//        }
        try {
//            Resposta ultimaRespostaAdicionada = treino.getRespostas().get(treino.getRespostas().size() - 1);
//            if (ultimaRespostaAdicionada.getCerta() == true) {
//                Questao q = serviceQuestao.getQuestaoByResposta(ultimaRespostaAdicionada);
//                double valor = 0;
//                if (null != q.getNivel()) {
//                    switch (q.getNivel()) {
//                        case FACIL:
//                            valor = 1;
//                            break;
//                        case MEDIO:
//                            valor = 2;
//                            break;
//                        case DIFICIL:
//                            valor = 3;
//                            break;
//                        default:
//                            break;
//                    }
//                }
//                if (treino.getPontuacao() == null) {
//                    treino.setPontuacao(valor);
//                } else {
//                    treino.setPontuacao(treino.getPontuacao() + valor);
//                }
//            }

            Quiz quiz = serviceQuiz.getQuizByTreino(treino);
//            if (treino.getRespostas().size() == quiz.getQuestoes().size()) {
//                treino.setTsFim(new Date());
//                double valorDoQuiz = 0;
//                for (Questao q : quiz.getQuestoes()) {
//                    if (q.getNivel() == NivelQuestao.FACIL) {
//                        valorDoQuiz += 1;
//                    } else if (q.getNivel() == NivelQuestao.MEDIO) {
//                        valorDoQuiz += 2;
//                    } else if (q.getNivel() == NivelQuestao.DIFICIL) {
//                        valorDoQuiz += 3;
//                    }
//                }
//                double aproveitamento = (treino.getPontuacao() * 100) / valorDoQuiz;
//                treino.setAproveitamento(aproveitamento);
//            }

            return gatewayTreino.updateTreino(treino);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ITQException("Erro ao atualizar o treino");
        }
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
}
