package controlador;

import entidade.TurmaQuiz;
import enums.StatusTurmaQuiz;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import servico.ServiceDesempenho;
import util.RestResponse;

@RestController
@CrossOrigin
public class ResourceDesempenho {

    @Autowired
    private ServiceDesempenho serviceDesempenho;

//    @RequestMapping(
//            value = "/turma/{id}/quiz",
//            method = RequestMethod.GET,
//            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public RestResponse<List<TurmaQuiz>> listQuizEncerradoByTurma(
//            @PathVariable Integer id,
//            @RequestParam(value = "status", required = false) StatusTurmaQuiz status) throws Exception {
//        return new RestResponse<>(serviceDesempenho.listQuizEncerradoByTurma(id, status));
//    }
    
}
