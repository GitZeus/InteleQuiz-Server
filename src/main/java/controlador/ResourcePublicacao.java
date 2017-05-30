package controlador;

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
import servico.ServicePublicacao;
import util.RestMessage;

@RestController
@CrossOrigin
@RequestMapping("ResourcePublicacao")
public class ResourcePublicacao {

    @Autowired
    private ServicePublicacao servicePublicacao;

    @RequestMapping(
            value = "/publicacao",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<RestMessage> savePublicacao(@RequestBody Publicacao publicacao) throws Exception {
        return new RestResponse<>(servicePublicacao.savePublicacao(publicacao));
    }

    @RequestMapping(
            value = "/turma/{id}/quiz",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<List<Publicacao>> listPublicacaoByStatusByTurma(
            @PathVariable int id,
            @RequestParam(value = "status", required = true) StatusTurmaQuiz status) throws Exception {
        return new RestResponse<>(servicePublicacao.listPublicacaoByStatusByTurma(id, status));
    }
}