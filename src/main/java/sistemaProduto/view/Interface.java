package sistemaProduto.view;

import sistemaProduto.model.UsuarioModel;
import sistemaProduto.service.GerenciadorProdutos;
import sistemaProduto.service.GerenciadorUsuario;
import sistemaProduto.service.ItemCarrinho;

import java.util.Scanner;

public class Interface {
	private final GerenciadorProdutos gerenciador;
	private final GerenciadorUsuario usuario;
	private final ItemCarrinho carrinho;
	private final Scanner scanner;
	private UsuarioModel usuarioAtual;
	private boolean estaLogado = false;

	public Interface(){
		this.scanner = new Scanner(System.in);
		this.gerenciador = new GerenciadorProdutos();
		this.carrinho = new ItemCarrinho();
		this.usuario = new GerenciadorUsuario();

	}


	public int menuInicial() {
		System.out.println("""
        1 - Logar
        2 - Criar Conta
        3 - Sair
    """);

		int choice = scanner.nextInt();
		scanner.nextLine();
		return choice;
	}

	public void menuUsuario() {
		int escolha = 0;

		while (escolha != 6) {
			System.out.println("""
            1 - Adicionar Item ao Carrinho
            2 - Listar Carrinho
            3 - Remover Item
            4 - Limpar Carrinho
            5 - Listar Produtos
            6 - Sair
        """);

			escolha = scanner.nextInt();
			scanner.nextLine();

			switch (escolha) {
				case 1 -> adicionaItemCarrinho();
				case 2 -> listaCarrinho();
				case 3 -> removerItemCarrinho();
				case 4 -> limparTodoCarrinho();
				case 5 -> listar();
				case 6 -> System.out.println("Saindo...");
				default -> System.out.println("Inválido");
			}
		}
	}

	public void menuVendedor() {
		int escolha = 0;

		while (escolha != 4) {
			System.out.println("""
            1 - Adicionar Produto
            2 - Remover Produto
            3 - Listar Produtos
            4 - Sair
        """);

			escolha = scanner.nextInt();
			scanner.nextLine();

			switch (escolha) {
				case 1 -> adicionarProduto();
				case 2 -> removerProduto();
				case 3 -> listar();
				case 4 -> System.out.println("Saindo...");
				default -> System.out.println("Inválido");
			}
		}
	}

