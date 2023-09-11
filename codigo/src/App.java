import java.io.File;
import java.io.FilePermission;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        List<Cliente> listaClientes = new ArrayList<>();
        List<Equipamento> listaEquipamentos = new ArrayList<>();
        List<Aluguel> listaAlugueis = new ArrayList<>();
        //String diretorioAtual = System.getProperty("user.home") + "\\AppData\\Local\\Temp";

        Cliente.carregarClientes(listaClientes);

        int choice = 0;

        do {
            System.out.print("\033[H\033[2J"); // Limpa o console
            System.out.flush();
            System.out.println("***** CONTROLE DE ALUGUEIS DE EQUIPAMENTOS *****");
            System.out.println("1 - Clientes\n2 - Equipamentos\n3 - Alugueis\n0 - Sair");
            System.out.print("Digite a opcao desejada: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
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
                            Cliente.cadastrarCliente(scanner, listaClientes);
                            Cliente.salvarClientes(listaClientes);
                            break;
                        case 2:
                            System.out.println("Cliente editado com sucesso!");
                            break;
                        case 3:
                            System.out.println("Cliente excluído com sucesso!");
                            break;
                        case 4:
                            Cliente.listarClientes(listaClientes);
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

                    switch (choice) {
                        case 1:
                            Equipamento.cadastrarEquipamento(scanner, listaEquipamentos);
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

                    switch (choice) {
                        case 1:
                            Aluguel.cadastrarAluguel(scanner, listaClientes, listaEquipamentos, listaAlugueis);
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
