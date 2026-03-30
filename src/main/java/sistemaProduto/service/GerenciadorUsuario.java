package sistemaProduto.service;

import sistemaProduto.DAO.UsuarioDAO;
import sistemaProduto.model.UsuarioModel;
//import sistemaProduto.model.VendedorModel;

import java.util.List;

public class GerenciadorUsuario {
    private UsuarioDAO usuario;
    private UsuarioModel usuarioAtual;

     public void setUsuarioModel(UsuarioModel usuarioAtual){this.usuarioAtual = usuarioAtual;}
     public UsuarioModel getUsuarioAtual(){return usuarioAtual;}


    public GerenciadorUsuario(){
        this.usuario = new UsuarioDAO();
    }

    public boolean login(String email, String senha){
        UsuarioModel user = usuario.buscarEmail(email, senha);

        if(usuario != null){
            usuarioAtual = user;
            return true;
        }


        return false;

    }

    public List<UsuarioModel> lista(){
        return usuario.listaUsuario();
    }


   public boolean cadastro(String nome, String email, String senha, String escolha) {
        UsuarioModel user = new UsuarioModel();

        for(UsuarioModel m : lista()){
            if(email.equals(m.getEmail()) && senha.equals(m.getSenha())){
                return false;
            }
        }

        user.setNome(nome);
        user.setEmail(email);
        user.setSenha(senha);
        user.setTipo(escolha);

        usuarioAtual = user;

        return usuario.cadastro(nome, email, senha, escolha);
    }


}
