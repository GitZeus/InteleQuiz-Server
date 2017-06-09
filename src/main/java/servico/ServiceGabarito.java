package servico;

import entidade.Aluno;
import entidade.Gabarito;
import entidade.Publicacao;
import entidade.Questao;
import entidade.Quiz;
import entidade.Treino;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistencia.GatewayGabarito;
import persistencia.GatewayTreino;
import util.ITQException;

@Service
public class ServiceGabarito {

    @Autowired
    private GatewayTreino gatewayTreino;

    @Autowired
    private ServiceAluno serviceAluno;

    @Autowired
    private ServicePublicacao servicePublicacao;

    @Autowired
    private ServiceQuestao serviceQuestao;

    @Autowired
    private ServiceQuiz serviceQuiz;

    @Autowired
    GatewayGabarito gatewayGabarito;

    public Gabarito saveGabarito(Gabarito gabarito) throws ITQException {
        try {
            if (gabarito.getGabaritoPK() == null) {
                throw new ITQException("Falha ao recuperar o registro acadêmico do aluno e o id do treino");
            }
            int idNovoGabarito = gatewayGabarito.saveGabarito(gabarito);
            Gabarito g = gatewayGabarito.getGabaritoById(idNovoGabarito);
            return g;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ITQException("Erro ao salvar gabarito do treino, contate o administrador do sistema");
        }
    }
}
