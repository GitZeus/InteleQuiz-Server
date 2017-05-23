package servico;

import entidade.TurmaQuiz;
import enums.StatusTurmaQuiz;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistencia.GatewayDesempenho;
import util.ITQException;

@Service
public class ServiceDesempenho {

    @Autowired
    private GatewayDesempenho gatewayDesempenho;

    public List<TurmaQuiz> listQuizEncerradoByTurma(Integer id, StatusTurmaQuiz status) throws ITQException {
        try {
            return gatewayDesempenho.listQuizEncerradoByTurma(id, status);
        } catch (Exception e) {
            throw new ITQException(e.getMessage());
        }
    }
}
