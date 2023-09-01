import java.util.List;

public class Equipamento {
    private int id;
    private String descricao;
    private float valorDiaria;

    public static Equipamento buscarEquipamentoPorID(List<Equipamento> listaEquipamentos, int idProcurado) {
        for (Equipamento equipamento : listaEquipamentos) {
            if (equipamento.id == idProcurado) {
                return equipamento;
            }
        }
        return null;
    }
}
