package entidade;

import enums.TipoTurnoTurma;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_turma")
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    private Professor professor;

    @OneToOne
    private Disciplina disciplina;

    @Enumerated
    private TipoTurnoTurma turno;

    @OneToMany(mappedBy = "turma")
    private List<Publicacao> quizzes;

    @ManyToMany
    @JoinTable(name = "rel_turma_aluno",
            joinColumns = {
                @JoinColumn(name = "turma_id")},
            inverseJoinColumns = {
                @JoinColumn(name = "aluno_ra")})
    private List<Aluno> alunos;

    private int ano;

    private int semestre;

    private String letra;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public TipoTurnoTurma getTurno() {
        return turno;
    }

    public void setTurno(TipoTurnoTurma turno) {
        this.turno = turno;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public List<Publicacao> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(List<Publicacao> quizzes) {
        this.quizzes = quizzes;
    }
}
