package enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

//@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TipoUsuario {
    PROFESSOR("Professor", 1), ALUNO("Aluno", 2);

    private String nome;
    private int codigo;

    private TipoUsuario(String nome, int codigo) {
        this.nome = nome;
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return String.valueOf(this.codigo);
    }

    @JsonValue
    public String getNome() {
        return nome;
    }

    public int getCodigo() {
        return codigo;
    }
}
