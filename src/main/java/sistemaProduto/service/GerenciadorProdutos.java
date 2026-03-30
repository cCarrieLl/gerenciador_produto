package sistemaProduto.service;

import sistemaProduto.DAO.ProdutoDAO;
import sistemaProduto.model.ProdutoModel;

import java.util.List;

public class GerenciadorProdutos {
	private ProdutoDAO produto;
	//Fazer validação de entrada com senha para logar
	public GerenciadorProdutos() {
		this.produto = new ProdutoDAO();
	}
	
	public boolean adicionarProduto(int id, String nome, double preco, int estoque) {
		if(nome == null || nome.isBlank() ) {
			return false;
		}

		for(var produto : listar()){
			if(nome.equals(produto.getNome())){
				return false;
			}
		}
		
		if(preco < 0){return false;}
		
		ProdutoModel mod = new ProdutoModel();
		
		mod.setId(id);
		mod.setNome(nome);
		mod.setPreco(preco);
		mod.setEstoque(estoque);
		
		produto.salvar(mod);
		return true;
	}
	
	public boolean remover(int id){
		
		var linha = listar();
		if(id > linha.size()){
			return false;
		}
		
		produto.remover(id);
		return true;
	}
	
	public List<ProdutoModel> listar() {
		return produto.listar();
	}
	

}
