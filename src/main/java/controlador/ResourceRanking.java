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
import util.RankingVO;

@RestController
@CrossOrigin
@RequestMapping("ResourceRanking")
public class ResourceRanking {

    @Autowired
    private ServiceRanking serviceRanking;

    @RequestMapping(
            value = "/aluno/{ra}/turma/ranking",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<List<RankingVO>> getRankingTurmaByAluno(
            @PathVariable("ra") String ra) throws Exception {
        return new RestResponse<>(serviceRanking.getRankingTurmaByAluno(ra));
    }
    
    @RequestMapping(
            value = "/professor/{matricula}/turma/ranking",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<List<RankingVO>> getRankingTurmaByProfessor(
            @PathVariable("matricula") String matricula) throws Exception {
        return new RestResponse<>(serviceRanking.getRankingTurmaByProfessor(matricula));
    }
    
    @RequestMapping(
            value = "/turma/{id}/aluno/ranking",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<List<RankingVO>> getRankingAlunoByTurma(
            @PathVariable("id") int id) throws Exception {
        return new RestResponse<>(serviceRanking.getRankingAlunoByTurma(id));
    }
}