package servico;

import entidade.Questao;
import entidade.Quiz;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistencia.GatewayQuestionario;
import util.ITQException;
import util.RestMessage;
import util.RestMessageType;

@Service
public class ServiceQuestionario {
    
    @Autowired
    private GatewayQuestionario gatewayQuestionario;
    
    public List<Questao> listQuestoesByQuestionario(Integer questionario_id) throws ITQException {
        try {
            return gatewayQuestionario.listQuestoesByQuestionario(questionario_id);
        } catch (Exception e) {
            throw new ITQException(e.getMessage());
        }
    }
       
    public RestMessage saveQuestionario(Quiz q) throws ITQException{
        try {
            boolean sucesso = gatewayQuestionario.saveQuestionario(q);
            RestMessage message = new RestMessage();
            if(sucesso){
                message.setText("Questionario incluído com sucesso");
                message.setType(RestMessageType.SUCCESS);
            }else{
                message.setText("Erro ao incluir Questionario, contate o administrador do sistema");
                message.setType(RestMessageType.ERROR);
            }
            return message;
        } catch (Exception e) {
            throw new ITQException(e.getMessage());
        }
    }
    
    public RestMessage updateQuestionario(Quiz q) throws ITQException{
        try {
            boolean sucesso = gatewayQuestionario.updateQuestionario(q);
            RestMessage message = new RestMessage();
            if(sucesso){
                message.setText("Questionario alterado com sucesso");
                message.setType(RestMessageType.SUCCESS);
            }else{
                message.setText("Erro ao alterar Questionario, contate o administrador do sistema");
                message.setType(RestMessageType.ERROR);
            }
            return message;
        } catch (Exception e) {
            throw new ITQException(e.getMessage());
        }
    }
}