package entidade;

import enums.TipoUsuario;

public class Usuario {
    private String login;
    private String senha;
    private TipoUsuario perfil;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public TipoUsuario getPerfil() {
        return perfil;
    }

    public void setPerfil(TipoUsuario perfil) {
        this.perfil = perfil;
    }
}