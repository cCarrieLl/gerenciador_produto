package sistemaProduto.model;

public class ProdutoModel {
	private int id;
	private String nomeProduto;
	private String vendedor;
	private double preco;
	private int estoque;
	//private UsuarioModel vendedor;

	//public void setVendedor(UsuarioModel vendedor){this.vendedor = vendedor;}
	//public UsuarioModel getVendedor(){return vendedor;}

	public void setEstoque(int estoque){this.estoque = estoque;}
	public int getEstoque(){return estoque;}
	
	public void setId(int id){this.id = id;}
	public int getId() {return id;}

	public void setNomeProduto(String nomeProduto){this.nomeProduto = nomeProduto;}
	public String getNomeProduto(){return nomeProduto;}

	public void setVendedor(String vendedor) {this.vendedor = vendedor;}
	public String getVendedor(){return vendedor;}
	
	public void setPreco(double preco) {this.preco = preco;}
	public double getPreco() {return preco;}

	/*public String produtos(){
		return "ID:" + id + " | Nome:" + vendedor + " | Preço: R$";
	}*/
	
	public ProdutoModel(){}
	

}
