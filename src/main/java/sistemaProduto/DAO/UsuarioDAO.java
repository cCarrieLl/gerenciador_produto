package sistemaProduto.DAO;

import sistemaProduto.conexao.ConexaoBanco;
import sistemaProduto.model.UsuarioModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    //private List<UsuarioModel> usuario;

    public UsuarioDAO(){
        //this.usuario = new ArrayList<>();
    }

    public boolean cadastroUsuario(String nome, String email, String senha, String tipo){
        String sql = "INSERT INTO usuarios (nome, email, senha, tipo) VALUES (?,?,?,?)";

        try{
            Connection conn = ConexaoBanco.conectar();
            PreparedStatement comando = conn.prepareStatement(sql);

            comando.setString(1, nome);
            comando.setString(2, email);
            comando.setString(3, senha);
            comando.setString(4, tipo);

            comando.executeUpdate();

            comando.close();
            conn.close();

            return true;
        }catch(SQLException e){
            return false;
        }
    }

   public UsuarioModel buscarEmail(String email, String senha){
       Connection conn = ConexaoBanco.conectar();
       UsuarioModel usuario = null;

       String sql = "SELECT id, email, nome, tipo FROM usuarios WHERE email = ? AND senha = ?";

       /*if(conn == null){
            return null;
        }*/

        try{
            PreparedStatement comando = conn.prepareStatement(sql);
            comando.setString(1, email);
            comando.setString(2, senha);

            ResultSet rs = comando.executeQuery();

            if (rs.next()) {
                usuario = new UsuarioModel();

                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setTipo(rs.getString("tipo"));
            }

            rs.close();
            comando.close();
            conn.close();

        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }


       return usuario;
   }

    public List<UsuarioModel> listaUsuario(){
        List<UsuarioModel> lista = new ArrayList<>();
        String sql = "SELECT id, email, nome, tipo FROM usuarios";

        Connection conn = ConexaoBanco.conectar();

        if(conn == null){
            return lista;
        }

        try{
            PreparedStatement comando = conn.prepareStatement(sql);
            ResultSet rs = comando.executeQuery();

            while(rs.next()){
                UsuarioModel u = new UsuarioModel();
                u.setId(rs.getInt("id"));
                u.setNome(rs.getString("nome"));
                u.setEmail(rs.getString("email"));
                u.setTipo(rs.getString("tipo"));
                lista.add(u);
            }

            rs.close();
            comando.close();
            conn.close();

        }catch(SQLException e){
            return null;
        }
        return lista;
    }

}
