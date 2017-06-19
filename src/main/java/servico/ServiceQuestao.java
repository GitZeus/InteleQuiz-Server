package servico;

import entidade.Gabarito;
import entidade.Questao;
import entidade.Resposta;
import entidade.Treino;
import enums.TipoNivelQuestao;
import enums.StatusQuizQuestao;
import enums.TipoQuestao;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistencia.GatewayQuestao;
import util.ITQException;
import util.InteleQuizUtil;
import util.RestMessage;
import util.RestMessageType;

@Service
public class ServiceQuestao {

    @Autowired
    private GatewayQuestao gatewayQuestao;

    @Autowired
    private ServiceTreino serviceTreino;

    @Autowired
    private ServiceGabarito serviceGabarito;

    public TipoQuestao[] listTipoQuestao() throws ITQException {
        try {
            return TipoQuestao.values();
        } catch (Exception e) {
            InteleQuizUtil.printExceptionLog(e);
            throw new ITQException("Erro não previsto ao listar tipos de questão");
        }
    }

    public TipoNivelQuestao[] listNivelQuestao() throws ITQException {
        try {
            return TipoNivelQuestao.values();
        } catch (Exception e) {
            InteleQuizUtil.printExceptionLog(e);
            throw new ITQException("Erro não previsto ao listar níveis de questão");
        }
    }

    public StatusQuizQuestao[] listStatusQuizQuestao() throws ITQException {
        try {
            return StatusQuizQuestao.values();
        } catch (Exception e) {
            InteleQuizUtil.printExceptionLog(e);
            throw new ITQException("Erro não previsto ao listar status quiz/questão");
        }
    }

    public RestMessage saveQuestao(Questao questao) throws ITQException {
        try {
            validarQuestao(questao);
            if (questao.getId() > 0) {
                throw new ITQException("Erro tentativa de incluir questão já existente");
            }
            questao.setStatus(StatusQuizQuestao.CADASTRADO);
            int id = gatewayQuestao.saveQuestao(questao);
            RestMessage message = new RestMessage();
            if (id != 0) {
                message.setText("Questão incluída com sucesso");
                message.setType(RestMessageType.SUCCESS);
            } else {
                message.setText("Erro ao incluir Questão, contate o administrador do sistema");
                message.setType(RestMessageType.ERROR);
            }
            return message;
        } catch (ITQException i) {
            throw new ITQException(i.getMessage());
        } catch (Exception e) {
            InteleQuizUtil.printExceptionLog(e);
            throw new ITQException("Erro não previsto ao incluir questão");
        }
    }

    public RestMessage updateQuestao(Questao questao) throws ITQException {
        try {
            validarQuestao(questao);
            Questao questaoOld = gatewayQuestao.getQuestaoById(questao.getId());
            if (questaoOld.getStatus() == StatusQuizQuestao.VINCULADO) {
                throw new ITQException("Questão vinculada a um quiz não pode ser alterada");
            }
            boolean sucesso = gatewayQuestao.updateQuestao(questao);
            RestMessage message = new RestMessage();
            if (sucesso) {
                message.setText("Questão alterada com sucesso");
                message.setType(RestMessageType.SUCCESS);
            } else {
                throw new Exception("Erro não previsto ao alterar questão");
            }
            return message;
        } catch (ITQException i) {
            throw new ITQException(i.getMessage());
        } catch (Exception e) {
            InteleQuizUtil.printExceptionLog(e);
            throw new ITQException("Erro não previsto ao alterar questão");
        }
    }

    private void validarQuestao(Questao questao) throws ITQException {
        if (questao.getTexto() == null || questao.getTexto().length() == 0) {
            throw new ITQException("Informe um texto para a questão");
        }
        if (questao.getRespostas() == null || questao.getRespostas().size() == 0) {
            throw new ITQException("Informe respostas para a questão");
        }
        if (questao.getTipo() == null) {
            throw new ITQException("Informe um tipo para a questão");
        }
        if (questao.getNivel() == null) {
            throw new ITQException("Informe um nível para a questão");
        }
        if (questao.getTemas() == null || questao.getTemas().size() == 0) {
            throw new ITQException("Informe ao menos um tema para a questão");
        }
    }

