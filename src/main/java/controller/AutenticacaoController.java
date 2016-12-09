package controller;

import service.AutenticacaoService;
import model.application.RestResponse;
import model.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:8383", maxAge = 3600)
public class AutenticacaoController {

    @Autowired
    private AutenticacaoService autenticacaoService;

    @RequestMapping("/teste")
    public RestResponse teste() {
        return new RestResponse("Hello Spring");
    }

    @RequestMapping(value = "/autenticar", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<Usuario> autenticar(@RequestBody Usuario usuario) throws Exception {
        return new RestResponse<>(autenticacaoService.autenticar(usuario));
    }
}