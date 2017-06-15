package controlador;

import enums.TipoUsuario;
import util.RestResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class ResourceTeste {
    
    @RequestMapping("/")
    public RestResponse<TipoUsuario> teste() {
        return new RestResponse("Comunicação REST ok");
    }   
}