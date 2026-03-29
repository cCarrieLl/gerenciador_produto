## 🚧 Status do Projeto
O projeto ainda está em desenvolvimento. Algumas funcionalidades podem não estar funcionando corretamente no momento.

---

## 🧠 Funcionalidades Principais

### 🔐 1. Autenticação de Usuários
- Login com e-mail e senha  
- Cadastro de novas contas  
- Tipos de usuário:
  - Cliente  
  - Vendedor  
  - *(futuramente: Administrador)*  

👉 O sistema mantém o usuário autenticado através da variável `usuarioAtual`.

---

### 👤 2. Controle de Sessão
- Verificação se o usuário está logado  
- Bloqueio de ações para usuários não autenticados  
- Exibição das informações do usuário atual (nome e e-mail)  

---

### 🛒 3. Gerenciamento de Produtos
- ✔ Criar produto  
  - Requer usuário logado  
  - *(ideal: apenas vendedores podem criar)*  

- ✔ Listar produtos  
  - Disponível para todos os usuários  

- ✔ Remover produto  
  - *(ideal: apenas o criador do produto ou um administrador)*  

---

### 🔗 4. Relacionamentos
- Cada produto está associado a um vendedor (usuário que o criou)
