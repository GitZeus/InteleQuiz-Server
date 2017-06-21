package entidade;

import enums.StatusPublicacao;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "tb_publicacao")
public class Publicacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

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

    @Enumerated(EnumType.ORDINAL)
    private StatusPublicacao status;

    @OneToMany(mappedBy = "publicacao")
    private List<Treino> treinos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public StatusPublicacao getStatus() {
        return status;
    }

    public void setStatus(StatusPublicacao status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Publicacao{" + "id=" + id + ", turma=" + turma + ", quiz=" + quiz + ", tsPublicacao=" + tsPublicacao + ", tsEncerramento=" + tsEncerramento + ", status=" + status + '}';
    }

    public List<Treino> getTreinos() {
        return treinos;
    }

    public void setTreinos(List<Treino> treinos) {
        this.treinos = treinos;
    }
}
