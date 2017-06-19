package enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoNivelQuestao {
    FACIL("Fácil", 1),
    MEDIO("Médio", 2),
    DIFICIL("Dificil", 3);

    private final String nome;
    private final int ponto;

    private TipoNivelQuestao(String nome, int ponto) {
        this.nome = nome;
        this.ponto = ponto;
    }

    @JsonValue
    public String getNome() {
        return nome;
    }

    public int getPonto() {
        return ponto;
    }
}
