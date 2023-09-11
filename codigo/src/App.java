import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

public class App {

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
        public static Equipamento criarEquipamentoDoArquivo(String linha) {
        String[] dados = linha.split(";");

        // int id = Integer.parseInt(dados[0]);
        String nome = dados[1];
        String descricao = dados[2];
        double valorDiaria = Double.parseDouble(dados[3]);

        Equipamento novoEquipamento = new Equipamento(nome, descricao, valorDiaria);

        return novoEquipamento;
    }

        public static void carregarEquipamentos(List<Equipamento> listaEquipamentos) {
            try {
                File file = new File(".\\codigo\\src\\equipamentos.txt");
                if(!file.exists()) {
                    file.createNewFile();
                }
                String dir = file.getAbsolutePath();
                System.out.println("Arquivo criado em: " + dir);
    
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
    
                String line;
                while((line = bufferedReader.readLine()) != null) {
                    Equipamento equipamento = criarEquipamentoDoArquivo(line);
                    listaEquipamentos.add(equipamento);
                }
    
                bufferedReader.close();
    
            } catch(IOException e) {
                System.out.println("Erro ao carregar os equipamentos.");
                e.printStackTrace();
            }
        }


    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        List<Cliente> listaClientes = new ArrayList<Cliente>();
        List<Equipamento> listaEquipamentos = new ArrayList<Equipamento>();
        List<Aluguel> listaAlugueis = new ArrayList<Aluguel>();
        //String diretorioAtual = System.getProperty("user.home") + "\\AppData\\Local\\Temp";

        carregarClientes(listaClientes);
        carregarEquipamentos(listaEquipamentos);

        int choice = 0;

        do {
            //System.out.print("\033[H\033[2J"); // Limpa o console
            //System.out.flush();
            System.out.println("***** CONTROLE DE ALUGUEIS DE EQUIPAMENTOS *****");
            System.out.println("1 - Clientes\n2 - Equipamentos\n3 - Alugueis\n0 - Sair");
            System.out.print("Digite a opcao desejada: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    Cliente cliente = new Cliente();
                    System.out.print("\033[H\033[2J"); // Limpa o console
                    System.out.flush();
                    System.out.println("***** CONTROLE DE ALUGUEIS DE EQUIPAMENTOS / CLIENTES *****");
                    System.out.println("1 - Cadastrar\n2 - Editar\n3 - Excluir\n4 - Lista de Clientes\n0 - Sair");
                    System.out.print("Digite a opcao desejada: ");
                    choice = scanner.nextInt();

                    switch (choice) {
                        case 1:
                            System.out.print("\033[H\033[2J"); // Limpa o console
                            System.out.flush();
                            cliente.cadastrarCliente(scanner, listaClientes);
                            Cliente.salvarClientes(listaClientes);
                            
                            break;
                        case 2:
                            System.out.println("Cliente editado com sucesso!");
                            break;
                        case 3:
                            System.out.println("Cliente excluído com sucesso!");
                            break;
                        case 4:
                            cliente.listarClientes(listaClientes);
                            break;
                        default:
                            System.out.println("Opção inválida!");
                            break;
                    }
                    break;
                case 2:
                    System.out.println("\n\n***** CONTROLE DE ALUGUEIS DE EQUIPAMENTOS / EQUIPAMENTOS *****");
                    System.out.println("1 - Cadastrar\n2 - Editar\n3 - Excluir\n4 - Lista de Equipamentos\n0 - Sair");
                    choice = scanner.nextInt();
                    Equipamento equipamento = new Equipamento();

                    switch (choice) {
                        case 1:
                            equipamento.cadastrarEquipamento(scanner, listaEquipamentos);
                            break;
                        case 2:
                            System.out.println("Equipamento editado com sucesso!");
                            break;
                        case 3:
                            System.out.println("Equipamento excluído com sucesso!");
                            break;
                        case 4:
                            Equipamento.listarEquipamentos(listaEquipamentos);
                            break;
                        case 0:
                            System.out.println("Saindo...");
                            break;
                        default:
                            System.out.println("Opção inválida!");
                            break;
                    }
                    break;
                case 3:
                    System.out.println("***** CONTROLE DE ALUGUEIS DE EQUIPAMENTOS / ALUGUEIS *****");
                    System.out.println("1 - Cadastrar\n2 - Editar\n3 - Excluir\n4 - Lista de Alugueis\n0 - Sair");
                    choice = scanner.nextInt();
                    Aluguel aluguel = new Aluguel();

                    switch (choice) {
                        case 1:
                            aluguel.cadastrarAluguel(scanner, listaClientes, listaEquipamentos, listaAlugueis);
                            break;
                        case 2:
                            System.out.println("Aluguel editado com sucesso!");
                            break;
                        case 3:
                            System.out.println("Aluguel excluído com sucesso!");
                            break;
                        case 4:
                            Aluguel.listarAlugueis(listaAlugueis);
                            break;
                        case 0:
                            System.out.println("Saindo...");
                            break;
                        default:
                            System.out.println("Opção inválida!");
                            break;
                    }
                    break;
                case 0:
                    System.out.println("Saindo...");
            }
            
        } while (choice != 0);

        scanner.close();
    }
}
