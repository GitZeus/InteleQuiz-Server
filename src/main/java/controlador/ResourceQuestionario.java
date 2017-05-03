package controlador;

import entidade.Questao;
import entidade.Quiz;
import java.util.List;
import util.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import servico.ServiceQuestionario;
import util.RestMessage;

public class ResourceQuestionario {

    @Autowired
    private ServiceQuestionario serviceQuestionario;

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
    public RestResponse<RestMessage> saveQuestionario(@RequestBody Quiz q) throws Exception {
        return new RestResponse<>(serviceQuestionario.saveQuestionario(q));
    }
    
    @RequestMapping(
            value = "/questionario",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<RestMessage> updateQuestionario(@RequestBody Quiz q) throws Exception {
        return new RestResponse<>(serviceQuestionario.updateQuestionario(q));
    }
}