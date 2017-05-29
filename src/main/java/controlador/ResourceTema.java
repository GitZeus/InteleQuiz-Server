package controlador;

import entidade.Tema;
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
import servico.ServiceTema;
import util.RestMessage;

@RestController
@CrossOrigin
public class ResourceTema {

    @Autowired
    private ServiceTema serviceTema;

    @RequestMapping(
            value = "/professor/{matricula}/disciplina/{id}/temas",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<List<Tema>> listTemasByDisciplinaByProfessor(
            @PathVariable("matricula") String matricula_professor,
            @PathVariable("id") int disciplina_id) throws Exception {
        return new RestResponse<>(serviceTema.listTemasByDisciplinaByProfessor(matricula_professor, disciplina_id));
    }

    @RequestMapping(
            value = "/tema",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<RestMessage> saveTema(@RequestBody Tema t) throws Exception {
        return new RestResponse<>(serviceTema.saveTema(t));
    }
}