package model.entity;

public class Aluno extends Usuario{
    public Aluno(){
        super();
    }
    
    public Aluno(Integer id, String nome, String login, String senha, TipoUsuario perfil){
        this.id = id;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.perfil = perfil;
    }
}
