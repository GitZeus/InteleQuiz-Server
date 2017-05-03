package controlador;

import entidade.Quiz;
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
import servico.ServiceQuiz;

@RestController
@CrossOrigin
public class ResourceQuiz {

    @Autowired
    private ServiceQuiz serviceQuiz;

    @RequestMapping(
            value = "/professor/{matricula}/turma",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<List<Turma>> listTurmasByProfessor(
            @PathVariable("matricula") String matricula) throws Exception {
        return new RestResponse<>(serviceQuiz.listTurmasByProfessor(matricula));
    }
    
    @RequestMapping(
            value = "/professor/{matricula}/disciplina/{id}/quiz",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<List<Quiz>> listQuizByDisciplinaByProfessor(
            @PathVariable("matricula") String matricula_professor,
            @PathVariable("id") Integer disciplina_id) throws Exception {
        return new RestResponse<>(serviceQuiz.listQuizByDisciplinaByProfessor(matricula_professor, disciplina_id));
    }
}