    public List<Questao> listQuestaoByQuiz(int id) throws ITQException {
        try {
            if (id <= 0) {
                throw new ITQException("Informe um id para o quiz");
            }
            return gatewayQuestao.listQuestaoByQuiz(id);
        } catch (ITQException i) {
            throw new ITQException(i.getMessage());
        } catch (Exception e) {
            InteleQuizUtil.printExceptionLog(e);
            throw new ITQException("Erro não previsto ao listar questões por quiz");
        }
    }

    public List<Questao> listQuestaoByTema(int id) throws ITQException {
        try {
            if (id <= 0) {
                throw new ITQException("Informe um id para o tema");
            }
            return gatewayQuestao.listQuestaoByTema(id);
        } catch (ITQException i) {
            throw new ITQException(i.getMessage());
        } catch (Exception e) {
            InteleQuizUtil.printExceptionLog(e);
            throw new ITQException("Erro não previsto ao listar questões por tema");
        }
    }

    public Questao getQuestaoByResposta(Resposta resposta) throws ITQException {
        try {
            if (resposta == null) {
                throw new ITQException("Informe uma resposta válida para recuperar a questão");
            }
            if (resposta.getId() <= 0) {
                throw new ITQException("Informe uma id para a questão");
            }
            Questao questao = gatewayQuestao.getQuestaoByResposta(resposta);
            return questao;
        } catch (ITQException i) {
            throw new ITQException(i.getMessage());
        } catch (Exception e) {
            InteleQuizUtil.printExceptionLog(e);
            throw new ITQException("Erro não previsto ao recuperar questão por resposta");
        }
    }

    public Resposta getRespostaById(int id) throws ITQException {
        try {
            if (id <= 0) {
                throw new ITQException("Informe um id para a resposta");
            }
            Resposta resposta = gatewayQuestao.getRespostaById(id);
            return resposta;
        } catch (ITQException i) {
            throw new ITQException(i.getMessage());
        } catch (Exception e) {
            InteleQuizUtil.printExceptionLog(e);
            throw new ITQException("Erro não previsto ao recuperar resposta por id");
        }
    }

    public List<Questao> listQuestaoContinuacaoByTreino(int id) throws ITQException {
        if (id <= 0) {
            throw new ITQException("Informe um id válido para o treino");
        }
        try {
            Treino treino = serviceTreino.getTreinoById(id);
//            Publicacao publicacao = servicePublicacao.getPublicacaoById(id);
            List<Questao> questoes = listQuestaoByQuiz(treino.getPublicacao().getQuiz().getId());

            List<Questao> questoesContinuacao = new ArrayList<>();
            List<Gabarito> gabaritos = serviceTreino.listGabaritoByTreino(id);
            Gabarito gabaritoVisualizado = null;
            for (Gabarito gabarito : gabaritos) {
                if (gabarito.isVisualizada() == false) {
                    if (gabaritoVisualizado == null) {
                        gabaritoVisualizado = gabarito;
                    }
                    for (Questao questao : questoes) {
                        if (gabarito.getQuestao_id() == questao.getId()) {
                            questoesContinuacao.add(questao);
                        }
                    }
                }
            }

            if (gabaritoVisualizado != null) {
                gabaritoVisualizado.setVisualizada(true);
                serviceGabarito.updateGabarito(gabaritoVisualizado);
            }

            return questoesContinuacao;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ITQException("Erro ao listar questpes nao respondidas do treino");
        }
    }

    public Questao getQuestaoById(int id) throws ITQException {
        try {
            if (id <= 0) {
                throw new ITQException("Informe um id para a questão");
            }
            Questao questao = gatewayQuestao.getQuestaoById(id);
            return questao;
        } catch (ITQException i) {
            throw new ITQException(i.getMessage());
        } catch (Exception e) {
            InteleQuizUtil.printExceptionLog(e);
            throw new ITQException("Erro não previsto ao recuperar questão por id");
        }
    }
}
