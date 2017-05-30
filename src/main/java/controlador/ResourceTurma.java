package controlador;

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
import servico.ServiceTurma;

@RestController
@CrossOrigin
@RequestMapping("ResourceTurma")
public class ResourceTurma {

    @Autowired
    private ServiceTurma serviceTurma;

    @RequestMapping(
            value = "/professor/{matricula}/turma",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<List<Turma>> listTurmaByProfessor(
            @PathVariable("matricula") String matricula) throws Exception {
        return new RestResponse<>(serviceTurma.listTurmaByProfessor(matricula));
    }

    @RequestMapping(
            value = "/professor/{matricula}/disciplina/{id}/turma",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<List<Turma>> listTurmaByProfessorByDisciplina(
            @PathVariable("matricula") String matricula,
            @PathVariable("id") int id) throws Exception {
        return new RestResponse<>(serviceTurma.listTurmaByProfessorByDisciplina(matricula, id));
    }
    
    @RequestMapping(
            value = "/aluno/{ra}/turma",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<List<Turma>> listTurmaByAluno(
            @PathVariable("ra") String ra) throws Exception {
        return new RestResponse<>(serviceTurma.listTurmaByAluno(ra));
    }
}