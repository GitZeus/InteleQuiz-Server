package entidade;

import entidade.Gabarito.GabaritoPK;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@IdClass(GabaritoPK.class)
@Table(name = "tb_gabarito")
public class Gabarito implements Serializable {
    
    public Gabarito(){}
    
    public Gabarito(int questao_id, int treino_id){
        this.questao_id = questao_id;
        this.treino_id = treino_id;
    }

    @Id
    private Integer questao_id;

    @Id
    private Integer treino_id;

    private int resposta_id;

    private boolean visualizada;

    @Temporal(TemporalType.TIMESTAMP)
    private Date ts_resposta;

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
        return ts_resposta;
    }

    public void setTs_resoista(Date ts_resposta) {
        this.ts_resposta = ts_resposta;
    }

    public Integer getQuestao_id() {
        return questao_id;
    }

    public void setQuestao_id(Integer questao_id) {
        this.questao_id = questao_id;
    }

    public Integer getTreino_id() {
        return treino_id;
    }

    public void setTreino_id(Integer treino_id) {
        this.treino_id = treino_id;
    }

    public Date getTs_resposta() {
        return ts_resposta;
    }

    public void setTs_resposta(Date ts_resposta) {
        this.ts_resposta = ts_resposta;
    }

    public static class GabaritoPK implements Serializable {

        private Integer questao_id;

        private Integer treino_id;

        public GabaritoPK() {
        }

        public GabaritoPK(Integer questao_id, Integer treino_id) {
            this.questao_id = questao_id;
            this.treino_id = treino_id;
        }

        public Integer getTreino_id() {
            return treino_id;
        }

        public void setTreino_id(Integer treino_id) {
            this.treino_id = treino_id;
        }

        public Integer getQuestao_id() {
            return questao_id;
        }

        public void setQuestao_id(Integer questao_id) {
            this.questao_id = questao_id;
        }

        @Override
        public int hashCode() {
            return (int) questao_id.hashCode() + treino_id.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final GabaritoPK other = (GabaritoPK) obj;
            if (this.treino_id != other.treino_id) {
                return false;
            }
            if (!Objects.equals(this.questao_id, other.questao_id)) {
                return false;
            }
            return true;
        }
    }

}
