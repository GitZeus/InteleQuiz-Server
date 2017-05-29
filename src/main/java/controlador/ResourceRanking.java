package controlador;

import entidade.Aluno;
import entidade.Turma;
import java.util.List;
import util.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import servico.ServiceRanking;

@RestController
@CrossOrigin
public class ResourceRanking {

    @Autowired
    private ServiceRanking serviceRanking;

    @RequestMapping(
            value = "/aluno/{ra}/turma/ranking",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<List<Turma>> listRankingTurmasByAluno(
            @PathVariable("ra") String ra) throws Exception {
        return new RestResponse<>(serviceRanking.listRankingTurmasByAluno(ra));
    }
    
    @RequestMapping(
            value = "/professor/{matricula}/turma/ranking",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<List<Turma>> listRankingTurmasByProfessor(
            @PathVariable("matricula") String matricula) throws Exception {
        return new RestResponse<>(serviceRanking.listRankingTurmasByProfessor(matricula));
    }
    
    @RequestMapping(
            value = "/turma/{id}/aluno/ranking",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<List<Aluno>> listRankingAlunosByTurma(
            @PathVariable("id") int id) throws Exception {
        return new RestResponse<>(serviceRanking.listRankingAlunosByTurma(id));
    }

}
