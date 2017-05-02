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

//    @RequestMapping(
//            value = "/tema",
//            method = RequestMethod.POST,
//            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public RestResponse<RestMessage> saveTema(@RequestBody Tema t) throws Exception {
//        return new RestResponse<>(serviceTreino.saveTema(t));
//    }
}
