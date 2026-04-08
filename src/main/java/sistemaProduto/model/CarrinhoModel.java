package sistemaProduto.model;

public class CarrinhoModel {
    private int id;
    private String nomeUsuario;
    private String nomeProduto;
    private double preco;
    private int idUsuario;
    private int idProduto;

    public void setId(int id){this.id = id;}
    public int getId(){return id;}

    public void setNomeUsuario(String nomeUsuario){this.nomeUsuario = nomeUsuario;}
    public String getNome(){return nomeUsuario;}

    public void setNomeProduto(String nomeProduto){this.nomeProduto = nomeProduto;}
    public String getNomeProduto(){return nomeProduto;}

    public void setPreco(double preco){this.preco = preco;}
    public double getPreco(){return preco;}

    public void setIdUsuario(int idUsuario){this.idUsuario = idUsuario;}
    public int getIdUsuario(){return idUsuario;}

    public void setIdProduto(int idProduto){this.idProduto = idProduto;}
    public int getIdProduto(){return idProduto;}



}
