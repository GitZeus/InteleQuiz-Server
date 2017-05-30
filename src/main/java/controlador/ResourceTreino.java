package controlador;

import entidade.Treino;
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
@RequestMapping("ResourceTreino")
public class ResourceTreino {

    @Autowired
    private ServiceTreino serviceTreino;

    @RequestMapping(
            value = "/aluno/{ra}/publicacao/{id}/treino",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<Treino> saveTreino(
            @PathVariable("ra") String ra,
            @PathVariable("id") int id) throws Exception {
        return new RestResponse<>(serviceTreino.saveTreino(ra, id));
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
    public RestResponse<Treino> getTreinoById(@PathVariable("id") int id) throws Exception {
        return new RestResponse<>(serviceTreino.getTreinoById(id));
    }
}