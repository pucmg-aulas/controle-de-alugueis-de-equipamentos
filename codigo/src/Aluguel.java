import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Aluguel {
    private static int contadorIDs = 0;

    private int id;
    private Cliente cliente;
    private Equipamento equipamento;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private double valorTotal;

    public Aluguel() {
        this.id = -1;
        this.cliente = null;
        this.equipamento = null;
        this.dataInicio = null;
        this.dataFim = null;
        this.valorTotal = 0;
    }

    public Aluguel(Cliente cliente, Equipamento equipamento, LocalDate dataInicio, LocalDate dataFim, float valorTotal) {
        this.id = contadorIDs;
        this.cliente = cliente;
        this.equipamento = equipamento;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.valorTotal = valorTotal;
        contadorIDs++;
    }

    public void cadastrarAluguel(Scanner scanner, List<Cliente> listaClientes, List<Equipamento> listaEquipamentos, List<Aluguel> listaAlugueis) {
        int idCliente, idEquipamento;
        List<Integer> alugueis = new ArrayList<Integer>();

        System.out.println("\n***** CONTROLE DE ALUGUEIS DE EQUIPAMENTOS / ALUGUEL / CADASTRAR *****");
        do {
            System.out.print("Digite o ID do cliente que está alugando: ");
            scanner.nextLine();
            idCliente = scanner.nextInt();
        } while (Cliente.buscarClientePorID(listaClientes, idCliente) == null);
        System.out.println("Cliente não encontrado");
        Cliente cliente = Cliente.buscarClientePorID(listaClientes, idCliente);
        alugueis = cliente.getAlugueis();
        alugueis.add(this.id);
        cliente.setAlugueis(alugueis);


        do {
            System.out.print("Digite o ID do equipamento a ser alugado: ");
            idEquipamento = scanner.nextInt();
        } while (Equipamento.buscarEquipamentoPorID(listaEquipamentos, idEquipamento) == null);
        System.out.println("Equipamento não encontrado");
        Equipamento equipamento = Equipamento.buscarEquipamentoPorID(listaEquipamentos, idEquipamento);
     
        System.out.print("Digite a data de início do aluguel: ");
        scanner.nextLine();
        LocalDate dataInicio = LocalDate.parse(scanner.nextLine());

        System.out.print("Digite a data de fim do aluguel: ");
        LocalDate dataFim = LocalDate.parse(scanner.nextLine());

        Aluguel novoAluguel = new Aluguel(cliente, equipamento, dataInicio, dataFim, 0);
        listaAlugueis.add(novoAluguel);
    }

    public static void listarAlugueis(List<Aluguel> listaAlugueis) {
        System.out.println("\n***** CONTROLE DE ALUGUEIS DE EQUIPAMENTOS / ALUGUEL / LISTAR *****");
        for (Aluguel aluguel : listaAlugueis) {
            System.out.println("ID: " + aluguel.id);
            System.out.println("Cliente: " + aluguel.cliente.getNome());
            System.out.println("Equipamento: " + aluguel.equipamento.getDescricao());
            System.out.println("Dias Alugados: " + aluguel.calcularDiasAlugados());
            System.out.println("Valor Total: R$" + aluguel.calcularValorTotal());
            System.out.print("\n");
        }
    }

    public int calcularDiasAlugados() {
        int diasAlugados = (int) ChronoUnit.DAYS.between(dataInicio, dataFim);
        return diasAlugados;
    }

    public double calcularValorTotal() {
        int diasAlugados = calcularDiasAlugados();
        valorTotal = diasAlugados * equipamento.getValorDiaria();
        return valorTotal;
    }
}
