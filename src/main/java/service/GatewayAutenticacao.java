package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.application.GlobalException;
import model.entity.Usuario;
import org.springframework.stereotype.Component;

@Component("gatewayAutenticacao")
public class GatewayAutenticacao {
    
    public Usuario autenticar(Usuario u) throws Exception{
        try {
            Connection con = GatewayConexao.getConection();
            String query = "select * from usuario"; //where login = ? and senha = ?
            PreparedStatement pstm = con.prepareStatement(query);
//            pstm.setString(1, u.getLogin());
//            pstm.setString(2, u.getSenha());
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                u.setCod_usuario(rs.getInt("cod_usuario"));
                u.setNome(rs.getString("nome"));
                u.setLogin(rs.getString("login"));
                u.setSenha(rs.getString("senha"));
            }
            return u;
        } catch (Exception e) {
            throw new GlobalException(e);
        }
    }
}