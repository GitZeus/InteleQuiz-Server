package entidade;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import enums.TipoQuestao;
import enums.StatusQuizQuestao;
import enums.NivelQuestao;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tb_questao")
public class Questao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToMany
    @JoinTable(name = "rel_questao_tema",
            joinColumns = {
                @JoinColumn(name = "questao_id")},
            inverseJoinColumns = {
                @JoinColumn(name = "tema_id")})
    private List<Tema> temas;

    @OneToMany(mappedBy = "questao", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Resposta> respostas;

    @Enumerated
    private TipoQuestao tipo;

    @Enumerated
    private NivelQuestao nivel;

    @Enumerated
    private StatusQuizQuestao status;

    @Transient
    private int countTotal;

    @Transient
    private int countErros;

    private String texto;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    @Override
    public String toString() {
        return "Questao{" + "id=" + id + ", tipo=" + tipo + ", nivel=" + nivel + ", status=" + status + ", texto=" + texto + '}';
    }

    public int getCountTotal() {
        return countTotal;
    }

    public void setCountTotal(int countTotal) {
        this.countTotal = countTotal;
    }

    public int getCountErros() {
        return countErros;
    }

    public void setCountErros(int countErros) {
        this.countErros = countErros;
    }
}
