package sistemaProduto.service;

import sistemaProduto.DAO.UsuarioDAO;
import sistemaProduto.model.UsuarioModel;
//import sistemaProduto.model.VendedorModel;

import java.util.List;

public class GerenciadorUsuario {
    private UsuarioDAO usuario;
     private UsuarioModel usuarioAtual;

     public void setUsuarioModel(UsuarioModel usuarioAtual){this.usuarioAtual = usuarioAtual;}
     public UsuarioModel getUsuarioModel(){return usuarioAtual;}


    public GerenciadorUsuario(){
        this.usuario = new UsuarioDAO();
    }

    public boolean login(String email, String senha){
        //UsuarioModel model = new UsuarioModel();
        var lista = usuario.lista();

        for(UsuarioModel m : lista){
            if(m.getEmail().equals(email) && m.getSenha().equals(senha)){
                usuarioAtual = m;
                return true;
            }
        }
        return false;

    }

    public List<UsuarioModel> lista(){
        return usuario.lista();
    }


    public boolean cadastro(String email, String senha, String escolha) {

        for(UsuarioModel v : lista()){
            if(email.equalsIgnoreCase(v.getNome()) && senha.equals(v.getSenha())){
                return false;
            }
        }


        return true;
    }


}
