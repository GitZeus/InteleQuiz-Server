package controlador;

import entidade.Quiz;
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
import servico.ServiceQuiz;
import util.RestMessage;

@RestController
@CrossOrigin
@RequestMapping("ResourceQuiz")
public class ResourceQuiz {

    @Autowired
    private ServiceQuiz serviceQuiz;

    @RequestMapping(
            value = "/quiz",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<RestMessage> saveQuiz(@RequestBody Quiz q) throws Exception {
        return new RestResponse<>(serviceQuiz.saveQuiz(q));
    }

    @RequestMapping(
            value = "/quiz",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<RestMessage> updateQuiz(@RequestBody Quiz q) throws Exception {
        return new RestResponse<>(serviceQuiz.updateQuiz(q));
    }
    
    @RequestMapping(
            value = "/professor/{matricula}/disciplina/{id}/quiz",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<List<Quiz>> listQuizByDisciplinaByProfessor(
            @PathVariable("matricula") String matricula,
            @PathVariable("id") int id) throws Exception {
        return new RestResponse<>(serviceQuiz.listQuizByDisciplinaByProfessor(matricula, id));
    }
}