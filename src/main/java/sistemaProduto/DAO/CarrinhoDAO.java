package sistemaProduto.DAO;

import sistemaProduto.conexao.ConexaoBanco;
import sistemaProduto.model.CarrinhoModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarrinhoDAO {

    public CarrinhoDAO(){}

    public boolean existeProduto(int id){
        Connection conn = ConexaoBanco.conectar();
        String sql = "SELECT COUNT(*) FROM produtos WHERE id = ?";

        try{
            PreparedStatement comando = conn.prepareStatement(sql);
            comando.setInt(1, id);

            ResultSet rs = comando.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

            rs.close();
            conn.close();

        }catch(SQLException e){
            return false;
        }
        return false;
    }

    public boolean adicionarItem(int idUsuario, int idProduto){
        if(!existeProduto(idProduto)){
            return false;
        }

        String sqlVerificacao = "SELECT id FROM carrinho WHERE id_usuario = ? AND id_produto = ?";

        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement comando = conn.prepareStatement(sqlVerificacao)) {

            comando.setInt(1, idUsuario);
            comando.setInt(2, idProduto);

            ResultSet rs = comando.executeQuery();

            if(!rs.next()){
                String sql = "INSERT INTO carrinho (id_usuario, id_produto) VALUES (?, ?)";

                try (PreparedStatement insert = conn.prepareStatement(sql)) {
                    insert.setInt(1, idUsuario);
                    insert.setInt(2, idProduto);
                    insert.executeUpdate();
                    return true;
                }
            }

        } catch(SQLException e){
            e.printStackTrace();
        }

        return false;
    }

    public boolean removerItem(int idUsuario, int idProduto){
        String sql = "DELETE FROM carrinho WHERE id_usuario = ? AND id_produto = ?";

        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement comando = conn.prepareStatement(sql)){


            comando.setInt(1, idUsuario);
            comando.setInt(2, idProduto);

            int linhasAfetadas = comando.executeUpdate();

            return linhasAfetadas > 0;
        }catch(SQLException e){
            return false;
        }
    }

    public boolean limparCarrinho(int idUsuario){
        String sql = "DELETE FROM carrinho WHERE id_usuario = ?";
        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement comando = conn.prepareStatement(sql)){


            comando.setInt(1, idUsuario);

            int linhasAfetadas = comando.executeUpdate();

            return linhasAfetadas > 0;
        }catch(SQLException e){
            return false;
        }
    }

    public List<CarrinhoModel> listarCarrinho(int idUsuario) {
        Connection conn = ConexaoBanco.conectar();
        List<CarrinhoModel> lista = new ArrayList<>();
        String sql = """
                        SELECT
                            c.id,
                            u.nome,
                            c.id_produto,
                            p.nomeProduto,
                            p.preco
                        FROM carrinho c
                        JOIN produtos p\s
                            ON p.id = c.id_produto
                        JOIN usuarios u\s
                            ON u.id = c.id_usuario
                        WHERE c.id_usuario = ?""";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                CarrinhoModel item = new CarrinhoModel();
                        item.setId(rs.getInt("id"));
                        item.setIdProduto(rs.getInt("id_produto"));
                        item.setNomeUsuario(rs.getString("nome"));
                        item.setNomeProduto(rs.getString("nomeProduto"));
                        item.setPreco(rs.getDouble("preco"));
                lista.add(item);
            }
            rs.close();

        } catch (SQLException e) {
            return null;
        }

        return lista;
    }
}
