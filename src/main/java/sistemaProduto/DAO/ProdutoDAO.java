package sistemaProduto.DAO;

import sistemaProduto.model.ProdutoModel;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
	private ArrayList<ProdutoModel> produtos;
	
	
	public ProdutoDAO() {
		this.produtos = new ArrayList<>();
	}

	
	public boolean salvar(ProdutoModel mod){
		produtos.add(mod);
		return true;
	}
	
	public void remover(int id) {
		produtos.remove(id);
	}
	
	
	public List<ProdutoModel> listar() {
		return produtos;
	}

}
