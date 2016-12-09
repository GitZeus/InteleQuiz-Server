package service;

import model.application.GlobalException;
import model.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.UsuarioDAO;

@Service
public class AutenticacaoService {
    
    @Autowired
    private UsuarioDAO usuarioDAO;
    
    public Usuario autenticar(Usuario u) throws Exception{
        try {
            return usuarioDAO.getUsuarioById(u);
        } catch (Exception e) {
            throw new GlobalException(e);
        }
    }
}