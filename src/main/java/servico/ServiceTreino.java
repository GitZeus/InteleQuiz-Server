package servico;

import entidade.Aluno;
import entidade.Treino;
import entidade.Turma;
import entidade.TurmaQuiz;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistencia.GatewayTreino;
import util.ITQException;

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

    public Treino startNewTreino(String ra, Integer turma_quiz_id) throws ITQException {
        try {
            return gatewayTreino.startNewTreino(ra, turma_quiz_id);
        } catch (Exception e) {
            throw new ITQException(e.getMessage());
        }
    }
}
