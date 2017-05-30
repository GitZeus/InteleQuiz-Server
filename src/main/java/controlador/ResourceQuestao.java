package controlador;

import entidade.Questao;
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
import util.ITQException;
import util.RestMessage;

@RestController
@CrossOrigin
@RequestMapping("ResourceQuestao")
public class ResourceQuestao {

    @Autowired
    private ServiceQuestao serviceQuestao;

    @RequestMapping(
            value = "/questao/tipo",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<TipoQuestao[]> listTipoQuestao() throws ITQException {
        return new RestResponse<>(serviceQuestao.listTipoQuestao());
    }

    @RequestMapping(
            value = "/questao/nivel",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<NivelQuestao[]> listNivelQuestao() throws ITQException {
        return new RestResponse<>(serviceQuestao.listNivelQuestao());
    }

    @RequestMapping(
            value = "/questao/status",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<StatusQuizQuestao[]> listStatusQuizQuestao() throws ITQException {
        return new RestResponse<>(serviceQuestao.listStatusQuizQuestao());
    }

    @RequestMapping(
            value = "/questao",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<RestMessage> saveQuestao(@RequestBody Questao questao) throws ITQException {
        return new RestResponse<>(serviceQuestao.saveQuestao(questao));
    }

    @RequestMapping(
            value = "/questao",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<RestMessage> updateQuestao(@RequestBody Questao questao) throws ITQException {
        return new RestResponse<>(serviceQuestao.updateQuestao(questao));
    }

    @RequestMapping(
            value = "/quiz/{id}/questao",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<List<Questao>> listQuestaoByQuiz(
            @PathVariable("id") int id) throws Exception {
        return new RestResponse<>(serviceQuestao.listQuestaoByQuiz(id));
    }

    @RequestMapping(
            value = "/tema/{id}/questao",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<List<Questao>> listQuestaoByTema(@PathVariable("id") int id) throws Exception {
        return new RestResponse<>(serviceQuestao.listQuestaoByTema(id));
    }
}