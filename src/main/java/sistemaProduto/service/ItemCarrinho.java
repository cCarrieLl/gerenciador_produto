package sistemaProduto.service;

import sistemaProduto.DAO.CarrinhoDAO;
import sistemaProduto.model.CarrinhoModel;

import java.util.List;

public class ItemCarrinho {
    private CarrinhoDAO carrinhoService;

    public ItemCarrinho(){
        this.carrinhoService = new CarrinhoDAO();
    }

    public boolean adicionarProduto(int idUsuario, int idProduto){
        return carrinhoService.adicionarItem(idUsuario, idProduto);
    }

    public boolean removerItemCarrinho(int idUsuario, int idProduto){
        return carrinhoService.removerItem(idUsuario, idProduto);
    }

    public boolean removerTodosItens(int idUsuario){
        return carrinhoService.limparCarrinho(idUsuario);
    }

    public List<CarrinhoModel> lista(int idUsuario){
        return carrinhoService.listarCarrinho(idUsuario);
    }





}
