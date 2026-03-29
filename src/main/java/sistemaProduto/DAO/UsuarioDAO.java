package sistemaProduto.DAO;

import sistemaProduto.model.UsuarioModel;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    private List<UsuarioModel> usario;

    public UsuarioDAO(){
        this.usario = new ArrayList<>();
    }

    public boolean cadastro(UsuarioModel mod){
       usario.add(mod);
        return true;
    }

   public boolean logar(UsuarioModel model){
        UsuarioModel usuario = new UsuarioModel();

        for(UsuarioModel m : usario){
            if(m.getNome().equalsIgnoreCase(usuario.getNome())
                    && m.getId() == (usuario.getId())){
                return true;
            }
        }
        return false;
    }

    public List<UsuarioModel> lista(){
        return usario;
    }

}
