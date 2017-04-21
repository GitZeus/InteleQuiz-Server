package servico;

import entidade.Tema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistencia.GatewayTema;
import util.ITQException;
import util.RestMessage;
import util.RestMessageType;

@Service
public class ServiceTema {
    
    @Autowired
    private GatewayTema gatewayTema;
       
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