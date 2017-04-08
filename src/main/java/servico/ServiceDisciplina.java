package servico;

import entidade.Disciplina;
import entidade.Professor;
import entidade.Questao;
import entidade.Tema;
import entidade.Usuario;
import enums.TipoUsuario;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistencia.GatewayDisciplina;
import util.GlobalException;
import util.ITQException;

@Service
public class ServiceDisciplina {
    
    @Autowired
    private GatewayDisciplina gatewayDisciplina;
    
    public List<Disciplina> listDisciplinasByProfessor(Professor p) throws GlobalException {
        try {
           return gatewayDisciplina.listDisciplinasByProfessor(p);
        } catch (Exception e) {
            throw new GlobalException(e);
        }
    }
    
    public List<Tema> listTagsByDisciplina(Disciplina d) throws GlobalException {
        try {
            return gatewayDisciplina.listTagsByDisciplina(d);
        } catch (Exception e) {
            throw new GlobalException(e);
        }
    }
    
    public List<Questao> listQuestoesByTag() throws GlobalException {
        try {
            return gatewayDisciplina.listQuestoesByTag();
        } catch (Exception e) {
            throw new GlobalException(e);
        }
    }
    
}