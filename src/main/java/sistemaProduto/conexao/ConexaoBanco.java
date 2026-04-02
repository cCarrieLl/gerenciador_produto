package sistemaProduto.conexao;

import java.sql.*;

public class ConexaoBanco {

    private static final String url = "jdbc:mysql://localhost:3306/gerenciador";
    private static final String user = "root";
    private static final String password = "1234";


    public static Connection conectar(){
        try {
            Connection conn =DriverManager.getConnection(url, user, password);
            System.out.println("Conectou");
            return conn;
        }catch(SQLException e){
            return null;
        }


    }


}
