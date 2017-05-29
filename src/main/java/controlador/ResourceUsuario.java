package controlador;

import entidade.Usuario;
import enums.TipoUsuario;
import servico.ServiceUsuario;
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
public class ResourceUsuario {

    @Autowired
    private ServiceUsuario serviceUsuario;
    
    @RequestMapping(
            value = "usuario",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<Usuario> getUsuarioByLoginSenha(@RequestBody Usuario usuario) throws ITQException {
        return new RestResponse<>(serviceUsuario.getUsuarioByLoginSenha(usuario));
    }
    
    @RequestMapping(
            value="usuario/perfil",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<TipoUsuario[]> listPerfilUsuario() throws ITQException{
        return new RestResponse<>(serviceUsuario.listPerfilUsuario());
    }
}