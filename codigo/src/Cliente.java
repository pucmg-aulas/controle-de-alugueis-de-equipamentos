import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cliente {
    private static int contadorIDs = 0;

    private int id;
    private String nome;
    private String cpf;
    private String endereco;
    private String telefone;
    private String email;

    public Cliente(String nome, String cpf, String endereco, String telefone, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.id = contadorIDs++;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getCPF(){
        return cpf;
    }

    public void setCPF(String cpf){
        this.cpf = cpf;
    }

    public String getEndereco(){
        return endereco;
    }

    public void setEndereco(String endereco){
        this.endereco = endereco;
    }

    public String getTelefone(){
        return telefone;
    }

    public void setTelefone(String telefone){
        this.telefone = telefone;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }
    
    public static void cadastrarCliente(Scanner scanner, List<Cliente> listaClientes) {
        /*
        String nome -> somente aceita letras
        String CPF -> somente aceita numeros, no formato de cpf xxx.xxx.xxx-xx e um cpf válido
        String endenreco
        String telefone -> somente aceita numeros
        String email -> somente aceita um email no formato usuario@dominio.com

        Função que cria o cadastro do cliente e valida se os dados são validos */

        System.out.println("***** CONTROLE DE ALUGUEIS DE EQUIPAMENTOS / CLIENTES / CADASTRAR *****");

        String nome, CPF, endereco, telefone, email;
        do {
            System.out.print("Digite o nome do cliente: ");
            scanner.nextLine();
            nome = scanner.nextLine();
        } while (!nome.matches("^[a-zA-Z]+$"));

        do {
            System.out.print("Digite o CPF do cliente no formato xxx.xxx.xxx-xx: ");
            CPF = scanner.nextLine();
        } while (validaCPF(CPF));

        do {
            System.out.print("Digite o endereço do cliente: ");
            endereco = scanner.nextLine();
        } while (endereco.isEmpty());

        do {
            System.out.print("Digite o telefone do cliente (apenas números): ");
            telefone = scanner.nextLine();
        } while (!telefone.matches("^[0-9]+$"));

        do {
            System.out.print("Digite o e-mail do cliente: ");
            email = scanner.nextLine();
        } while (!validaEmail(email));

        try {
            Cliente novoCliente = new Cliente(nome, CPF, endereco, telefone, email);
            listaClientes.add(novoCliente);
            System.out.printf("Cliente %s cadastrado com sucesso!\n\n", nome);
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar o cliente. Verifique os dados fornecidos.");
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return getId() +
            ";" + getNome() +
            ";" + getCPF() +
            ";" + getEndereco() +
            ";" + getTelefone() +
            ";" + getEmail();
    }

    public static void carregarClientes(List<Cliente> listaClientes) {
        try {
            File file = new File(".\\codigo\\src\\clientes.txt");
            if(!file.exists()) {
                file.createNewFile();
            }
            String dir = file.getAbsolutePath();
            System.out.println("Arquivo criado em: " + dir);

            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while((line = bufferedReader.readLine()) != null) {
                Cliente cliente = criarClienteDoArquivo(line);
                listaClientes.add(cliente);
            }

            bufferedReader.close();

        } catch(IOException e) {
            System.out.println("Erro ao carregar os clientes.");
            e.printStackTrace();
        }
    }

    public static Cliente criarClienteDoArquivo(String linha) {
        String[] dados = linha.split(";");

        // int id = Integer.parseInt(dados[0]);
        String nome = dados[1];
        String cpf = dados[2];
        String endereco = dados[3];
        String telefone = dados[4];
        String email = dados[5];

        Cliente novoCliente = new Cliente(nome, cpf, endereco, telefone, email);

        return novoCliente;
    }

    public static void salvarClientes(List<Cliente> listaClientes) {
        try {
            File file = new File(".\\codigo\\src\\clientes.txt");
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for(Cliente cliente: listaClientes) {
                bufferedWriter.write(cliente.toString());
                bufferedWriter.newLine();
            }

            bufferedWriter.close();
        } catch(IOException e) {
            System.out.println("Erro ao salvar os clientes.");
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
        /*Função que lista todos os clientes*/
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
        /*DAVI TEM QUE VALIDAR E DOCUMENTAR */
        for (Cliente cliente : listaClientes) {
            if (cliente.id == idProcurado) {
                return cliente;
            }
        }
        return null;
    }

    public static boolean validaCPF(String cpf) {
        // Verifica se o CPF possui o formato válido
        String cpfRegex = "^(\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2})|(\\d{11})$";
        boolean formatoValido = cpf.matches(cpfRegex);
    
        // Verifica se o CPF contém apenas dígitos numéricos
        boolean somenteNumericos = !cpf.matches("[0-9]+");
    
        if (!formatoValido || somenteNumericos || cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) {
            return false;
        }
    
        int[] digitos = new int[11];
        for (int i = 0; i < 11; i++) {
            digitos[i] = Character.getNumericValue(cpf.charAt(i));
        }
    
        int soma1 = 0;
        for (int i = 0; i < 9; i++) {
            soma1 += digitos[i] * (10 - i);
        }
        int resto1 = soma1 % 11;
        int digito1 = (resto1 < 2) ? 0 : 11 - resto1;
    
        int soma2 = 0;
        for (int i = 0; i < 10; i++) {
            soma2 += digitos[i] * (11 - i);
        }
    
        int resto2 = soma2 % 11;
        int digito2 = (resto2 < 2) ? 0 : 11 - resto2;
    
        return (digitos[9] == digito1) && (digitos[10] == digito2);
    }
    
    public static boolean validaEmail(String email) {
        // Essa função valida se o email está em um formato básico, como usuario@dominio.com
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }
    
}
