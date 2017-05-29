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
import util.ITQException;
import util.RestMessage;

@RestController
@CrossOrigin
public class ResourceQuestao {

    @Autowired
    private ServiceQuestao serviceQuestao;

    @RequestMapping(
            value = "/professor/disciplinas",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<List<Disciplina>> listDisciplinasByProfessor(@RequestBody Professor p) throws Exception {
        return new RestResponse<>(serviceQuestao.listDisciplinasByProfessor(p));
    }

    @RequestMapping(
            value = "/tema/{id}/questoes",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<List<Questao>> listQuestoesByTema(@PathVariable("id") int tema_id) throws Exception {
        return new RestResponse<>(serviceQuestao.listQuestoesByTema(tema_id));
    }

    @RequestMapping(
            value = "/questoes/tipos",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<TipoQuestao[]> listTiposQuestao() throws ITQException {
        return new RestResponse<>(serviceQuestao.listTiposQuestao());
    }

    @RequestMapping(
            value = "/questoes/niveis",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<NivelQuestao[]> listNiveisQuestao() throws ITQException {
        return new RestResponse<>(serviceQuestao.listNiveisQuestao());
    }

    @RequestMapping(
            value = "/questao/status",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<StatusQuizQuestao[]> listStatusQuizQuestao() throws ITQException{
        return new RestResponse<>(serviceQuestao.listStatusQuizQuestao());
    }

    @RequestMapping(
            value = "/questao/{id}/temas",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<List<Tema>> listTemasByQuestao(@PathVariable("id") int questao_id) throws ITQException{
        return new RestResponse<>(serviceQuestao.listTemasByQuestao(questao_id));
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
}
