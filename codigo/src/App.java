import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;
import java.time.LocalDate;
import java.io.FileReader;
import java.io.BufferedReader;

public class App {

    private List<Cliente> listaClientes;
    private List<Equipamento> listaEquipamentos;
    private List<Aluguel> listaAlugueis;

    public App() {
        listaClientes = new ArrayList<Cliente>();
        listaEquipamentos = new ArrayList<Equipamento>();
        listaAlugueis = new ArrayList<Aluguel>();
    }

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public List<Equipamento> getListaEquipamentos() {
        return listaEquipamentos;
    }

    public void setListaEquipamentos(List<Equipamento> listaEquipamentos) {
        this.listaEquipamentos = listaEquipamentos;
    }

    public List<Aluguel> getListaAlugueis() {
        return listaAlugueis;
    }

    public void setListaAlugueis(List<Aluguel> listaAlugueis) {
        this.listaAlugueis = listaAlugueis;
    }

    public Cliente criarClienteDoArquivo(String linha) {
        String[] dados = linha.split(";");

        String nome = dados[1];
        String cpf = dados[2];
        String endereco = dados[3];
        String telefone = dados[4];
        String email = dados[5];

        Cliente novoCliente = new Cliente(nome, cpf, endereco, telefone, email);

        return novoCliente;
    }

    public void carregarClientes() {
        try {
            File file = new File(".\\codigo\\src\\clientes.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            String dir = file.getAbsolutePath();
            System.out.println("Arquivo criado em: " + dir);

            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Cliente cliente = criarClienteDoArquivo(line);
                listaClientes.add(cliente);
            }

            bufferedReader.close();

        } catch (IOException e) {
            System.out.println("Erro ao carregar os clientes.");
            e.printStackTrace();
        }
    }

    public void listarClientes() {
        /* Função que lista todos os clientes */
        System.out.println("\n***** CONTROLE DE ALUGUEIS DE EQUIPAMENTOS / CLIENTES / LISTA DE CLIENTES *****");
        for (Cliente cliente : listaClientes) {
            System.out.println("ID: " + cliente.getId());
            System.out.println("Nome: " + cliente.getNome());
            System.out.println("CPF: " + cliente.getCPF());
            System.out.println("Endereço: " + cliente.getEndereco());
            System.out.println("Telefone: " + cliente.getTelefone());
            System.out.println("E-mail: " + cliente.getEmail());
            System.out.print("\n");
        }
    }

    public Cliente buscarClientePorID(int idProcurado) {
        /* DAVI TEM QUE VALIDAR E DOCUMENTAR */
        for (Cliente cliente : listaClientes) {
            if (cliente.getId() == idProcurado) {
                return cliente;
            }
        }
        return null;
    }

    public Equipamento criarEquipamentoDoArquivo(String linha) {
        String[] dados = linha.split(";");

        String nome = dados[1];
        String descricao = dados[2];
        double valorDiaria = Double.parseDouble(dados[3]);

        Equipamento novoEquipamento = new Equipamento(nome, descricao, valorDiaria);

        return novoEquipamento;
    }

    public void carregarEquipamentos() {
        try {
            File file = new File(".\\codigo\\src\\equipamentos.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            String dir = file.getAbsolutePath();
            System.out.println("Arquivo criado em: " + dir);

            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Equipamento equipamento = criarEquipamentoDoArquivo(line);
                listaEquipamentos.add(equipamento);
            }

            bufferedReader.close();

        } catch (IOException e) {
            System.out.println("Erro ao carregar os equipamentos.");
            e.printStackTrace();
        }
    }

    public void listarEquipamentos() {
        System.out.println("\n***** CONTROLE DE ALUGUEIS DE EQUIPAMENTOS / EQUIPAMENTO / LISTA DE EQUIPAMENTOS *****");
        for (Equipamento equipamento : listaEquipamentos) {
            System.out.println("ID: " + equipamento.getId());
            System.out.println("Nome: " + equipamento.getNome());
            System.out.println("Descrição: " + equipamento.getDescricao());
            System.out.println("Valor da diária: " + equipamento.getValorDiaria());
            System.out.print("\n");
        }
    }

    public Equipamento buscarEquipamentoPorID(int idProcurado) {
        for (Equipamento equipamento : listaEquipamentos) {
            if (equipamento.getId() == idProcurado) {
                return equipamento;
            }
        }
        return null;
    }

    public Aluguel criarAluguelDoArquivo(String linha) {
        String[] dados = linha.split(";");

        Cliente cliente = buscarClientePorID(Integer.parseInt(dados[1]));
        Equipamento equipamento = buscarEquipamentoPorID(Integer.parseInt(dados[2]));
        LocalDate dataInicio = LocalDate.parse(dados[3]);
        LocalDate dataFim = LocalDate.parse(dados[4]);
        double valorTotal = Double.parseDouble(dados[5]);

        Aluguel novoAluguel = new Aluguel(cliente, equipamento, dataInicio, dataFim, valorTotal);

        return novoAluguel;
    }

    public void carregarAlugueis() {
        try {
            File file = new File(".\\codigo\\src\\alugueis.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            String dir = file.getAbsolutePath();
            System.out.println("Arquivo criado em: " + dir);

            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Aluguel aluguel = criarAluguelDoArquivo(line);
                listaAlugueis.add(aluguel);
            }

            bufferedReader.close();

        } catch (IOException e) {
            System.out.println("Erro ao carregar os equipamentos.");
            e.printStackTrace();
        }
    }

