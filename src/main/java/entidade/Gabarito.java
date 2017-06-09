package entidade;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "TB_GABARITO")
public class Gabarito {

    public Gabarito() {
    }

    public Gabarito(int questao_id, int treino_id) {
        this.gabaritoPK = new GabaritoPK(questao_id, treino_id);
    }

    @EmbeddedId
    private GabaritoPK gabaritoPK;

    private int resposta_id;

    private boolean visualizada;

    @Temporal(TemporalType.TIMESTAMP)
    private Date ts_resoista;

    public int getResposta_id() {
        return resposta_id;
    }

    public void setResposta_id(int resposta_id) {
        this.resposta_id = resposta_id;
    }

    public boolean isVisualizada() {
        return visualizada;
    }

    public void setVisualizada(boolean visualizada) {
        this.visualizada = visualizada;
    }

    public Date getTs_resoista() {
        return ts_resoista;
    }

    public void setTs_resoista(Date ts_resoista) {
        this.ts_resoista = ts_resoista;
    }

    public GabaritoPK getGabaritoPK() {
        return gabaritoPK;
    }

    public void setGabaritoPK(GabaritoPK gabaritoPK) {
        this.gabaritoPK = gabaritoPK;
    }

    @Embeddable
    public class GabaritoPK implements Serializable {

        private int questao_id;
        private int treino_id;

        public GabaritoPK() {
        }

        public GabaritoPK(int questao_id, int treino_id) {
            this.questao_id = questao_id;
            this.treino_id = treino_id;
        }

        public int getTreino_id() {
            return treino_id;
        }

        public void setTreino_id(int treino_id) {
            this.treino_id = treino_id;
        }

        public int getQuestao_id() {
            return questao_id;
        }

        public void setQuestao_id(int questao_id) {
            this.questao_id = questao_id;
        }
    }
}
