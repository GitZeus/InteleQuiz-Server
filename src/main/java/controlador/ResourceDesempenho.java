package controlador;

import entidade.Desempenho;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import servico.ServiceDesempenho;
import util.RestResponse;

@RestController
@CrossOrigin
public class ResourceDesempenho {

    @Autowired
    private ServiceDesempenho serviceDesempenho;

    @RequestMapping(
            value = "desempenho/turma/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<Desempenho> getDesempenhoByTurma(
            @PathVariable Integer id) throws Exception {
        return new RestResponse<>(serviceDesempenho.getDesempenhoByTurma(id));
    }

}
