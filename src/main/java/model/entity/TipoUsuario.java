package model.entity;

import java.util.List;

public enum TipoUsuario {
    PROFESSOR(1), ALUNO(2), COORDENADOR(3);
    
    private final int codigo;
    
    private TipoUsuario(int codigo){
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }
    
//    public List<TipoUsuario> listTipoUsuario(){
//        
//    }
}