package entidade;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TB_PROFESSOR")
public class Professor extends Usuario {

    @Id
    private String matricula;
    private String nome;

    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "rel_professor_disciplina",
            joinColumns = {
                @JoinColumn(name = "professor_matricula")},
            inverseJoinColumns = {
                @JoinColumn(name = "disciplina_id")})
    private List<Disciplina> disciplinas;

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    @Override
    public String toString() {
        return "Professor{" + "matricula=" + matricula + ", nome=" + nome + ", disciplinas=" + disciplinas + '}';
    }
}
