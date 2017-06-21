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
@Table(name = "tb_aluno")
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

    @OneToMany(mappedBy = "aluno")
    private List<Treino> treinos;

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

    public List<Treino> getTreinos() {
        return treinos;
    }

    public void setTreinos(List<Treino> treinos) {
        this.treinos = treinos;
    }
}
