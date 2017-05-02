package entidade;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TB_ALUNO")
public class Aluno extends Usuario {

    @Id
    private String ra;
    private String nome;

    @ManyToMany
    @JoinTable(name = "rel_turma_aluno",
            joinColumns = {
                @JoinColumn(name = "aluno_ra")},
            inverseJoinColumns = {
                @JoinColumn(name = "turma_id")})
    private List<Turma> turmas;

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Turma> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<Turma> turmas) {
        this.turmas = turmas;
    }
}
