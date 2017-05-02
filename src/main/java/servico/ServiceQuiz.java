package servico;

import entidade.Turma;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistencia.GatewayQuiz;
import util.ITQException;

@Service
public class ServiceQuiz {

    @Autowired
    private GatewayQuiz gatewayQuiz;

    public List<Turma> listTurmasByProfessor(String matricula) throws ITQException {
        try {
            return gatewayQuiz.listTurmasByProfessor(matricula);
        } catch (Exception e) {
            throw new ITQException(e.getMessage());
        }
    }
}
