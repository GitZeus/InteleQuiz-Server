package servico;

import entidade.Tema;
import java.util.List;
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

    public RestMessage saveTema(Tema tema) throws ITQException {
        try {
            if (tema.getDisciplina() == null || tema.getDisciplina().getId() == 0) {
                throw new ITQException("Informe uma disciplina para o tema");
            }
            if (tema.getProfessor() == null || tema.getProfessor().getMatricula() == null) {
                throw new ITQException("Falha ao recuperar a matricula do professor");
            }
            if (tema.getNome() == null || tema.getNome().trim().length() == 0) {
                throw new ITQException("Informe um nome para o tema");
            }
            int id = gatewayTema.saveTema(tema);
            RestMessage message = new RestMessage();
            if (id != 0) {
                message.setText("Tema incluído com sucesso");
                message.setType(RestMessageType.SUCCESS);
            } else {
                message.setText("Erro ao incluir tema, contate o administrador do sistema");
                message.setType(RestMessageType.ERROR);
            }
            return message;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ITQException("Erro ao incluir tema, contate o administrador do sistema");
        }
    }

    public Tema getTemaById(int id) throws ITQException {
        try {
            if (id <= 0) {
                throw new ITQException("Informe um id válido para o tema");
            }
            Tema tema = gatewayTema.getTemaById(id);
            return tema;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ITQException("Erro ao recuperar tema pelo id, contate o administrador do sistema");
        }
    }

    public List<Tema> listTemasByDisciplinaByProfessor(String matricula, int id) throws ITQException {
        try {
            return gatewayTema.listTemasByDisciplinaByProfessor(matricula, id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ITQException("Erro ao listar temas por disciplina e professor");
        }
    }
    
    public List<Tema> listTemaByQuestao(int id) throws ITQException {
        try {
            if (id <= 0) {
                throw new ITQException("Informe um id válido para a questão");
            }
            List<Tema> temas = gatewayTema.listTemaByQuestao(id);
            return temas;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ITQException("Erro ao listar temas por questão");
        }
    }
}
