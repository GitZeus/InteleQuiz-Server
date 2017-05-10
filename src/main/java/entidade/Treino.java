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
@Table(name = "TB_TREINO")
public class Treino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Aluno aluno;
    
    @ManyToOne
    @JoinColumn(name = "TURMA_QUIZ_ID", referencedColumnName = "ID")
    private TurmaQuiz turmaQuiz;
    
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
    private List<Resposta> respostas;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public TurmaQuiz getTurmaQuiz() {
        return turmaQuiz;
    }

    public void setTurmaQuiz(TurmaQuiz turmaQuiz) {
        this.turmaQuiz = turmaQuiz;
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
        return "Treino{" + "id=" + id + ", aluno=" + aluno + ", turmaQuiz=" + turmaQuiz + ", tsInicio=" + tsInicio + ", tsFim=" + tsFim + ", pontuacao=" + pontuacao + ", aproveitamento=" + aproveitamento + ", respostas=" + respostas + '}';
    }
}