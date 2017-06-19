package enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoTurnoTurma {
    MATUTINO("Matutino"),
    VESPERTINO("Vespertino"),
    NOTURNO("Noturno")
    ;

    private final String nome;

    private TipoTurnoTurma(String nome) {
        this.nome = nome;
    }

    @JsonValue
    public String getNome() {
        return nome;
    }
}
