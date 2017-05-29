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

    public List<Tema> listTemasByDisciplinaByProfessor(String matricula_professor, int disciplina_id) throws ITQException {
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

    public List<Tema> listTemaByQuestao(int id) throws ITQException {
        try {
            List<Tema> temas = gatewayTema.listTemaByQuestao(id);
            return temas;
        } catch (Exception e) {
            throw new ITQException(e.getMessage());
        }
    }   
}