package controlador;

import entidade.Questao;
import entidade.Questionario;
import java.util.List;
import util.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import servico.ServiceQuestionario;
import util.RestMessage;

@RestController
@CrossOrigin
public class ResourceQuestionario {

    @Autowired
    private ServiceQuestionario serviceQuestionario;

    @RequestMapping(
            value = "/professor/{matricula}/disciplina/{id}/questionario",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<List<Questionario>> listQuestionarioByDisciplinaByProfessor(
            @PathVariable("matricula") String matricula_professor,
            @PathVariable("id") Integer disciplina_id) throws Exception {
        return new RestResponse<>(serviceQuestionario.listQuestionarioByDisciplinaByProfessor(matricula_professor, disciplina_id));
    }
    
    @RequestMapping(
            value = "/questionario/{id}/questao",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<List<Questao>> listQuestoesByQuestionario(
            @PathVariable("id") Integer questionario_id) throws Exception {
        return new RestResponse<>(serviceQuestionario.listQuestoesByQuestionario(questionario_id));
    }

    @RequestMapping(
            value = "/questionario",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<RestMessage> saveQuestionario(@RequestBody Questionario q) throws Exception {
        return new RestResponse<>(serviceQuestionario.saveQuestionario(q));
    }
    
    @RequestMapping(
            value = "/questionario",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<RestMessage> updateQuestionario(@RequestBody Questionario q) throws Exception {
        return new RestResponse<>(serviceQuestionario.updateQuestionario(q));
    }
}