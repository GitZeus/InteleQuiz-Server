package controller;

import service.AutenticacaoService;
import model.application.RestResponse;
import model.entity.TipoUsuario;
import model.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("autenticacaoController")
public class AutenticacaoController {

    @Autowired
    private AutenticacaoService autenticacaoService;
    
    @RequestMapping(value = "/autenticar", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<Usuario> autenticar(@RequestBody Usuario usuario) throws Exception {
        return new RestResponse<>(autenticacaoService.getUsuarioByLoginSenha(usuario));
    }
    
    @RequestMapping(value="tiposUsuario", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<TipoUsuario[]> getTiposUsuario(){
        return new RestResponse<>(autenticacaoService.getTiposUsuario());
    }
}