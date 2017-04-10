
package controle;

import java.util.ArrayList;
import modelo.PedagioBEAN;
import modelo.PedagioDAO;
import modelo.PracaBEAN;
import modelo.PracaDAO;


public class PracaControle {
    
     public void cadastraPraca(PracaBEAN p){
        PracaDAO pdao = new PracaDAO();
        pdao.cadastrar(p);
        
    }
     public ArrayList<PracaBEAN> mostrarPraca() {
        PracaDAO pdao = new PracaDAO();
        return pdao.listarALL();

    }
}
