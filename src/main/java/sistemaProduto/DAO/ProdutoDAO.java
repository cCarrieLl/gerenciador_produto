package sistemaProduto.DAO;

import sistemaProduto.conexao.ConexaoBanco;
import sistemaProduto.model.ProdutoModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

	public ProdutoDAO() {
	}

	public boolean remover(int idProduto, int idVendedor){
		Connection conn = ConexaoBanco.conectar();
		if(conn == null){
			return false;
		}

		String sql = "DELETE FROM produtos WHERE id = ? AND usuario_id = ?";

		try{
			PreparedStatement comando = conn.prepareStatement(sql);

			comando.setInt(1, idProduto);
			comando.setInt(2, idVendedor);

			int linhasAfetadas =  comando.executeUpdate();

			comando.close();
			conn.close();

			return linhasAfetadas > 0;
		}catch(SQLException e){
			return false;
		}
	}

	public boolean adicionandoProduto(String nome, double preco, int estoque, int id){
		Connection conn = ConexaoBanco.conectar();

		if(conn == null){
			return false;
		}

		String sql = "INSERT INTO produtos (nomeProduto, preco, estoque, usuario_id) VALUES (?, ?, ?, ?)";

		try{
			//conn = ConexaoBanco.conectar();
			PreparedStatement comando = conn.prepareStatement(sql);

			comando.setString(1, nome);
			comando.setDouble(2, preco);
			comando.setInt(3, estoque);
			comando.setInt(4, id);

			comando.executeUpdate();

			comando.close();
			conn.close();

			return true;
		}catch(SQLException e){
			return false;
		}

	}
	
	public List<ProdutoModel> listar() {
		Connection conn = ConexaoBanco.conectar();
		List<ProdutoModel> produto = new ArrayList<>();

		if(conn == null){
			return produto;
		}

		String sql = "SELECT p.id, p.nomeProduto, p.preco, p.estoque, u.nome AS vendedor " +
				"FROM produtos p " +
				"JOIN usuarios u ON p.usuario_id = u.id";

		try{
			PreparedStatement comando = conn.prepareStatement(sql);
			ResultSet rs = comando.executeQuery();


			while(rs.next()){
				ProdutoModel p = new ProdutoModel();
				p.setId(rs.getInt("id"));
				p.setNomeProduto(rs.getString("nomeProduto"));
				p.setVendedor(rs.getString("vendedor"));
				p.setPreco(rs.getDouble("preco"));
				p.setEstoque(rs.getInt("estoque"));
				produto.add(p);
			}

			rs.close();
			comando.close();
			conn.close();

		}catch(SQLException e){
			return null;
		}
		return produto;
	}

}
