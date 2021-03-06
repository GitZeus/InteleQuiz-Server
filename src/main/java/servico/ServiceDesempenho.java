package servico;

import entidade.Aluno;
import util.DesempenhoVO;
import entidade.Gabarito;
import entidade.Treino;
import entidade.Publicacao;
import entidade.Questao;
import entidade.Resposta;
import entidade.Tema;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.ITQException;
import util.InteleQuizUtil;

@Service
public class ServiceDesempenho {

    @Autowired
    private ServicePublicacao servicePublicacao;

    @Autowired
    private ServiceTreino serviceTreino;

    @Autowired
    private ServiceTema serviceTema;

    @Autowired
    ServiceAluno serviceAluno;

    @Autowired
    ServiceQuestao serviceQuestao;

    public DesempenhoVO getDesempenhoByTurmaByProfessor(int id) throws ITQException {
        DesempenhoVO desempenho = new DesempenhoVO();
        try {

            List<Aluno> alunos = serviceAluno.listAlunoByTurma(id);
            List<Publicacao> publicacoes = servicePublicacao.listPublicacaoByTurma(id);
            desempenho.setPublicacoes(publicacoes);

            double auxAproveitamento = 0;
            double auxMedAproveitamento = 0;
            double auxMedEnvolvimento = 0;

            for (Publicacao publicacao : publicacoes) {
                List<Treino> treinos = serviceTreino.listTreinoByPublicacao(publicacao.getId());

                for (Treino treino : treinos) {
                    auxAproveitamento += treino.getAproveitamento();
                }
                desempenho.getEncerramentos().add(InteleQuizUtil.formatData(publicacao.getTsEncerramento()));
                if (treinos.size() > 0) {
                    double aprov = InteleQuizUtil.formatDecimal(auxAproveitamento / treinos.size());

                    double envolv = InteleQuizUtil.formatDecimal((double) (treinos.size() * 100) / alunos.size());

                    desempenho.getAproveitamentosTurma().add(aprov);
                    desempenho.getEnvolvimentosTurma().add(envolv);

                    auxMedAproveitamento += aprov;
                    auxMedEnvolvimento += envolv;
                } else {
                    desempenho.getAproveitamentosTurma().add(InteleQuizUtil.formatDecimal(0d));
                    desempenho.getEnvolvimentosTurma().add(InteleQuizUtil.formatDecimal(0d));
                }
                auxAproveitamento = 0;
            }
            if (publicacoes.size() > 0) {
                desempenho.setMedAproveitamentoTurma(InteleQuizUtil.formatDecimal(auxMedAproveitamento / publicacoes.size()));
                desempenho.setMedEnvolvimentoTurma(InteleQuizUtil.formatDecimal(auxMedEnvolvimento / publicacoes.size()));
            }

            return desempenho;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ITQException("Erro ao obter dados de desempenho por turma e professor");
        }
    }

    public DesempenhoVO getDesempenhoByTurmaByAluno(int id, String ra) throws ITQException {
        DesempenhoVO desempenho = getDesempenhoByTurmaByProfessor(id);
        try {
            List<Treino> treinos = serviceTreino.listTreinoByTurmaByAluno(id, ra);

            for (Treino treino : treinos) {
                desempenho.somaMedAproveitamentoAluno(treino.getAproveitamento());
            }
            if (treinos.size() > 0) {
                desempenho.setMedAproveitamentoAluno(InteleQuizUtil.formatDecimal(desempenho.getMedAproveitamentoAluno() / treinos.size()));
            } else {
                desempenho.setMedAproveitamentoAluno(InteleQuizUtil.formatDecimal(0d));
            }

            for (Publicacao publicacao : desempenho.getPublicacoes()) {
                boolean treinoMatch = false;
                for (Treino treino : treinos) {
                    if (treino.getPublicacao().getId() == publicacao.getId()) {
                        treinoMatch = true;
                        desempenho.getAproveitamentosAluno().add(InteleQuizUtil.formatDecimal(treino.getAproveitamento()));
                        break;
                    }
                }
                if (!treinoMatch) {
                    desempenho.getAproveitamentosAluno().add(InteleQuizUtil.formatDecimal(0d));
                }
            }
            return desempenho;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ITQException("Erro ao obter dados de desempenho por turma e aluno");
        }
    }

