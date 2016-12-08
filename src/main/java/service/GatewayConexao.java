package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import model.application.GlobalException;

public class GatewayConexao {

    public static Connection getConection() throws ClassNotFoundException, SQLException, GlobalException {
        try {
            //Carrega o driver na memória
//            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Class.forName("com.mysql.jdbc.Driver");

            // Conecta ao banco
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/appdata", "root", "root");

            return con;
        } catch (Exception e) {
            throw new GlobalException(e);
        }
    }
}