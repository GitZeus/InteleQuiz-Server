package enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum StatusQuizQuestao {
    CADASTRADO("Cadastrado"),
    VINCULADO("Vinculado")
    ;

    private final String nome;

    private StatusQuizQuestao(String nome) {
        this.nome = nome;
    }

    @JsonValue
    public String getNome() {
        return nome;
    }
}
