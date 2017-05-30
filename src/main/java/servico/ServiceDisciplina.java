package servico;

import entidade.Disciplina;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistencia.GatewayDisciplina;
import util.ITQException;

@Service
public class ServiceDisciplina {

    @Autowired
    private GatewayDisciplina gatewayDisciplina;

    public List<Disciplina> listDisciplinaByProfessor(String matricula) throws ITQException {
        try {
            if (matricula == null || matricula.length() == 0) {
                throw new ITQException("Informe uma matrícula válida");
            }
            return gatewayDisciplina.listDisciplinaByProfessor(matricula);
        } catch (Exception e) {
            throw new ITQException("Erro ao listar disciplinas por professor");
        }
    }
}
