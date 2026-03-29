package sistemaProduto.model;

public class ProdutoModel {
	private int id;
	private String nome;
	private double preco;
	private int estoque;
//Acrescentar senha em todos model
	public void setEstoque(int estoque){this.estoque = estoque;}
	public int getEstoque(){return estoque;}
	
	public void setId(int id){this.id = id;}
	public int getId() {return id;}
	
	public void setNome(String nome) {this.nome = nome;}
	public String getNome(){return nome;}
	
	public void setPreco(double preco) {this.preco = preco;}
	public double getPreco() {return preco;}

	/*public String produtos(){
		return "ID:" + id + " | Nome:" + nome + " | Preço: R$";
	}*/
	
	public ProdutoModel(){}
	

}