    public void listarAlugueis() {
        System.out.println("\n***** CONTROLE DE ALUGUEIS DE EQUIPAMENTOS / ALUGUEL / LISTAR *****");
        for (Aluguel aluguel : listaAlugueis) {
            System.out.println("ID: " + aluguel.getId());
            System.out.println("Cliente: " + aluguel.getCliente().getNome());
            System.out.println("Equipamento: " + aluguel.getEquipamento().getDescricao());
            System.out.println("Dias Alugados: " + aluguel.calcularDiasAlugados());
            System.out.println("Valor Total: R$" + aluguel.calcularValorTotal());
            System.out.print("\n");
        }
    }

    public void listarAluguel(Aluguel aluguel) {

        System.out.println("ID: " + aluguel.getId());
        System.out.println("Cliente: " + aluguel.getCliente().getNome());
        System.out.println("Equipamento: " + aluguel.getEquipamento().getDescricao());
        System.out.println("Dias Alugados: " + aluguel.calcularDiasAlugados());
        System.out.println("Valor Total: R$" + aluguel.calcularValorTotal());
        System.out.print("\n");

    }

    public void listarAlugueisCliente(Scanner scanner) {
        System.out.println("\n***** CONTROLE DE ALUGUEIS DE EQUIPAMENTOS / ALUGUEL / LISTAR POR CLIENTE *****");
        int idCliente;

        System.out.println("Digite o ID do cliente: ");
        idCliente = scanner.nextInt();

        for (int i = 0; i < listaAlugueis.size(); i++) {
            if (listaAlugueis.get(i).getCliente().getId() == idCliente) {
                listarAluguel(listaAlugueis.get(i));
                System.out.println("");
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        App master = new App();
        // String diretorioAtual = System.getProperty("user.home") +
        // "\\AppData\\Local\\Temp";

        master.carregarClientes();
        master.carregarEquipamentos();
        master.carregarAlugueis();

        int choice = 0;

        do {
            // System.out.print("\033[H\033[2J"); // Limpa o console
            // System.out.flush();
            System.out.println("***** CONTROLE DE ALUGUEIS DE EQUIPAMENTOS *****");
            System.out.println("1 - Clientes\n2 - Equipamentos\n3 - Alugueis\n4 - Sair");
            System.out.print("Digite a opcao desejada: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    Cliente cliente = new Cliente();
                    System.out.print("\033[H\033[2J"); // Limpa o console
                    System.out.flush();
                    System.out.println("***** CONTROLE DE ALUGUEIS DE EQUIPAMENTOS / CLIENTES *****");
                    System.out.println("1 - Cadastrar\n2 - Lista de Clientes\n3 - Sair");
                    System.out.print("Digite a opcao desejada: ");
                    choice = scanner.nextInt();

                    switch (choice) {
                        case 1:
                            System.out.print("\033[H\033[2J"); // Limpa o console
                            System.out.flush();
                            cliente.cadastrarCliente(scanner, master.getListaClientes());
                            cliente.salvarClientes(master.getListaClientes());

                            break;
                        case 2:
                            master.listarClientes();
                            break;
                        case 3:
                            System.out.println("Saindo...");
                            break;
                        default:
                            System.out.println("Opção inválida!");
                            break;
                    }
                    break;
                case 2:
                    System.out.println("\n\n***** CONTROLE DE ALUGUEIS DE EQUIPAMENTOS / EQUIPAMENTOS *****");
                    System.out.println("1 - Cadastrar\n2 - Lista de Equipamentos\n3 - Sair");
                    choice = scanner.nextInt();
                    Equipamento equipamento = new Equipamento();

                    switch (choice) {
                        case 1:
                            equipamento.cadastrarEquipamento(scanner, master.getListaEquipamentos());
                            equipamento.salvarEquipamentos(master.getListaEquipamentos());
                            break;
                        case 2:
                            master.listarEquipamentos();
                            break;
                        case 3:
                            System.out.println("Saindo...");
                            break;
                        default:
                            System.out.println("Opção inválida!");
                            break;
                    }
                    break;
                case 3:
                    System.out.println("***** CONTROLE DE ALUGUEIS DE EQUIPAMENTOS / ALUGUEIS *****");
                    System.out.println(
                            "1 - Cadastrar\n2 - Lista de Alugueis\n3- Listar Alugueis de um Cliente\n4- Sair");
                    choice = scanner.nextInt();
                    Aluguel aluguel = new Aluguel();

                    switch (choice) {
                        case 1:
                            aluguel.cadastrarAluguel(scanner, master.getListaClientes(), master.getListaEquipamentos(),
                                    master.getListaAlugueis());
                            aluguel.salvarAlugueis(master.getListaAlugueis());
                            break;
                        case 2:
                            master.listarAlugueis();
                            break;
                        case 3:
                            master.listarAlugueisCliente(scanner);
                            break;
                        case 4:
                            System.out.println("Saindo...");
                            break;
                        default:
                            System.out.println("Opção inválida!");
                            break;
                    }
                    break;
                case 4:
                    System.out.println("Saindo...");
                default:
                    System.out.println("Opção inválida");
            }

        } while (choice != 4);

        scanner.close();
    }
}
