package resource;

import gateway.GatewayAutenticacao;
import model.application.RestResponse;
import model.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class ResourceAutenticacao {
    
    @Autowired
    private GatewayAutenticacao gatewayAutenticacao;

    @RequestMapping("/teste")
    public RestResponse teste() {
        return new RestResponse("Hello Spring");
    }

    @RequestMapping(value = "/autenticar", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<Usuario> autenticar() throws Exception {
        Usuario usuario = new Usuario("José");
        System.out.println("Usuário: " + usuario);
        return new RestResponse<>(gatewayAutenticacao.autenticar(usuario));
    }
}