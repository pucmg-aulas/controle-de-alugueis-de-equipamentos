import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Equipamento {
    private static int contadorIDs = 0;

    private int id;
    private String nome;
    private String descricao;
    private double valorDiaria;

    public Equipamento() {
        this.nome=null;
        this.descricao = null;
        this.valorDiaria = 0;
    }

    public Equipamento(String nome, String descricao, double valorDiaria) {
        this.nome = nome;
        this.descricao = descricao;
        this.valorDiaria = valorDiaria;
        this.id = contadorIDs;
        contadorIDs++;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(double valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    public void cadastrarEquipamento(Scanner scanner, List<Equipamento> listaEquipamentos) {
        System.out.println("\n***** CONTROLE DE ALUGUEIS DE EQUIPAMENTOS / EQUIPAMENTO / CADASTRAR *****");
        System.out.println("Digite o nome do quipamento: ");
        scanner.nextLine();
        String nome = scanner.nextLine();

        System.out.print("Digite a descricao do equipamento: ");
        String descricao = scanner.nextLine();

        System.out.print("Digite o valor da diária do equipamento: ");
        double valorDiaria = scanner.nextDouble();

        Equipamento novoEquipamento = new Equipamento(nome, descricao, valorDiaria);
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

    @Override
    public String toString() {
        return getId() +
            ";" + getNome() +
            ";" + getDescricao() +
            ";" + getValorDiaria();
    }

    public static void salvarEquipamentos(List<Equipamento> listaEquipamentos) {
        try {
            File file = new File(".\\codigo\\src\\equipamentos.txt");
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for(Equipamento equipamento: listaEquipamentos) {
                bufferedWriter.write(equipamento.toString());
                bufferedWriter.newLine();
            }

            bufferedWriter.close();
        } catch(IOException e) {
            System.out.println("Erro ao salvar o equipamento.");
            e.printStackTrace();
        }
    }
}
