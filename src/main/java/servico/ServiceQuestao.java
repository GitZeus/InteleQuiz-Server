package servico;

import entidade.Disciplina;
import entidade.Professor;
import entidade.Questao;
import entidade.Tema;
import entidade.Usuario;
import enums.NivelQuestao;
import enums.TipoQuestao;
import enums.TipoUsuario;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistencia.GatewayDisciplina;
import util.GlobalException;
import util.ITQException;

@Service
public class ServiceQuestao {
    
//    @Autowired
    private GatewayDisciplina gatewayDisciplina;
    
    public List<Disciplina> listDisciplinasByProfessor(Professor p) throws GlobalException {
        try {
           return gatewayDisciplina.listDisciplinasByProfessor(p);
        } catch (Exception e) {
            throw new GlobalException(e);
        }
    }
    
    public List<Tema> listTemasByDisciplinaByProfessor(String matricula_professor, Integer disciplina_id) throws GlobalException {
        try {
            return gatewayDisciplina.listTemasByDisciplinaByProfessor(matricula_professor, disciplina_id);
        } catch (Exception e) {
            throw new GlobalException(e);
        }
    }
    
    public List<Questao> listQuestoesByTema(Integer tema_id) throws GlobalException {
        try {
            return gatewayDisciplina.listQuestoesByTema(tema_id);
        } catch (Exception e) {
            throw new GlobalException(e);
        }
    }
    
    public TipoQuestao[] listTiposQuestao() throws GlobalException {
        try {
            return TipoQuestao.values();
        } catch (Exception e) {
            throw new GlobalException(e);
        }
    }
    
    public NivelQuestao[] listNiveisQuestao() throws GlobalException {
        try {
            return NivelQuestao.values();
        } catch (Exception e) {
            throw new GlobalException(e);
        }
    }
}