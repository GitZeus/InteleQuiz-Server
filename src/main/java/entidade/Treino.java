package entidade;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "TB_TREINO")
public class Treino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "ALUNO_RA")
    private Aluno aluno;

    @ManyToOne
    @JoinColumn(name = "PUBLICACAO_ID")
    private Publicacao publicacao;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name = "TS_INICIO")
    private Date tsInicio;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "TS_FIM")
    private Date tsFim;

    private Double pontuacao;
    private Double aproveitamento;

    @OneToMany
    @JoinTable(name = "rel_treino_resposta",
            joinColumns = {
                @JoinColumn(name = "treino_id")},
            inverseJoinColumns = {
                @JoinColumn(name = "resposta_id")})
    private List<Resposta> respostas;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Date getTsInicio() {
        return tsInicio;
    }

    public void setTsInicio(Date tsInicio) {
        this.tsInicio = tsInicio;
    }

    public Date getTsFim() {
        return tsFim;
    }

    public void setTsFim(Date tsFim) {
        this.tsFim = tsFim;
    }

    public Double getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(Double pontuacao) {
        this.pontuacao = pontuacao;
    }

    public Double getAproveitamento() {
        return aproveitamento;
    }

    public void setAproveitamento(Double aproveitamento) {
        this.aproveitamento = aproveitamento;
    }

    public List<Resposta> getRespostas() {
        return respostas;
    }

    public void setRespostas(List<Resposta> respostas) {
        this.respostas = respostas;
    }

    @Override
    public String toString() {
        return "Treino{" + "id=" + id + ", aluno=" + aluno + ", publicacao=" + publicacao + ", tsInicio=" + tsInicio + ", tsFim=" + tsFim + ", pontuacao=" + pontuacao + ", aproveitamento=" + aproveitamento + ", respostas=" + respostas + '}';
    }

    public Publicacao getPublicacao() {
        return publicacao;
    }

    public void setPublicacao(Publicacao publicacao) {
        this.publicacao = publicacao;
    }
}
