package model.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity(name="professor")
public class Professor extends Usuario{

    public Professor(){
    }
    
    public Professor(Integer id, String nome, String login, String senha, TipoUsuario perfil){
        this.id = id;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.perfil = perfil;
    }
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "REL_PROFESSOR_DISCIPLINA", joinColumns = {@JoinColumn(name = "tb_professor_id")}, inverseJoinColumns = {@JoinColumn(name = "tb_disciplina_id")})
    public List<Disciplina> disciplinas = new ArrayList<Disciplina>();

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }
    
}