package controller;

import java.util.List;
import model.application.RestResponse;
import model.entity.Disciplina;
import model.entity.Tag;
import model.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.ServiceDisciplina;

@RestController
@CrossOrigin
@RequestMapping("disciplinaResource")
public class ResourceDisciplina {
    
    @Autowired
    private ServiceDisciplina serviceDisciplina;
    
    @RequestMapping(value = "/listDisciplinasByProfessor", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<List<Disciplina>> listDisciplinasByProfessor(@RequestBody Usuario usuario) throws Exception {
        return new RestResponse<>(serviceDisciplina.listDisciplinasByProfessor(usuario));
    }
    
    @RequestMapping(value = "/listTagsByDisciplina", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<List<Tag>> listTagsByDisciplina(@RequestBody Disciplina disciplina) throws Exception {
        return new RestResponse<>(serviceDisciplina.listTagsByDisciplina(disciplina));
    }
    
    
}