package sistemaProduto.model;

public class UsuarioModel {
    private String email;
    private String senha;
    private String nome;
    private int id;
    private String tipo;

    public UsuarioModel(){
    }

    public void setEmail(String email){this.email = email;}
    public String getEmail(){return email;}

    public void setSenha(String senha){this.senha = senha;}
    public String getSenha(){return senha;}

    public void setNome(String nome){this.nome = nome;}
    public String getNome(){return nome;}

    public void setId(int id){this.id = id;}
    public int getId(){return id;}

    public void setTipo(String tipo){this.tipo = tipo;}
    public String getTipo(){return tipo;}


}
