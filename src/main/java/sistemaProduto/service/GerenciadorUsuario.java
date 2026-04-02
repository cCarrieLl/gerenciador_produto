package sistemaProduto.service;

import sistemaProduto.DAO.UsuarioDAO;
import sistemaProduto.model.UsuarioModel;

import java.util.List;

public class GerenciadorUsuario {
    private UsuarioDAO usuario;
    private UsuarioModel usuarioAtual;

    public void setUsuarioModel(UsuarioModel usuarioAtual){this.usuarioAtual = usuarioAtual;}
    public UsuarioModel getUsuarioAtual(){return usuarioAtual;}


    public GerenciadorUsuario(){
        this.usuario = new UsuarioDAO();
    }

    public List<UsuarioModel> lista(){
        return usuario.listaUsuario();
    }


   public boolean cadastro(String nome, String email, String senha, String escolha) {
       UsuarioModel user = new UsuarioModel();

       if (usuario.buscarEmail(email, senha) != null) {
           return false;
       }

       user.setNome(nome);
       user.setEmail(email);
       user.setSenha(senha);
       user.setTipo(escolha);

       boolean sucesso = usuario.cadastroUsuario(nome, email, senha, escolha);
       if (sucesso) {
           usuarioAtual = user;
           return true;
       }

       return false;
   }

    public boolean buscarUsuario(String email, String senha){
         UsuarioModel user = usuario.buscarEmail(email, senha);

         if(user != null){
             usuarioAtual = user;
             return true;
         }

        return false;
    }

}
