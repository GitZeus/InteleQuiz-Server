package servico;

import entidade.Resposta;
import entidade.Tema;
import entidade.Treino;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistencia.GatewayTema;
import persistencia.GatewayTreino;
import util.ITQException;
import util.InteleQuizUtil;
import util.RestMessage;
import util.RestMessageType;

@Service
public class ServiceTema {

    @Autowired
    private GatewayTema gatewayTema;

    @Autowired
    private GatewayTreino gatewayTreino;

    public List<Tema> listTemasByDisciplinaByProfessor(String matricula_professor, Integer disciplina_id) throws ITQException {
        try {
            return gatewayTema.listTemasByDisciplinaByProfessor(matricula_professor, disciplina_id);
        } catch (Exception e) {
            throw new ITQException(e.getMessage());
        }
    }

    public RestMessage saveTema(Tema t) throws ITQException {
        try {
            boolean sucesso = gatewayTema.saveTema(t);
            RestMessage message = new RestMessage();
            if (sucesso) {
                message.setText("Tema incluído com sucesso");
                message.setType(RestMessageType.SUCCESS);
            } else {
                message.setText("Erro ao incluir Tema, contate o administrador do sistema");
                message.setType(RestMessageType.ERROR);
            }
            return message;
        } catch (Exception e) {
            throw new ITQException(e.getMessage());
        }
    }

    public List<Tema> listTemaByQuestao(Integer id) throws ITQException {
        try {
            List<Tema> temas = gatewayTema.listTemaByQuestao(id);
            return temas;
        } catch (Exception e) {
            throw new ITQException(e.getMessage());
        }
    }

    public Tema getTemaAtencaoByQuizPublicado(Integer id) throws ITQException {
        try {
            List<Treino> treinos = gatewayTreino.listTreinoByQuizPublicado(id);
            HashMap hmTemas = new HashMap();

            for (Treino treino : treinos) {
                for (Resposta resposta : treino.getRespostas()) {
                    List<Tema> temas = listTemaByQuestao(resposta.getQuestao().getId());
                    for (Tema tema : temas) {
                        if (!(hmTemas.containsKey(tema.getId()))) {
                            tema.setTotal(1);
                            if (resposta.getCerta() == false) {
                                tema.setErrados(1);
                            }
                            hmTemas.put(tema.getId(), tema);
                        } else {
                            Tema auxTema = (Tema) hmTemas.get(tema.getId());
                            auxTema.setTotal(auxTema.getTotal() + 1);
                            if (resposta.getCerta() == false) {
                                auxTema.setErrados(auxTema.getErrados() + 1);
                            }
                            hmTemas.put(auxTema.getId(), auxTema);
                        }
                    }
                }
            }

            Tema temaAtencao = null;
            for (Object obj : hmTemas.values()) {
                Tema auxTema = (Tema) obj;
                auxTema.setPercentErros(InteleQuizUtil.formatDecimal((double) (auxTema.getErrados() * 100) / auxTema.getTotal()));
                if (temaAtencao == null || auxTema.getPercentErros() > temaAtencao.getPercentErros()) {
                    temaAtencao = auxTema;
                }
            }

            return temaAtencao;
        } catch (Exception e) {
            throw new ITQException(e.getMessage());
        }
    }
}
