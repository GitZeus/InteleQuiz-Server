package controller;

import java.util.List;
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
@CrossOrigin//(origins = "http://192.168.0.3:8100", maxAge = 3600)
public class AutenticacaoController {

    @Autowired
    private AutenticacaoService autenticacaoService;

    @RequestMapping("/teste")
    public RestResponse teste() {
        return new RestResponse("Hello Spring");
    }

    @RequestMapping(value = "/getUsuarioById", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<Usuario> getUsuarioById(@RequestBody Usuario usuario) throws Exception {
        return new RestResponse<>(autenticacaoService.getUsuarioById(usuario));
    }
    
    @RequestMapping(value = "/autenticar", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<Usuario> autenticar(@RequestBody Usuario usuario) throws Exception {
        return new RestResponse<>(autenticacaoService.getUsuarioByLoginSenha(usuario));
    }
    
    @RequestMapping(value = "/listarUsuarios", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<List<Usuario>> listUsuario() throws Exception {
        return new RestResponse<>(autenticacaoService.listUsuario());
    }
    
    @RequestMapping(value = "/salvarUsuario", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<Boolean> saveUsuario(@RequestBody Usuario usuario) throws Exception {
        return new RestResponse<>(autenticacaoService.saveUsuario(usuario));
    }
    
    @RequestMapping(value = "/alterarUsuario", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<Boolean> updateUsuario(@RequestBody Usuario usuario) throws Exception {
        return new RestResponse<>(autenticacaoService.updateUsuario(usuario));
    }
    
    @RequestMapping(value = "/deletarUsuario", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<Boolean> deleteUsuario(@RequestBody Usuario usuario) throws Exception {
        return new RestResponse<>(autenticacaoService.deleteUsuario(usuario));
    }
}