    public DesempenhoVO getTemaCriticoByPublicacao(int id) throws ITQException {
        try {
            List<Treino> treinos = serviceTreino.listTreinoByPublicacao(id);
            HashMap hmTemas = new HashMap();

            for (Treino treino : treinos) {
                for (Gabarito gabarito : treino.getGabaritos()) {
                    if(gabarito.isVisualizada()){
                        Resposta resposta = null;
                        if (gabarito.getResposta_id() > 0) {
                            resposta = serviceQuestao.getRespostaById(gabarito.getResposta_id());
                        }
                        List<Tema> temas = serviceTema.listTemaByQuestao(gabarito.getQuestao_id());
                        for (Tema tema : temas) {
                            if (!(hmTemas.containsKey(tema.getId()))) {
                                tema.setTotal(1);
                                if (gabarito.isVisualizada() && resposta == null || resposta.getCerta() == false) {
                                    tema.setErrados(1);
                                }
                                hmTemas.put(tema.getId(), tema);
                            } else {
                                Tema auxTema = (Tema) hmTemas.get(tema.getId());
                                auxTema.setTotal(auxTema.getTotal() + 1);
                                if (gabarito.isVisualizada() && resposta == null || resposta.getCerta() == false) {
                                    auxTema.setErrados(auxTema.getErrados() + 1);
                                }
                                hmTemas.put(auxTema.getId(), auxTema);
                            }
                        }
                    }
                }
            }

            DesempenhoVO desempenho = new DesempenhoVO();
            for (Object obj : hmTemas.values()) {
                Tema tema = (Tema) obj;
                if (tema.getErrados() > 0) {
                    tema.setPercentErros(InteleQuizUtil.formatDecimal((double) (tema.getErrados() * 100) / tema.getTotal()));
                    desempenho.getTemasCriticos().add(tema);
                }
            }

            return desempenho;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ITQException("Erro ao obter dados de tema crítico por publicacao");
        }
    }

    public DesempenhoVO getTemaCriticoByPublicacaoByAluno(int id, String ra) throws ITQException {
        try {
            List<Treino> treinos = serviceTreino.listTreinoByPublicacaoByAluno(id, ra);
            HashMap hmTemas = new HashMap();

            for (Treino treino : treinos) {
                for (Gabarito gabarito : treino.getGabaritos()) {
                    Resposta resposta = null;
                    if (gabarito.getResposta_id() > 0) {
                        resposta = serviceQuestao.getRespostaById(gabarito.getResposta_id());
                    }
                    List<Tema> temas = serviceTema.listTemaByQuestao(gabarito.getQuestao_id());
                    for (Tema tema : temas) {
                        if (!(hmTemas.containsKey(tema.getId()))) {
                            tema.setTotal(1);
                            if (gabarito.isVisualizada() && resposta == null || resposta.getCerta() == false) {
                                tema.setErrados(1);
                            }
                            hmTemas.put(tema.getId(), tema);
                        } else {
                            Tema auxTema = (Tema) hmTemas.get(tema.getId());
                            auxTema.setTotal(auxTema.getTotal() + 1);
                            if (gabarito.isVisualizada() && resposta == null || resposta.getCerta() == false) {
                                auxTema.setErrados(auxTema.getErrados() + 1);
                            }
                            hmTemas.put(auxTema.getId(), auxTema);
                        }
                    }
                }
            }

            DesempenhoVO desempenho = new DesempenhoVO();
            for (Object obj : hmTemas.values()) {
                Tema tema = (Tema) obj;
                if (tema.getErrados() > 0) {
                    tema.setPercentErros(InteleQuizUtil.formatDecimal((double) (tema.getErrados() * 100) / tema.getTotal()));
                    desempenho.getTemasCriticos().add(tema);
                }
            }

            return desempenho;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ITQException("Erro ao obter dados de tema crítico por publicacao e aluno");
        }
    }

