package service;

import java.util.List;
import model.application.GlobalException;
import model.application.ITQException;
import model.entity.TipoUsuario;
import model.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import gateway.AutenticacaoGateway;

@Service
public class AutenticacaoService {

    @Autowired
    private AutenticacaoGateway autenticacaoGateway;

    public Usuario getUsuarioByLoginSenha(Usuario u) throws GlobalException {
        try {
            if (u.getPerfil() == TipoUsuario.PROFESSOR || u.getPerfil() == TipoUsuario.COORDENADOR) {
                return autenticacaoGateway.getProfessorByLoginSenha(u);
            } else if (u.getPerfil() == TipoUsuario.ALUNO) {
                return autenticacaoGateway.getAlunoByLoginSenha(u);
            } else {
                throw new ITQException("Perfil de usuário não reconhecido");
            }
        } catch (Exception e) {
            throw new GlobalException(e);
        }
    }

    public TipoUsuario[] getTiposUsuario() {
        return TipoUsuario.values();
    }

    public Usuario getUsuarioById(Usuario u) throws GlobalException {
        try {
            if (u.getPerfil() == TipoUsuario.PROFESSOR || u.getPerfil() == TipoUsuario.COORDENADOR) {
                return autenticacaoGateway.getUsuarioById(u);
            } else {
                throw new ITQException("Aluno ainda não implementado");
            }
        } catch (Exception e) {
            throw new GlobalException(e);
        }
    }

    public List<Usuario> listUsuario() throws GlobalException {
        try {
            return autenticacaoGateway.listUsuario();
        } catch (Exception e) {
            throw new GlobalException(e);
        }
    }

    public boolean saveUsuario(Usuario u) throws GlobalException {
        try {
            autenticacaoGateway.saveUsuario(u);
            return true;
        } catch (Exception e) {
            throw new GlobalException(e);
        }
    }

    public boolean updateUsuario(Usuario u) throws GlobalException {
        try {
            autenticacaoGateway.updateUsuario(u);
            return true;
        } catch (Exception e) {
            throw new GlobalException(e);
        }
    }

    public boolean deleteUsuario(Usuario u) throws GlobalException {
        try {
            autenticacaoGateway.deleteUsuario(u);
            return true;
        } catch (Exception e) {
            throw new GlobalException(e);
        }
    }
}
