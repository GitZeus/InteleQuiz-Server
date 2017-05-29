package controlador;

import entidade.Questao;
import entidade.Quiz;
import entidade.Turma;
import entidade.Publicacao;
import enums.StatusTurmaQuiz;
import java.util.List;
import util.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import servico.ServiceQuiz;
import util.RestMessage;

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
            value = "/professor/{matricula}/disciplina/{id}/turma",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<List<Turma>> listTurmasByProfessorByDisciplina(
            @PathVariable("matricula") String matricula,
            @PathVariable("id") int id) throws Exception {
        return new RestResponse<>(serviceQuiz.listTurmasByProfessorByDisciplina(matricula, id));
    }

    @RequestMapping(
            value = "/professor/{matricula}/disciplina/{id}/quiz",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<List<Quiz>> listQuizByDisciplinaByProfessor(
            @PathVariable("matricula") String matricula_professor,
            @PathVariable("id") int disciplina_id) throws Exception {
        return new RestResponse<>(serviceQuiz.listQuizByDisciplinaByProfessor(matricula_professor, disciplina_id));
    }

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
            value = "/quiz/{id}/questao",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<List<Questao>> listQuestoesByQuiz(
            @PathVariable("id") int quiz_id) throws Exception {
        return new RestResponse<>(serviceQuiz.listQuestoesByQuiz(quiz_id));
    }

    @RequestMapping(
            value = "/publicacao",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<RestMessage> publicarQuiz(@RequestBody Publicacao tq) throws Exception {
        return new RestResponse<>(serviceQuiz.publicarQuiz(tq));
    }

    @RequestMapping(
            value = "/turma/{id}/quiz",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<List<Publicacao>> listQuizPublicadoByStatusByTurma(
            @PathVariable int id,
            @RequestParam(value = "status", required = false) StatusTurmaQuiz status) throws Exception {
        return new RestResponse<>(serviceQuiz.listQuizPublicadoByStatusByTurma(id, status));
    }
}