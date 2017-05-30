package controlador;

import entidade.Disciplina;
import java.util.List;
import util.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import servico.ServiceDisciplina;

@RestController
@CrossOrigin
@RequestMapping("ResourceDisciplina")
public class ResourceDisciplina {

    @Autowired
    private ServiceDisciplina serviceDisciplina;

        @RequestMapping(
            value = "/professor/{matricula}/disciplina",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<List<Disciplina>> listDisciplinaByProfessor(@PathVariable("matricula") String matricula) throws Exception {
        return new RestResponse<>(serviceDisciplina.listDisciplinaByProfessor(matricula));
    }
}