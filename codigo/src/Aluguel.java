import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.Month;

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

    public Aluguel(Cliente cliente, Equipamento equipamento, LocalDate dataInicio, LocalDate dataFim,
            double valorTotal) {
        this.id = contadorIDs;
        this.cliente = cliente;
        this.equipamento = equipamento;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.valorTotal = valorTotal;
        contadorIDs++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    @Override
    public String toString() {
        return getId() +
                ";" + getCliente().getId() +
                ";" + getEquipamento().getId() +
                ";" + getDataInicio() +
                ";" + getDataFim() +
                ";" + getValorTotal();
    }

    public void imprimirMes() {
        System.out.println("ID: " + id);
        System.out.println("Cliente: " + cliente.getNome());
        System.out.println("Equipamento: " + equipamento.getNome());
        System.out.println("Dias Alugados: " + calcularDiasAlugadosMes(dataInicio, dataFim));
        System.out.println("Valor Total: R$"
                + calcularValorTotalMes(calcularDiasAlugadosMes(dataInicio, dataFim), equipamento.getValorDiaria()));
        System.out.print("\n");
    }

    public Cliente buscarClientePorID(List<Cliente> listaClientes, int idProcurado) {
        /* DAVI TEM QUE VALIDAR E DOCUMENTAR */
        for (Cliente cliente : listaClientes) {
            if (cliente.getId() == idProcurado) {
                return cliente;
            }
        }
        return null;
    }

    public Equipamento buscarEquipamentoPorID(List<Equipamento> listaEquipamentos, int idProcurado) {
        for (Equipamento equipamento : listaEquipamentos) {
            if (equipamento.getId() == idProcurado) {
                return equipamento;
            }
        }
        return null;
    }

    public void cadastrarAluguel(Scanner scanner, List<Cliente> listaClientes, List<Equipamento> listaEquipamentos,
            List<Aluguel> listaAlugueis) {
        int idCliente, idEquipamento;
        List<Integer> alugueis = new ArrayList<Integer>();

        System.out.println("\n***** CONTROLE DE ALUGUEIS DE EQUIPAMENTOS / ALUGUEL / CADASTRAR *****");
        do {
            System.out.print("Digite o ID do cliente que está alugando: ");
            scanner.nextLine();
            idCliente = scanner.nextInt();
        } while (buscarClientePorID(listaClientes, idCliente) == null);
        Cliente cliente = buscarClientePorID(listaClientes, idCliente);
        alugueis = cliente.getAlugueis();
        alugueis.add(this.id);
        cliente.setAlugueis(alugueis);

        do {
            System.out.print("Digite o ID do equipamento a ser alugado: ");
            idEquipamento = scanner.nextInt();
        } while (buscarEquipamentoPorID(listaEquipamentos, idEquipamento) == null);
        Equipamento equipamento = buscarEquipamentoPorID(listaEquipamentos, idEquipamento);

        // DAVI ARRUMAR AS DATAS
        System.out.print("Digite a data de início do aluguel: ");
        scanner.nextLine();
        LocalDate dataInicio = LocalDate.parse(scanner.nextLine());

        System.out.print("Digite a data de fim do aluguel: ");
        LocalDate dataFim = LocalDate.parse(scanner.nextLine());

        Aluguel novoAluguel = new Aluguel(cliente, equipamento, dataInicio, dataFim,
                calcularValorTotal(equipamento.getValorDiaria(), dataInicio, dataFim));
        listaAlugueis.add(novoAluguel);
    }

    public void salvarAlugueis(List<Aluguel> listaAlugueis) {
        try {
            File file = new File(".\\codigo\\src\\alugueis.txt");
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (Aluguel aluguel : listaAlugueis) {
                bufferedWriter.write(aluguel.toString());
                bufferedWriter.newLine();
            }

            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Erro ao salvar o cliente.");
            e.printStackTrace();
        }
    }

    public int calcularDiasAlugados(LocalDate dataInicio, LocalDate dataFim) {
        int diasAlugados = (int) ChronoUnit.DAYS.between(dataInicio, dataFim);
        return diasAlugados;
    }

    public int calcularDiasAlugadosMes(LocalDate dataInicio, LocalDate dataFim) {

        Month mesInicio = dataInicio.getMonth();
        Month mesFim = dataFim.getMonth();
        int diasAlugadosMes = 0;

        if (mesInicio != mesFim) {

            if (mesInicio == Month.APRIL || mesInicio == Month.JUNE || mesInicio == Month.SEPTEMBER
                    || mesInicio == Month.NOVEMBER) {
                diasAlugadosMes = (int) ChronoUnit.DAYS.between(dataInicio,
                        LocalDate.of(dataInicio.getYear(), dataInicio.getMonth(), 30));
            } else if (mesInicio == Month.FEBRUARY) {
                diasAlugadosMes = (int) ChronoUnit.DAYS.between(dataInicio,
                        LocalDate.of(dataInicio.getYear(), dataInicio.getMonth(), 29));
            } else {
                diasAlugadosMes = (int) ChronoUnit.DAYS.between(dataInicio,
                        LocalDate.of(dataInicio.getYear(), dataInicio.getMonth(), 31));
            }
        } else if (mesInicio == mesFim) {
            diasAlugadosMes = calcularDiasAlugados(dataInicio, dataFim);
        }
        return diasAlugadosMes;
    }

    public double calcularValorTotal(double valorDiaria, LocalDate dataInicio, LocalDate dataFim) {
        int diasAlugados = calcularDiasAlugados(dataInicio, dataFim);
        valorTotal = diasAlugados * valorDiaria;
        return valorTotal;
    }

    public double calcularValorTotalMes(int diasAlugadosMes, double valorDiaria) {
        valorTotal = diasAlugadosMes * valorDiaria;
        return valorTotal;
    }

    public List<Aluguel> alugueisPorMes(List<Aluguel> listaAlugueis, Month mes) {
        // dados
        List<Aluguel> result = new ArrayList<Aluguel>();
        Month mesInicio, mesFim;
        Aluguel aluguel;
        double faturamentoTotal = 0.0;
        int diasAlugados;

        System.out.println("Analise de alugueis do mes " + mes + "\n");

        // percorrendo pela lista de alugueis
        for (int i = 0; i < listaAlugueis.size(); i++) {
            aluguel = listaAlugueis.get(i);
            mesInicio = aluguel.getDataInicio().getMonth();
            mesFim = aluguel.getDataFim().getMonth();

            if (mesInicio == mes && mesInicio == mesFim) {
                faturamentoTotal = faturamentoTotal + aluguel.getValorTotal();
                aluguel.imprimirMes();
            } else if (mesInicio == mes && mesInicio != mesFim) {

                if (mesInicio == Month.APRIL || mesInicio == Month.JUNE || mesInicio == Month.SEPTEMBER
                        || mesInicio == Month.NOVEMBER) {
                    diasAlugados = (int) ChronoUnit.DAYS.between(aluguel.getDataInicio(),
                            LocalDate.of(aluguel.getDataInicio().getYear(), aluguel.getDataInicio().getMonth(), 30));
                } else if (mesInicio == Month.FEBRUARY) {
                    diasAlugados = (int) ChronoUnit.DAYS.between(aluguel.getDataInicio(),
                            LocalDate.of(aluguel.getDataInicio().getYear(), aluguel.getDataInicio().getMonth(), 29));
                } else {
                    diasAlugados = (int) ChronoUnit.DAYS.between(aluguel.getDataInicio(),
                            LocalDate.of(aluguel.getDataInicio().getYear(), aluguel.getDataInicio().getMonth(), 31));
                }

                faturamentoTotal = faturamentoTotal + (diasAlugados * aluguel.getEquipamento().getValorDiaria());
                aluguel.imprimirMes();
            }
        }

        System.out.println("Faturamento total do mes: R$" + faturamentoTotal);

        return result;
    }
}
