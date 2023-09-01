import java.time.LocalDate;
import java.util.Scanner;

public class Aluguel {
    private int id;
    private Cliente cliente;
    private Equipamento equipamento;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private float valorTotal;

    public static void cadastrarAluguel(Scanner scanner) {
        System.out.println("\n***** CONTROLE DE ALUGUEIS DE EQUIPAMENTOS / ALUGUEL / CADASTRAR *****");
        System.out.print("Digite o ID do cliente que está alugando: ");
        int idCliente = scanner.nextInt();
        Cliente cliente = Cliente.buscarClientePorID(listaClientes, idCliente);

        System.out.print("Digite o ID do equipamento a ser alugado: ");
        int idEquipamento = scanner.nextInt();

    }

    public void calcularDiasAlugados() {
        System.out.println("Dias alugados calculados com sucesso!");
    }

    public void calcularValorTotal() {
        System.out.println("Valor total calculado com sucesso!");
    }
}
