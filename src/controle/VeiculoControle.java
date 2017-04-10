
package controle;

import java.util.ArrayList;
import modelo.PracaBEAN;
import modelo.PracaDAO;
import modelo.VeiculoBEAN;
import modelo.VeiculoDAO;


public class VeiculoControle {

    public void cadastraVeiculo(VeiculoBEAN p) {
        VeiculoDAO pdao = new VeiculoDAO();
        pdao.cadastrar(p);

    }
     public ArrayList<VeiculoBEAN> mostrarVeiculo() {
        VeiculoDAO pdao = new VeiculoDAO();
        return pdao.listarALL();

    }
}
