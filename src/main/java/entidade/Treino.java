package entidade;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "tb_treino")
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

    private double pontuacao;
    private double aproveitamento;

    @OneToMany(mappedBy = "treino_id")
    private List<Gabarito> gabaritos;

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

    public double getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(double pontuacao) {
        this.pontuacao = pontuacao;
    }

    public double getAproveitamento() {
        return aproveitamento;
    }

    public void setAproveitamento(double aproveitamento) {
        this.aproveitamento = aproveitamento;
    }

    public Publicacao getPublicacao() {
        return publicacao;
    }

    public void setPublicacao(Publicacao publicacao) {
        this.publicacao = publicacao;
    }

    public List<Gabarito> getGabaritos() {
        return gabaritos;
    }

    public void setGabaritos(List<Gabarito> gabaritos) {
        this.gabaritos = gabaritos;
    }
}
