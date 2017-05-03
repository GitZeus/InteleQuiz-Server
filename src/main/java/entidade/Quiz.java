package entidade;

import enums.StatusQuizQuestao;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TB_QUIZ")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @OneToOne
    private Professor professor;
    
    @OneToOne
    private Disciplina disciplina;
    
    @OneToMany
    @JoinTable(name = "rel_questao_quiz",
            joinColumns = {
                @JoinColumn(name = "quiz_id")},
            inverseJoinColumns = {
                @JoinColumn(name = "questao_id")})
    private List<Questao> questoes;
    
    private String descricao;
    
    @Enumerated
    private StatusQuizQuestao status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public StatusQuizQuestao getStatus() {
        return status;
    }

    public void setStatus(StatusQuizQuestao status) {
        this.status = status;
    }

    public List<Questao> getQuestoes() {
        return questoes;
    }

    public void setQuestoes(List<Questao> questoes) {
        this.questoes = questoes;
    }

    @Override
    public String toString() {
        return "Quiz{" + "id=" + id + ", professor=" + professor + ", disciplina=" + disciplina + ", questoes=" + questoes + ", descricao=" + descricao + ", status=" + status + '}';
    }
}