package service;

import gateway.GatewayDisciplina;
import java.util.List;
import model.application.GlobalException;
import model.application.ITQException;
import model.entity.Disciplina;
import model.entity.Tag;
import model.entity.TipoUsuario;
import model.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceDisciplina {
    
    @Autowired
    private GatewayDisciplina gatewayDisciplina;
    
    public List<Disciplina> listDisciplinasByProfessor(Usuario u) throws GlobalException {
        try {
            if (u.getPerfil() == TipoUsuario.PROFESSOR || u.getPerfil() == TipoUsuario.COORDENADOR) {
                return gatewayDisciplina.listDisciplinasByProfessor(u);
            } else {
                throw new ITQException("Perfil sem acesso à funcionalidade");
            }
        } catch (Exception e) {
            throw new GlobalException(e);
        }
    }
    
    public List<Tag> listTagsByDisciplina(Disciplina d) throws GlobalException {
        try {
            return gatewayDisciplina.listTagsByDisciplina(d);
        } catch (Exception e) {
            throw new GlobalException(e);
        }
    }
    
}