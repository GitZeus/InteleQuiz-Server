package servico;

import entidade.Questao;
import entidade.Resposta;
import enums.NivelQuestao;
import enums.StatusQuizQuestao;
import enums.TipoQuestao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistencia.GatewayQuestao;
import util.ITQException;
import util.RestMessage;
import util.RestMessageType;

@Service
public class ServiceQuestao {

    @Autowired
    private GatewayQuestao gatewayQuestao;

    public TipoQuestao[] listTipoQuestao() throws ITQException {
        try {
            return TipoQuestao.values();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ITQException("Erro não especificado ao listar tipos de questão");
        }
    }

    public NivelQuestao[] listNivelQuestao() throws ITQException {
        try {
            return NivelQuestao.values();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ITQException("Erro não especificado ao listar níveis de questão");
        }
    }

    public StatusQuizQuestao[] listStatusQuizQuestao() throws ITQException {
        try {
            return StatusQuizQuestao.values();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ITQException("Erro não especificado ao listar status quiz/questão");
        }
    }

    public RestMessage saveQuestao(Questao questao) throws ITQException {
        try {
            validarQuestao(questao);
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
        } catch (Exception e) {
            e.printStackTrace();
            throw new ITQException("Erro ao incluir Questão, contate o administrador do sistema");
        }
    }

    public RestMessage updateQuestao(Questao questao) throws ITQException {
        try {
            validarQuestao(questao);
            boolean sucesso = gatewayQuestao.updateQuestao(questao);
            RestMessage message = new RestMessage();
            if (sucesso) {
                message.setText("Questão alterada com sucesso");
                message.setType(RestMessageType.SUCCESS);
            } else {
                message.setText("Erro ao alterar Questão, contate o administrador do sistema");
                message.setType(RestMessageType.ERROR);
            }
            return message;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ITQException("Erro ao alterar Questão, contate o administrador do sistema");
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
                throw new ITQException("Informe um id valido para o quiz");
            }
            return gatewayQuestao.listQuestaoByQuiz(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ITQException("Erro ao listar questões por quiz");
        }
    }

    public List<Questao> listQuestaoByTema(int id) throws ITQException {
        try {
            return gatewayQuestao.listQuestaoByTema(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ITQException("Erro ao listar questões por tema");
        }
    }

    public Questao getQuestaoByResposta(Resposta resposta) throws ITQException {
        if (resposta == null) {
            throw new ITQException("Informe uma resposta válida para recuperar a questão");
        }
        try {
            Questao questao = gatewayQuestao.getQuestaoByResposta(resposta);
            return questao;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ITQException("Erro ao recuperar questão por resposta");
        }
    }
    
    public Resposta getRespostaById(int id) throws ITQException{
        if(id <= 0){
            throw new ITQException("Informe um id válido para recuperar a resposta");
        }
        try {
            Resposta resposta = gatewayQuestao.getRespostaById(id);
            return resposta;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ITQException("Erro ao recuperar resposta por id");
        }
    }
}
