import java.util.List;
import java.util.Scanner;

public class Cliente {
    private static int contadorIDs = 0;

    private int id;
    private String nome;
    private String cpf;
    private String endereco;
    private String telefone;
    private String email;

    public Cliente(String nome, String cpf, String endereco, String telefone, String email) throws Exception {
        this.id = ++contadorIDs;

        if(nome.length() <= 45 && nome.matches("[a-zA-Z ]+$")) { // teste nome
            this.nome = nome;
        } else {
            throw new Error("erro: nome invalido!");
        }

        if(cpf.length() == 14 && cpf.matches("([0-9]{2}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[\\/]?[0-9]{4}[-]?[0-9]{2})|([0-9]{3}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[-]?[0-9]{2})")) { // teste cpf
            this.cpf = cpf;
        } else {
            throw new Error("erro: cpf invalido!");
        }

        this.endereco = endereco;

        if(telefone.length() <= 11 && telefone.matches("[0-9]+$")) {
            this.telefone = telefone;
        } else {
            throw new Error("erro: telefone invalido!");
        }
        
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public static void cadastrarCliente(Scanner scanner, List<Cliente> listaClientes) {
        System.out.println("\n***** CONTROLE DE ALUGUEIS DE EQUIPAMENTOS / CLIENTES / CADASTRAR *****");
        System.out.print("Digite o nome do cliente: ");
        scanner.nextLine();
        String nome = scanner.nextLine();
        System.out.print("Digite o CPF do cliente: ");
        String CPF = scanner.nextLine();
        System.out.print("Digite o endereço do cliente: ");
        String endereco = scanner.nextLine();
        System.out.print("Digite o telefone do cliente: ");
        String telefone = scanner.nextLine();
        System.out.print("Digite o e-mail do cliente: ");
        String email = scanner.nextLine();

        try {
            Cliente novoCliente = new Cliente(nome, CPF, endereco, telefone, email);
            listaClientes.add(novoCliente);
            System.out.printf("Cliente %s cadastrado com sucesso!\n\n", nome);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void editarCliente() {
        System.out.println("Cliente editado com sucesso!");
    }

    public void excluirCliente() {
        System.out.println("Cliente excluído com sucesso!");
    }

    public static void listarClientes(List<Cliente> listaClientes) {
        System.out.println("\n***** CONTROLE DE ALUGUEIS DE EQUIPAMENTOS / CLIENTES / LISTA DE CLIENTES *****");
        for (Cliente cliente : listaClientes) {
            System.out.println("ID: " + cliente.id);
            System.out.println("Nome: " + cliente.nome);
            System.out.println("CPF: " + cliente.cpf);
            System.out.println("Endereço: " + cliente.endereco);
            System.out.println("Telefone: " + cliente.telefone);
            System.out.println("E-mail: " + cliente.email);
            System.out.print("\n");
        }
    }

    public static Cliente buscarClientePorID(List<Cliente> listaClientes, int idProcurado) {
        for (Cliente cliente : listaClientes) {
            if (cliente.id == idProcurado) {
                return cliente;
            }
        }
        return null;
    }
}
