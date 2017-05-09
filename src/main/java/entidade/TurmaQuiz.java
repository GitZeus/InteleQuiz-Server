package entidade;

import enums.StatusTurmaQuiz;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "TB_TURMA_QUIZ")
public class TurmaQuiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Turma turma;

    @ManyToOne
    private Quiz quiz;
    
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "TS_PUBLICACAO")
    private Date tsPublicacao;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "TS_ENCERRAMENTO")
    private Date tsEncerramento;

    @Enumerated
    private StatusTurmaQuiz status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public Date getTsPublicacao() {
        return tsPublicacao;
    }

    public void setTsPublicacao(Date tsPublicacao) {
        this.tsPublicacao = tsPublicacao;
    }

    public Date getTsEncerramento() {
        return tsEncerramento;
    }

    public void setTsEncerramento(Date tsEncerramento) {
        this.tsEncerramento = tsEncerramento;
    }

    public StatusTurmaQuiz getStatus() {
        return status;
    }

    public void setStatus(StatusTurmaQuiz status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TurmaQuiz{" + "id=" + id + ", turma=" + turma + ", quiz=" + quiz + ", tsPublicacao=" + tsPublicacao + ", tsEncerramento=" + tsEncerramento + ", status=" + status + '}';
    }
}
