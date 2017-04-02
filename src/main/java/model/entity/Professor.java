package model.entity;

import java.util.ArrayList;
import java.util.List;

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
    
    private List<Disciplina> disciplinas = new ArrayList<Disciplina>();

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }
    
}