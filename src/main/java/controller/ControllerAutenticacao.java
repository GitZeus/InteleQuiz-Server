package controller;

import service.GatewayAutenticacao;
import model.application.RestResponse;
import model.entity.Usuario;
import model.entity.UsuarioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:8383", maxAge = 3600)
public class ControllerAutenticacao {

    @Autowired
    private GatewayAutenticacao gatewayAutenticacao;

    @Autowired
    private UsuarioDAO usuarioDAO;

    @RequestMapping("/teste")
    public RestResponse teste() {
        return new RestResponse("Hello Spring");
    }

    @RequestMapping(value = "/autenticar", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<Usuario> autenticar() throws Exception {
        Usuario usuario = new Usuario("José");
        return new RestResponse<>(gatewayAutenticacao.autenticar(usuario));
    }

    @RequestMapping(value = "/login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<Usuario> login(@RequestBody int cod_usuario) {
        return new RestResponse<>(usuarioDAO.getPersonById(cod_usuario));
    }
}