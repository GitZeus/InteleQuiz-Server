package controlador;

import entidade.Usuario;
import enums.TipoUsuario;
import servico.ServiceAutenticacao;
import util.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import util.ITQException;

@RestController
@CrossOrigin
public class ResourceAutenticacao {

    @Autowired
    private ServiceAutenticacao serviceAutenticacao;
    
    @RequestMapping(
            value = "usuario/autenticacao",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<Usuario> autenticarUsuario(@RequestBody Usuario usuario) throws ITQException {
        return new RestResponse<>(serviceAutenticacao.getUsuarioByLoginSenha(usuario));
    }
    
    @RequestMapping(
            value="usuario/tipo",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<TipoUsuario[]> getTiposUsuario() throws ITQException{
        return new RestResponse<>(serviceAutenticacao.getTiposUsuario());
    }
}