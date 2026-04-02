package sistemaProduto;

import sistemaProduto.conexao.ConexaoBanco;
import sistemaProduto.view.Interface;

public class Main {
	public static void main(String[] args) {
		Interface usuario = new Interface();
		ConexaoBanco banco = new ConexaoBanco();

		banco.conectar();
		usuario.iniciar();
		
		
	}

}
