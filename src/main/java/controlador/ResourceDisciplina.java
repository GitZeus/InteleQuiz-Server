package controlador;

import entidade.Disciplina;
import entidade.Professor;
import entidade.Questao;
import entidade.Tema;
import java.util.List;
import util.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import servico.ServiceDisciplina;

@RestController
@CrossOrigin
public class ResourceDisciplina {
    
    @Autowired
    private ServiceDisciplina serviceDisciplina;
    
    @RequestMapping(
            value = "/professor/disciplinas",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<List<Disciplina>> listDisciplinasByProfessor(@RequestBody Professor p) throws Exception {
        return new RestResponse<>(serviceDisciplina.listDisciplinasByProfessor(p));
    }
    
    @RequestMapping(value = "/listTagsByDisciplina", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<List<Tema>> listTagsByDisciplina(@RequestBody Disciplina disciplina) throws Exception {
        return new RestResponse<>(serviceDisciplina.listTagsByDisciplina(disciplina));
    }
    
    @RequestMapping(value = "/listQuestoesByTag", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<List<Questao>> listQuestoesByTag() throws Exception {
        return new RestResponse<>(serviceDisciplina.listQuestoesByTag());
    }
    
    
}