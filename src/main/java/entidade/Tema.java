package entidade;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "TB_TEMA")
public class Tema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    private Professor professor;

    @OneToOne
    private Disciplina disciplina;

    private String nome;

    @ManyToMany
    @JoinTable(name = "rel_questao_tema",
            joinColumns = {
                @JoinColumn(name = "tema_id")},
            inverseJoinColumns = {
                @JoinColumn(name = "questao_id")})
    private List<Questao> questoes;

    @JsonIgnore
    @Transient
    private int total;

    @JsonIgnore
    @Transient
    private int errados;

    @Transient
    private double percentErros;

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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Tema{" + "id=" + id + ", professor=" + professor + ", disciplina=" + disciplina + ", + nome=" + nome + '}';
    }

    public List<Questao> getQuestoes() {
        return questoes;
    }

    public void setQuestoes(List<Questao> questoes) {
        this.questoes = questoes;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getErrados() {
        return errados;
    }

    public void setErrados(int errados) {
        this.errados = errados;
    }

    public double getPercentErros() {
        return percentErros;
    }

    public void setPercentErros(double percentErros) {
        this.percentErros = percentErros;
    }
}