	public void iniciar() {
		while (true) {
			int choice = menuInicial();

			switch (choice) {
				case 1 -> {
					login();

					if (usuarioAtual.getTipo().equals("USUARIOCOMUM")) {
						menuUsuario();
					} else if (usuarioAtual.getTipo().equals("VENDEDOR")) {
						menuVendedor();
					}
				}

				case 2 -> criaConta();

				case 3 -> {
					System.out.println("Programa encerrado.");
					return;
				}

				default -> System.out.println("Entrada inválida.");
			}
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

		boolean sucesso =  usuario.buscarUsuario(email, senha);
		if(!sucesso){
			System.out.println("Erro, verifique se digitou corretamente ou se a conta existe .");
			return;
		}

		UsuarioModel usuarioAtual = usuario.getUsuarioAtual();
		if(usuarioAtual == null){
			System.out.println("Verifique se digitou corretamente o seu email e a sua senha.");
			return;
		}

		UsuarioModel u = usuario.getUsuarioAtual();
		if(u != null){
			this.usuarioAtual = u;
		}else{
			System.out.println("Usuário não cadastrado");
			return;
		}
		estaLogado = true;

		System.out.println("Login realizado com sucesso.");
		System.out.println("Seja muito bem vindo " + u.getNome());

	}

	public void criaConta(){
		if(estaLogado){
			System.out.println("Conta ja cadastrada.");
			return;
		}
		System.out.println("""
				Qual estilo de conta:
				1 - Cliente
				2 - Vendedor""");
		int escolha = scanner.nextInt();

		String tipo;

		if(escolha == 1){
			tipo = "USUARIOCOMUM";
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
			System.out.println("Erro na entrada do email.");
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
			System.out.println("Usuario já possui cadastrado.");
			return;
		}

		System.out.println("Conta criada com sucesso");

	}

	public void adicionarProduto(){
		UsuarioModel user = usuario.getUsuarioAtual();

		String tipo;
		if(user != null){
			tipo = user.getTipo();
		}else{
			System.out.println("Algo deu errado. Tente novamente.");
			return;
		}

		if(!estaLogado){
			System.out.println("E preciso estar logado");
			return;
		}

        if(tipo.equals("USUARIOCOMUM")){
			System.out.println("E necessário ser um vendedor para cadastrar algum produto.");
			return;
		}

		System.out.print("Digite o nome do produto:");
		var nome = scanner.nextLine();
		
		System.out.print("Digite o valor: R$");
		var valor = scanner.nextDouble();

		System.out.print("Digite a quantidade em estoque:");
		int estoque = scanner.nextInt();

		scanner.nextLine();


		int id = usuarioAtual.getId();
		boolean sucesso = gerenciador.adicionarProduto(nome, valor, estoque, id);
		
		if(!sucesso){
			System.out.println("Erro!!!, verifique as informações solicitadas e o que foi digitado");
			return;
		}
		
		System.out.println("Informações adicionadas com sucesso.");
		
	}

	public void adicionaItemCarrinho(){
		if(!estaLogado){
			System.out.println("E preiciso estar logado");
			return;
		}

		if(usuarioAtual.getTipo().equals("VENDEDOR")){
			System.out.println("É necessário ser um cliente para adicionar itens ao carrinho.");
			return;
		}

		System.out.println("Selecione o ID do produto que deseja:\n");
		listar();
		System.out.print("\nDigite o ID: ");
		int item = scanner.nextInt();

		boolean sucesso = carrinho.adicionarProduto(usuarioAtual.getId(), item);

		if(sucesso){
			System.out.println("Item adicionado com sucesso.");
		}else{
			System.out.println("Algo deu errado, tente novamente");
		}

	}

	public void removerItemCarrinho(){
		if(!estaLogado){
			System.out.println("E preciso estar logado ");
			return;
		}

		if(usuarioAtual.getTipo().equals("VENDEDOR")){
			System.out.println("E preciso estar logado.");
			return;
		}

		System.out.println("Digite o ID do produto para remover.");
		listaCarrinho();
		System.out.print("\nDigite o ID: ");
		int id = scanner.nextInt();

		boolean sucesso = carrinho.removerItemCarrinho(usuarioAtual.getId(), id);

		if(!sucesso){
			System.out.print("Algo deu errado, tente novamente.");
			return;
		}

		System.out.println("Item de ID " + id + " removido com sucesso.");
	}

	public void limparTodoCarrinho() {
		if (!estaLogado) {
			System.out.println("E preciso estar logado");
			return;
		}

		if (usuarioAtual.getTipo().equals("VENDEDOR")){
			System.out.println("E necessario ser um cliente");
			return;
		}


		boolean sucesso = carrinho.removerTodosItens(usuarioAtual.getId());

		if(!sucesso){
			System.out.println("Algo deu errado, tente novamente.");
			return;
		}

		System.out.println("Produto removido com sucesso.");
	}

	public void listaCarrinho(){
		if(!estaLogado){
			System.out.println("E preciso estar logado.");
			return;
		}
		if(usuarioAtual.getTipo().equals("VENDEDOR")){
			System.out.println("E necessario ser um cliente para ter um carrinho.");
			return;
		}

		var lista = carrinho.lista(usuarioAtual.getId());
		if(lista.isEmpty()){
			System.out.println("\n--- Carrinho de Compra ---");
			System.out.println("Esta vazia");
			System.out.println("====================");
			return;
		}

		System.out.println("\n--- Carrinho de Compra ---");
		for(var car : lista){
			System.out.println("ID: " + car.getId() +
					" | nome usuário: " + usuarioAtual.getNome() +
					" | ID produto: " + car.getIdProduto() +
					" | nome produto: " + car.getNomeProduto() +
					" | preco: " + car.getPreco());
		}
		System.out.println("====================");
	}

	public void listar() {
		UsuarioModel user = usuario.getUsuarioAtual();

		if(user == null){
			System.out.println("Algo deu errado. Tente novamente.");
			return;
		}

		if(!estaLogado){
			System.out.println("E preciso estar logado");
			return;
		}

		if(usuarioAtual.equals("USUARIOCOMUM")){
			System.out.println("E necessário ser um vendedor para ver produtos cadastrado.");
			return;
		}

		var lista = gerenciador.listar();

		System.out.println("\n--- LISTA DE PRODUTOS ---");

		if(lista.isEmpty()){
			System.out.println("Nenhum produto cadastrado.");
			System.out.println("====================\n");
			return;
		}
		
		for(var produto : lista) {
			System.out.println("Vendedor: " + produto.getVendedor() +
					" | ID:" + produto.getId() +
					" | Nome:" + produto.getNomeProduto() +
					" | Preço:R$" + produto.getPreco() +
					" | Quantidade em Estoque:" + produto.getEstoque());
		}

		System.out.println("====================\n");
	}
	
	public void removerProduto() {
		if(!estaLogado){
			System.out.println("E preciso estar logado");
			return;
		}

		if(usuarioAtual.getTipo().equals("USUARIOCOMUM")){
			System.out.println("E necessário ser vendedor para cadastrar algum produto.");
			return;
		}

		var l = gerenciador.listar();

		if(l.isEmpty()){
			listar();
			return;
		}

		listar();

		System.out.println("\nEscolha o produto que quer remover(ID): ");
		System.out.print("\nDigite: ");
		var id= scanner.nextInt();

		boolean sucesso = gerenciador.removerItem(id, usuarioAtual);
		
		if(!sucesso) {
			System.out.println("Id não existente, tente novamente");
			return;
		}
		
		System.out.println("Produto removido com sucesso.");
	}
	
}