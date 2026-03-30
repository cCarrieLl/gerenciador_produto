package sistemaProduto.view;

//import sistemaProduto.model.UsuarioModel;
import sistemaProduto.model.UsuarioModel;
import sistemaProduto.service.GerenciadorProdutos;
import sistemaProduto.service.GerenciadorUsuario;
//import sistemaProduto.service.GerenciadorVendedor;

import java.util.Scanner;

public class Interface {
	private final GerenciadorProdutos gerenciador;
	private final GerenciadorUsuario usuario;
	private final Scanner scanner;
	private String tipoUsuario;
	private boolean estaLogado = false;
	//private UsuarioModel usuarioAtual;

	//public void setTipoUsuario(String tipoUsuario){this.tipoUsuario = tipoUsuario;}

//finalizar todas as opções
//Fazer listagem de produtos, vendedor, usuario e carinho de usuario
	public Interface(){
		this.scanner = new Scanner(System.in);
		this.gerenciador = new GerenciadorProdutos();
		this.usuario = new GerenciadorUsuario();

	}
	
	
	public void iniciar() {
		String opcao = """
		Escolha uma das opções:
		1 - Logar
		2 - Criar Conta
		3 - Adicionar Produto
		4 - Listar
		5 - Remover
		6 - Sair""";
		System.out.println(opcao);
		
		int escolha = 0;
		while(escolha != 6){
			escolha = scanner.nextInt();

			scanner.nextLine();

			switch (escolha) {
				case 1 -> login();
				case 2 -> criaConta();
				case 3 -> adicionar();
				case 4 -> listar();
				case 5 -> remover();
				case 6 -> System.out.println("Programa encerrado.");
				default -> System.out.println("Entrada inválida, tente novamente");
			}
			System.out.println(opcao);
		}
    }

	public void login(){
		if(estaLogado){
			System.out.println("Conta já logada.");
			return;
		}

		System.out.print("Digite o seu email: ");
		String email = scanner.nextLine();

		if(email == null || email.isBlank()){
			System.out.print("Entrada de email inválida, tente novamente:");
			return;
		}

		System.out.print("Digite a sua senha: ");
		String senha = scanner.nextLine();

		if(senha == null || senha.isBlank()){
			System.out.println("Entrada de senha inválida, tente novamente");
			return;
		}

		boolean sucesso =  usuario.login(email, senha);

		if(!sucesso){
			System.out.println("Erro, verifique se digitou corretamente ou se a conta existe .");
			return;
		}

		System.out.println("Login realizado com sucesso.");

		UsuarioModel usuarioAtual = usuario.getUsuarioAtual();

		if(usuarioAtual == null){
			System.out.println("Erro interno no login.");
			return;
		}

		UsuarioModel u = usuario.getUsuarioAtual();
		if(u != null){
			tipoUsuario = u.getTipo();
		}else{
			System.out.println("Usuário não cadastrado");
			return;
		}

		estaLogado = true;
		System.out.println("Seja muito bem vindo " + u.getNome());

	}

	public void criaConta(){

		System.out.println("""
				Qual estilo de conta:
				1 - Cliente
				2 - Vendedor""");
		int escolha = scanner.nextInt();

		String tipo;

		if(escolha == 1){
			tipo = "USUARIOCOMUN";
		}else if(escolha == 2){
			tipo = "VENDEDOR";
		}else{
			System.out.println("Entrada inválida, tente novamente.");
			return;
		}

		scanner.nextLine();
		System.out.println("Digite o seu email:");
		String email = scanner.nextLine();

		if (email == null || email.isBlank()) {
			System.out.println("Erro na entrada do email...");
			return;
		}

		System.out.println("Digite a sua senha: ");
		String senha = scanner.nextLine();

		if(senha == null || senha.isBlank()){
			System.out.println("Erro, senha inválida.");
		}

		System.out.print("Digite o nome do perfil:");
		String nome = scanner.nextLine();

		boolean sucesso = usuario.cadastro(nome, email, senha, tipo);
		if(!sucesso){
			System.out.println("Usuario já cadastrado.");
			return;
		}

		System.out.println("Conta criada com sucesso");

	}

	public void adicionar(){
		if(!estaLogado){
			System.out.println("E preciso estar logado");
			return;
		}

		if(tipoUsuario.equals("USUARIOCOMUN")){
			System.out.println("E necessário ser vendedor para cadastrar algum produto.");
			return;
		}

		System.out.println("Digite o ID do produto: ");
		var num = scanner.nextInt();
		if(num <= 0) {
			System.out.println("Valor de entrada de id, incorreto");
			return;
		}
		
		scanner.nextLine();
		System.out.println("Digite o nome do produto:");
		var nome = scanner.nextLine();
		
		System.out.println("Digite o valor: ");
		var valor = scanner.nextDouble();

		System.out.println("Digite a quantidade em estoque:");
		int estoque = scanner.nextInt();

		scanner.nextLine();
		
		boolean sucesso = gerenciador.adicionarProduto(num, nome, valor, estoque);
		
		if(!sucesso){
			System.out.println("Erro!!!, verifique as informações solicitadas e o que foi digitado");
			return;
		}
		
		System.out.println("Informações adicionadas com sucesso.");
		
	}
	
	public void listar() {
		if(!estaLogado){
			System.out.println("E preciso estar logado");
			return;
		}

		if(tipoUsuario.equals("USUARIOCOMUN")){
			System.out.println("E necessário ser vendedor para ver produtos cadastrado.");
			return;
		}
		var lista = gerenciador.listar();

		System.out.println("\n--- LISTA DE PRODUTOS ---");

		if(lista.isEmpty()){
			System.out.println("Nenhum produto cadastrado.");
		}
		
		for(var produto : lista) {
			System.out.println("ID:" + produto.getId() + " | Nome:" + produto.getNome() + " | Preço:R$" +
					produto.getPreco() + " | Quantidade em Estoque:" + produto.getEstoque());
		}

		System.out.println("====================\n");
	}
	
	public void remover() {
		if(!estaLogado){
			System.out.println("E preciso estar logado");
			return;
		}

		if(tipoUsuario.equals("USUARIOCOMUN")){
			System.out.println("E necessário ser vendedor para cadastrar algum produto.");
			return;
		}

		var l = gerenciador.listar();

		if(l.isEmpty()){
			listar();
			return;
		}

		listar();

		System.out.println("\nEscolha o produto que quer remover: ");
		System.out.print("\nDigite: ");
		var num = scanner.nextInt();

		num-=1;
		boolean sucesso = gerenciador.remover(num);
		
		if(!sucesso) {
			System.out.println("Id não existente, tente novamente");
			return;
		}

		
		System.out.println("Produto do Id " + (num +=1) + " removido com sucesso.");
	}
	
}
