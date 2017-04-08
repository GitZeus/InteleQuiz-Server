package controlador;

import util.RestResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class TesteController {
    
    @RequestMapping("/")
    public RestResponse teste() {
        return new RestResponse("Comunicação REST ok");
    }   
}