    public DesempenhoVO listQuestoesCriticasByPublicacaoByTemaCritico(int publicacao_id, int tema_id) throws ITQException {
        try {
            DesempenhoVO desempenho = new DesempenhoVO();
            List<Treino> treinos = serviceTreino.listTreinoByPublicacao(publicacao_id);
            HashMap hmQuestoes = new HashMap();

            for (Treino treino : treinos) {
                for (Gabarito gabarito : treino.getGabaritos()) {
                    if (gabarito.isVisualizada()) {
                        List<Tema> temas = serviceTema.listTemaByQuestao(gabarito.getQuestao_id());
                        for (Tema tema : temas) {
                            if (tema.getId() == tema_id) {
                                Resposta resposta = null;
                                if (gabarito.getResposta_id() > 0) {
                                    resposta = serviceQuestao.getRespostaById(gabarito.getResposta_id());
                                }
                                if (resposta != null && resposta.getCerta() == false) {
                                    Questao q = verificaErrosQuestao(gabarito, hmQuestoes);
                                    hmQuestoes.put(q.getId(), q);
                                }
                            }
                        }
                    }
                }
            }

            Comparator<Questao> comparator = new Comparator<Questao>() {
                @Override
                public int compare(Questao q1, Questao q2) {
                    if (q1.getCountErros() > q2.getCountErros()) {
                        return -1;
                    } else if (q1.getCountErros() < q2.getCountErros()) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
            };

            List<Questao> questoes = new ArrayList<>(hmQuestoes.values());
            Collections.sort(questoes, comparator);
            desempenho.setQuestoesCriticas(questoes);

            return desempenho;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ITQException("Erro ao listar questões críticas por publicação e tema crítico");
        }
    }

    public DesempenhoVO listQuestoesCriticasByPublicacaoByAlunoByTemaCritico(int publicacao_id, String ra, int tema_id) throws ITQException {
        try {
            DesempenhoVO desempenho = new DesempenhoVO();
            List<Treino> treinos = serviceTreino.listTreinoByPublicacaoByAluno(publicacao_id, ra);
            HashMap hmQuestoes = new HashMap();

            for (Treino treino : treinos) {
                for (Gabarito gabarito : treino.getGabaritos()) {
                    if (gabarito.isVisualizada()) {
                        List<Tema> temas = serviceTema.listTemaByQuestao(gabarito.getQuestao_id());
                        for (Tema tema : temas) {
                            if (tema.getId() == tema_id && serviceQuestao.getRespostaById(gabarito.getResposta_id()).getCerta() == false) {
                                Resposta resposta = null;
                                if (gabarito.getResposta_id() > 0) {
                                    resposta = serviceQuestao.getRespostaById(gabarito.getResposta_id());
                                }
                                if (resposta != null && resposta.getCerta() == false) {
                                    Questao q = verificaErrosQuestao(gabarito, hmQuestoes);
                                    hmQuestoes.put(q.getId(), q);
                                }
                            }
                        }
                    }
                }
            }

            Comparator<Questao> comparator = new Comparator<Questao>() {
                @Override
                public int compare(Questao q1, Questao q2) {
                    if (q1.getCountErros() > q2.getCountErros()) {
                        return -1;
                    } else if (q1.getCountErros() < q2.getCountErros()) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
            };

            List<Questao> questoes = new ArrayList<>(hmQuestoes.values());
            Collections.sort(questoes, comparator);
            desempenho.setQuestoesCriticas(questoes);

            return desempenho;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ITQException("Erro ao listar questões críticas por publicação, aluno e tema crítico");
        }
    }

    private Questao verificaErrosQuestao(Gabarito gabarito, HashMap hmQuestoes) throws ITQException {
        Questao questao = serviceQuestao.getQuestaoById(gabarito.getQuestao_id());
        if (!(hmQuestoes.containsKey(questao.getId()))) {
            questao.setCountErros(1);
        } else {
            questao = (Questao) hmQuestoes.get(questao.getId());
            questao.setCountErros(questao.getCountErros() + 1);
        }
        return questao;
    }
}
