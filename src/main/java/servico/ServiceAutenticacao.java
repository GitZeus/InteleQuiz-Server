package servico;

import entidade.Usuario;
import enums.TipoUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistencia.GatewayAutenticacao;
import util.ITQException;

@Service
public class ServiceAutenticacao {

    @Autowired
    private GatewayAutenticacao gatewayAutenticacao;

    public Usuario getUsuarioByLoginSenha(Usuario u) throws ITQException {
        try {
            if (u.getPerfil() == TipoUsuario.PROFESSOR || u.getPerfil() == TipoUsuario.COORDENADOR) {
                return gatewayAutenticacao.getProfessorByLoginSenha(u);
            } else if (u.getPerfil() == TipoUsuario.ALUNO) {
                return gatewayAutenticacao.getAlunoByLoginSenha(u);
            } else {
                throw new ITQException("Perfil de usuário não reconhecido");
            }
        } catch (Exception e) {
            throw new ITQException(e.getMessage());
        }
    }

    public TipoUsuario[] getTiposUsuario() throws ITQException {
        try {
            return TipoUsuario.values();
        } catch (Exception e) {
            throw new ITQException(e.getMessage());
        }
    }
}