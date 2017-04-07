package entidades;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TB_TREINO")
public class Treino {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Aluno aluno;
    private TurmaQuiz turmaQuiz;
    private Date tsInicio;
    private Date tsFim;
    private Double pontuacao;
    private Double aproveitamento;
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
}