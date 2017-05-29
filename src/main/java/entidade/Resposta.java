package entidade;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TB_RESPOSTA")
public class Resposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JsonBackReference
    private Questao questao;
    private String texto;
    private Boolean certa;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Questao getQuestao() {
        return questao;
    }

    public void setQuestao(Questao questao) {
        this.questao = questao;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Boolean getCerta() {
        return certa;
    }


    public void setCerta(Boolean certa) {
        this.certa = certa;
    }

    @Override
    public String toString() {
        return "Resposta{" + "id=" + id + ", texto=" + texto + ", certa=" + certa + '}';
    }
}
