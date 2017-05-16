package controlador;

import entidade.Treino;
import entidade.Turma;
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
import servico.ServiceTreino;

@RestController
@CrossOrigin
public class ResourceTreino {

    @Autowired
    private ServiceTreino serviceTreino;

    @RequestMapping(
            value = "/aluno/{ra}/disciplina",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<List<Turma>> listTurmasByAluno(
            @PathVariable("ra") String ra) throws Exception {
        return new RestResponse<>(serviceTreino.listTurmasByAluno(ra));
    }

    @RequestMapping(
            value = "/aluno/{ra}/turmaQuiz/{turma_quiz_id}/treino",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<Treino> startNewTreino(
            @PathVariable("ra") String ra,
            @PathVariable("turma_quiz_id") Integer turma_quiz_id) throws Exception {
        return new RestResponse<>(serviceTreino.startNewTreino(ra, turma_quiz_id));
    }

    @RequestMapping(
            value = "/treino",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<Treino> updateTreino(@RequestBody Treino treino) throws Exception {
        return new RestResponse<>(serviceTreino.updateTreino(treino));
    }

    @RequestMapping(
            value = "/treino/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<Treino> getTreino(@PathVariable("id") Integer id) throws Exception {
        return new RestResponse<>(serviceTreino.getTreino(id));
    }
}
