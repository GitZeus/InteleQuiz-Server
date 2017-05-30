package servico;

import entidade.Usuario;
import enums.TipoUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistencia.GatewayAutenticacao;
import util.ITQException;

@Service
public class ServiceUsuario {

    @Autowired
    private GatewayAutenticacao gatewayAutenticacao;

    public Usuario getUsuarioByLoginSenha(Usuario u) throws ITQException {
        try {
            if (null == u.getPerfil()) {
                throw new ITQException("Perfil de usuário não reconhecido");
            } else switch (u.getPerfil()) {
                case PROFESSOR:
                    return gatewayAutenticacao.getProfessorByLoginSenha(u);
                case ALUNO:
                    return gatewayAutenticacao.getAlunoByLoginSenha(u);
                default:
                    throw new ITQException("Perfil de usuário não reconhecido");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ITQException("Erro ao autenticar usuário, contate o administrador do sistema");
        }
    }

    public TipoUsuario[] listPerfilUsuario() throws ITQException {
        try {
            return TipoUsuario.values();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ITQException("Erro não previsto ao listar perfis de usuário");
        }
    }
}