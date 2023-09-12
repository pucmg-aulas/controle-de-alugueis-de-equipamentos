# Instruções de uso - Sistema de aluguel de equipamentos 

Esse software tem a função de facilitar para que uma empresa de construção civil possa realizar todo o processo de registro de clientes, equipamentos e aluguéis do seu negócio. 

## Inicialização e interface 

Ao iniciar o programa, o usuário terá acesso ao menu de opções sobre quais dos tópicos deseja acessar: 

 1. Clientes
 2. Equipamentos
 3. Alugueis
 0. Sair

 Em cada uma das opções de 1 à 3, haverão novas opções listadas em submenus, que representam as funcionalidades que podem ser escolhidas pelo usuário, de acordo com sua necessidade.

 ## Funcionalidades principais

 ### Opção 1 - Clientes 
 
1. Cadastrar: cadastrar um novo cliente no banco de dados, inserindo todos seus dados pedidos.
1. Lista de Clientes: mostra a lista de clientes já cadastrados, com suas informações.
0. Sair: sai do submenu e volta ao menu principal.

### Opção 2 - Equipamentos 

1. Cadastrar: cadastra um equipamento e suas informações necessárias para o aluguel. 
1. Lista de Equipamentos: mostra a lista de equipamentos cadastrados e disponíveis para compra. 
0. Sair: sai do submenu e volta ao menu principal.

### Opção 3 - Aluguéis

1. Cadastrar: cadastra um aluguel, pegando informações do cliente que está alugando e do equipamento que será alugado. 
1. Lista de Alugueis: mostra o registro de equipamentos alugados. 
0. Sair

## Testes 

|Teste|Entrada|Saída|
|-------|--------|------|
|Opções dos menus e submenus| Opção não listada | Mensagem de opção inválida |
|Nome do cliente errado| Números | Mensagem de erro e pede para digitar novamente|
|CPF do cliente errado | Letras e caracteres especiais | Mensagem de erro e pede para digitar novamente |
|Endereço do cliente errado| Espaço vazio | Mensagem de erro e pede para digitar novamente|
|Telefone do cliente errado | Letras e caracteres especiais | Mensagem de erro e pede para digitar novamente|
|Email do cliente errado | Endereço errado, sem domínio de email |Mensagem de erro e pede para digitar novamente|
|ID de cliente não cadastrado no aluguel| ID não cadastrado | Pede pra digitar novamente |
|ID de equipamento não cadastrado no aluguel | ID não cadastrado | Pede para digitar novamente|

