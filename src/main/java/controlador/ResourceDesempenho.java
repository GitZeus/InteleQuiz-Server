package controlador;

import util.DesempenhoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import servico.ServiceDesempenho;
import util.RestResponse;

@RestController
@CrossOrigin
@RequestMapping("ResourceDesempenho")
public class ResourceDesempenho {

    @Autowired
    private ServiceDesempenho serviceDesempenho;

    @RequestMapping(
            value = "turma/{id}/desempenho",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<DesempenhoVO> getDesempenhoByTurmaByProfessor(
            @PathVariable int id) throws Exception {
        return new RestResponse<>(serviceDesempenho.getDesempenhoByTurmaByProfessor(id));
    }

    @RequestMapping(
            value = "turma/{id}/aluno/{ra}/desempenho",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<DesempenhoVO> getDesempenhoByTurmaByAluno(
            @PathVariable int id, @PathVariable String ra) throws Exception {
        return new RestResponse<>(serviceDesempenho.getDesempenhoByTurmaByAluno(id, ra));
    }

    @RequestMapping(
            value = "/publicacao/{id}/temaCritico",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<DesempenhoVO> getTemaCriticoByPublicacao(@PathVariable("id") int id) throws Exception {
        return new RestResponse<>(serviceDesempenho.getTemaCriticoByPublicacao(id));
    }

    @RequestMapping(
            value = "/publicacao/{id}/aluno/{ra}/temaCritico",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<DesempenhoVO> getTemaCriticoByPublicacaoByAluno(@PathVariable int id, @PathVariable String ra) throws Exception {
        return new RestResponse<>(serviceDesempenho.getTemaCriticoByPublicacaoByAluno(id, ra));
    }

    @RequestMapping(
            value = "/publicacao/{publicacao_id}/temaCritico/{tema_id}/questao",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<DesempenhoVO> listQuestoesCriticasByPublicacaoByTemaCritico(@PathVariable int publicacao_id, @PathVariable int tema_id) throws Exception {
        return new RestResponse<>(serviceDesempenho.listQuestoesCriticasByPublicacaoByTemaCritico(publicacao_id, tema_id));
    }

    @RequestMapping(
            value = "/publicacao/{publicacao_id}/aluno/{ra}/temaCritico/{tema_id}/questao",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<DesempenhoVO> listQuestoesCriticasByPublicacaoByAlunoByTemaCritico(@PathVariable int publicacao_id, @PathVariable String ra, @PathVariable int tema_id) throws Exception {
        return new RestResponse<>(serviceDesempenho.listQuestoesCriticasByPublicacaoByAlunoByTemaCritico(publicacao_id, ra, tema_id));
    }

}
