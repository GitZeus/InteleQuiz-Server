package servico;

import entidade.Tema;
import entidade.Turma;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistencia.GatewayTreino;
import util.ITQException;
import util.RestMessage;
import util.RestMessageType;

@Service
public class ServiceTreino {

    @Autowired
    private GatewayTreino gatewayTreino;

    public List<Turma> listTurmasByAluno(String ra) throws ITQException {
        try {
            return gatewayTreino.listTurmasByAluno(ra);
        } catch (Exception e) {
            throw new ITQException(e.getMessage());
        }
    }

    public RestMessage saveTema(Tema t) throws ITQException {
        try {
            boolean sucesso = gatewayTreino.saveTema(t);
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
}
