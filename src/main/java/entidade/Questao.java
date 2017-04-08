package entidade;

import enums.TipoQuestao;
import enums.StatusQuizQuestao;
import enums.NivelQuestao;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TB_QUESTAO")
public class Questao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToMany
    private List<Tema> temas;
    
    @OneToMany
    private List<Resposta> respostas;

    @Enumerated
    private TipoQuestao tipo;

    @Enumerated
    private NivelQuestao nivel;

    @Enumerated
    private StatusQuizQuestao status;

    private String texto;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TipoQuestao getTipo() {
        return tipo;
    }

    public void setTipo(TipoQuestao tipo) {
        this.tipo = tipo;
    }

    public NivelQuestao getNivel() {
        return nivel;
    }

    public void setNivel(NivelQuestao nivel) {
        this.nivel = nivel;
    }

    public StatusQuizQuestao getStatus() {
        return status;
    }

    public void setStatus(StatusQuizQuestao status) {
        this.status = status;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public List<Tema> getTemas() {
        return temas;
    }

    public void setTemas(List<Tema> temas) {
        this.temas = temas;
    }

    public List<Resposta> getRespostas() {
        return respostas;
    }

    public void setRespostas(List<Resposta> respostas) {
        this.respostas = respostas;
    }
}
