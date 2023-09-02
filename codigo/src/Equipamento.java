import java.util.List;
import java.util.Scanner;

public class Equipamento {
    private static int contadorIDs = 0;

    private int id;
    private String descricao;
    private float valorDiaria;

    public Equipamento(int id, String descricao, float valorDiaria) {
        this.id = id;
        this.descricao = descricao;
        this.valorDiaria = valorDiaria;
        contadorIDs++;
    }

    public String getDescricao() {
        return descricao;
    }

    public float getValorDiaria() {
        return valorDiaria;
    }

    public static void cadastrarEquipamento(Scanner scanner, List<Equipamento> listaEquipamentos) {
        System.out.println("\n***** CONTROLE DE ALUGUEIS DE EQUIPAMENTOS / EQUIPAMENTO / CADASTRAR *****");
        System.out.print("Digite a descricao do equipamento: ");
        scanner.nextLine();
        String descricao = scanner.nextLine();

        System.out.print("Digite o valor da diária do equipamento: ");
        float valorDiaria = scanner.nextFloat();

        Equipamento novoEquipamento = new Equipamento(contadorIDs, descricao, valorDiaria);
        listaEquipamentos.add(novoEquipamento);
    }

    public static void listarEquipamentos(List<Equipamento> listaEquipamentos) {
        System.out.println("\n***** CONTROLE DE ALUGUEIS DE EQUIPAMENTOS / EQUIPAMENTO / LISTA DE EQUIPAMENTOS *****");
        for (Equipamento equipamento : listaEquipamentos) {
            System.out.println("ID: " + equipamento.id);
            System.out.println("Descrição: " + equipamento.descricao);
            System.out.println("Valor da diária: " + equipamento.valorDiaria);
            System.out.print("\n");
        }
    }

    public static Equipamento buscarEquipamentoPorID(List<Equipamento> listaEquipamentos, int idProcurado) {
        for (Equipamento equipamento : listaEquipamentos) {
            if (equipamento.id == idProcurado) {
                return equipamento;
            }
        }
        return null;
    }
}
