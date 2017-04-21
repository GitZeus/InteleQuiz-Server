package controlador;

import entidade.Disciplina;
import entidade.Professor;
import entidade.Questao;
import entidade.Tema;
import enums.NivelQuestao;
import enums.StatusQuizQuestao;
import enums.TipoQuestao;
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
import servico.ServiceQuestao;
import servico.ServiceTema;
import util.GlobalException;
import util.ITQException;
import util.RestMessage;

@RestController
@CrossOrigin
public class ResourceTema {

    @Autowired
    private ServiceTema serviceTema;

    @RequestMapping(
            value = "/tema",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<RestMessage> saveTema(@RequestBody Tema t) throws Exception {
        return new RestResponse<>(serviceTema.saveTema(t));
    }
}
