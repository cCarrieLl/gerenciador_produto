package sistemaProduto.service;

import sistemaProduto.DAO.ProdutoDAO;
import sistemaProduto.model.ProdutoModel;
import sistemaProduto.model.UsuarioModel;

import java.util.List;

public class GerenciadorProdutos {
	private ProdutoDAO produto;

	public GerenciadorProdutos() {
		this.produto = new ProdutoDAO();
	}
	
	public boolean adicionarProduto(String nome, double preco, int estoque, int id) {
		if(nome == null || nome.isBlank() ) {
			return false;
		}

		for(var produto : listar()){
			if(nome.equals(produto.getVendedor())){
				return false;
			}
		}
		
		if(preco < 0){return false;}
		
		ProdutoModel mod = new ProdutoModel();
		mod.setVendedor(nome);
		mod.setPreco(preco);
		mod.setEstoque(estoque);
		mod.setId(id);
		
		produto.adicionandoProduto(nome, preco, estoque, id);
		return true;
	}
	
	public boolean removerItem(int idProduto, UsuarioModel usuarioAtual){

		int idVendedor = usuarioAtual.getId();
		return produto.remover(idProduto, idVendedor);
	}
	
	public List<ProdutoModel> listar() {
		return produto.listar();
	}
	

}
