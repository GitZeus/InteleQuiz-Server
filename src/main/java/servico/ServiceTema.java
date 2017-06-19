package servico;

import entidade.Tema;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistencia.GatewayTema;
import util.ITQException;
import util.InteleQuizUtil;
import util.RestMessage;
import util.RestMessageType;

@Service
public class ServiceTema {

    @Autowired
    private GatewayTema gatewayTema;

    public RestMessage saveTema(Tema tema) throws ITQException {
        try {
            if (tema.getNome() == null || tema.getNome().trim().length() == 0) {
                throw new ITQException("Informe um nome para o tema");
            }
            if (gatewayTema.getTemaByNome(tema.getNome()).size() > 0) {
                throw new ITQException("Já existe um tema com este nome");
            }
            int id = gatewayTema.saveTema(tema);
            RestMessage message = new RestMessage();
            if (id != 0) {
                message.setText("Tema incluído com sucesso");
                message.setType(RestMessageType.SUCCESS);
            } else {
                throw new Exception();
            }
            return message;
        } catch (ITQException i) {
            throw new ITQException(i.getMessage());
        } catch (Exception e) {
            InteleQuizUtil.printExceptionLog(e);
            throw new ITQException("Erro não previsto ao incluir tema");
        }
    }

    public Tema getTemaById(int id) throws ITQException {
        try {
            if (id <= 0) {
                throw new ITQException("Informe um id para o tema");
            }
            Tema tema = gatewayTema.getTemaById(id);
            return tema;
        } catch (ITQException i) {
            throw new ITQException(i.getMessage());
        } catch (Exception e) {
            InteleQuizUtil.printExceptionLog(e);
            throw new ITQException("Erro não previsto ao recuperar tema");
        }
    }

    public List<Tema> listTemasByDisciplinaByProfessor(String matricula, int id) throws ITQException {
        try {
            return gatewayTema.listTemasByDisciplinaByProfessor(matricula, id);
        } catch (Exception e) {
            InteleQuizUtil.printExceptionLog(e);
            throw new ITQException("Erro não previsto ao listar temas por disciplina e professor");
        }
    }

    public List<Tema> listTemaByQuestao(int id) throws ITQException {
        try {
            if (id <= 0) {
                throw new ITQException("Informe um id válido para a questão");
            }
            List<Tema> temas = gatewayTema.listTemaByQuestao(id);
            return temas;
        } catch (ITQException i) {
            throw new ITQException(i.getMessage());
        } catch (Exception e) {
            InteleQuizUtil.printExceptionLog(e);
            throw new ITQException("Erro não previsto ao listar temas por questão");
        }
    }
}
