package enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoQuestao {
    VERDADEIRO_FALSO("Verdadeiro ou Falso"),
    UNICA_ESCOLHA("Única Escolha"),
    MULTIPLA_ESCOLHA("Múltipla Escolha");

    private final String nome;

    private TipoQuestao(String nome) {
        this.nome = nome;
    }
    
    @JsonValue
    public String getNome(){
        return nome;
    }
}
