package enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoUsuario {
    PROFESSOR("Professor"),
    ALUNO("Aluno")
    ;

    private final String nome;

    private TipoUsuario(String nome) {
        this.nome = nome;
    }

    @JsonValue
    public String getNome() {
        return nome;
    }
}