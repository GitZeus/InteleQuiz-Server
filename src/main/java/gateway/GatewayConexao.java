package gateway;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GatewayConexao {

    public static Connection getConection() throws ClassNotFoundException, SQLException {
        try {
            //Carrega o driver na memória
//            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Class.forName("com.mysql.jdbc.Driver");

            // Conecta ao banco
//            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app");
//            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/RECEITAS", "root", "root");
            Connection con = DriverManager.getConnection("jdbc:mysql://sql9.freemysqlhosting.net:3306/sql9147355", "sql9147355", "b2g6Im8wiI");
//            Connection con = DriverManager.getConnection("jdbc:mysql://mysql.hostinger.com.br:3306/u484450709_intel", "u484450709_intel", "67856785");

            return con;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}