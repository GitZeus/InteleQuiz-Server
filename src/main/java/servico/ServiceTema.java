package servico;

import entidade.Tema;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistencia.GatewayTema;
import util.GlobalException;
import util.ITQException;
import util.RestMessage;
import util.RestMessageType;

@Service
public class ServiceTema {
    
    @Autowired
    private GatewayTema gatewayTema;
    
    public List<Tema> listTemasByDisciplinaByProfessor(String matricula_professor, Integer disciplina_id) throws GlobalException {
        try {
            return gatewayTema.listTemasByDisciplinaByProfessor(matricula_professor, disciplina_id);
        } catch (Exception e) {
            throw new GlobalException(e);
        }
    }
       
    public RestMessage saveTema(Tema t) throws ITQException{
        try {
            boolean sucesso = gatewayTema.saveTema(t);
            RestMessage message = new RestMessage();
            if(sucesso){
                message.setText("Tema incluído com sucesso");
                message.setType(RestMessageType.SUCCESS);
            }else{
                message.setText("Erro ao incluir Tema, contate o administrador do sistema");
                message.setType(RestMessageType.ERROR);
            }
            return message;
        } catch (Exception e) {
            throw new ITQException(e.getMessage());
        }
    }
}