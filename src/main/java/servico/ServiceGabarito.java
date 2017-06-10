package servico;

import entidade.Gabarito;
import entidade.Gabarito.GabaritoPK;
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
            GabaritoPK pk = gatewayGabarito.saveGabarito(gabarito);
            Gabarito g = gatewayGabarito.getGabaritoById(pk);
            return g;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ITQException("Erro ao salvar gabarito do treino, contate o administrador do sistema");
        }
    }
    
    public Gabarito updateGabarito (Gabarito gabarito) throws ITQException {
        try {
            gatewayGabarito.updateGabarito(gabarito);
            GabaritoPK pk = new GabaritoPK(gabarito.getQuestao_id(), gabarito.getTreino_id());
            Gabarito g = gatewayGabarito.getGabaritoById(pk);
            return g;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ITQException("Erro ao salvar gabarito do treino, contate o administrador do sistema");
        }
    }
}
