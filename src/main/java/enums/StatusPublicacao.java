package enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public enum StatusPublicacao {
    
    @JsonProperty("Publicado")
    PUBLICADO("Publicado"),
    
    @JsonProperty("Encerrado")
    ENCERRADO("Encerrado")
    ;

    private final String nome;

    private StatusPublicacao(String nome) {
        this.nome = nome;
    }

    @JsonValue
    public String getNome() {
        return nome;
    }
}
