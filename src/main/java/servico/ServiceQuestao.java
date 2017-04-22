package servico;

import entidade.Disciplina;
import entidade.Professor;
import entidade.Questao;
import entidade.Tema;
import enums.NivelQuestao;
import enums.StatusQuizQuestao;
import enums.TipoQuestao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistencia.GatewayQuestao;
import util.GlobalException;
import util.ITQException;
import util.RestMessage;
import util.RestMessageType;

@Service
public class ServiceQuestao {
    
    @Autowired
    private GatewayQuestao gatewayQuestao;
    
    public List<Disciplina> listDisciplinasByProfessor(Professor p) throws GlobalException {
        try {
           return gatewayQuestao.listDisciplinasByProfessor(p);
        } catch (Exception e) {
            throw new GlobalException(e);
        }
    }
    
    public List<Questao> listQuestoesByTema(Integer tema_id) throws GlobalException {
        try {
            return gatewayQuestao.listQuestoesByTema(tema_id);
        } catch (Exception e) {
            throw new GlobalException(e);
        }
    }
    
    public List<Tema> listTemasByQuestao(Integer questao_id) throws GlobalException {
        try {
            return gatewayQuestao.listTemasByQuestao(questao_id);
        } catch (Exception e) {
            throw new GlobalException(e);
        }
    }
    
    public TipoQuestao[] listTiposQuestao() throws GlobalException {
        try {
            return TipoQuestao.values();
        } catch (Exception e) {
            throw new GlobalException(e);
        }
    }
    
    public NivelQuestao[] listNiveisQuestao() throws GlobalException {
        try {
            return NivelQuestao.values();
        } catch (Exception e) {
            throw new GlobalException(e);
        }
    }
    
    public StatusQuizQuestao[] listStatusQuizQuestao() throws GlobalException {
        try {
            return StatusQuizQuestao.values();
        } catch (Exception e) {
            throw new GlobalException(e);
        }
    }
    
    public RestMessage saveQuestao(Questao questao) throws ITQException{
        try {
            boolean sucesso = gatewayQuestao.saveQuestao(questao);
            RestMessage message = new RestMessage();
            if(sucesso){
                message.setText("Questão incluída com sucesso");
                message.setType(RestMessageType.SUCCESS);
            }else{
                message.setText("Erro ao incluir Questão, contate o administrador do sistema");
                message.setType(RestMessageType.ERROR);
            }
            return message;
        } catch (Exception e) {
            throw new ITQException(e.getMessage());
        }
    }
    
    public RestMessage updateQuestao(Questao questao) throws ITQException{
        try {
            boolean sucesso = gatewayQuestao.updateQuestao(questao);
            RestMessage message = new RestMessage();
            if(sucesso){
                message.setText("Questão alterada com sucesso");
                message.setType(RestMessageType.SUCCESS);
            }else{
                message.setText("Erro ao alterar Questão, contate o administrador do sistema");
                message.setType(RestMessageType.ERROR);
            }
            return message;
        } catch (Exception e) {
            throw new ITQException(e.getMessage());
        }
    }
}