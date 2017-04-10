
package controle;

import java.util.ArrayList;
import modelo.*;

public class PedagioControle {

    public void cadastraPedagio(PedagioBEAN p) {
        PedagioDAO pdao = new PedagioDAO();
        pdao.cadastrar(p);

    }

    public ArrayList<PedagioBEAN> listarALL() {
        PedagioDAO pdao = new PedagioDAO();
        return pdao.listarALL();

    }
    //a empresa possui 6 praças de pedagios
    //cidades: itauna, s s oeste, corrego f, piumhi, passos, pratápolis
    //gerente quer q controle lucros totais e os lucros de cada praça de pedágio
    //sistema deve armazenar inform de veiculos q passam na praça
    //inf:data, tipo do veiculo, e valor pago - de acorod com o tipo do veiculo e com o numero de eixos
    //funcionalidade: imprimir relatórios
    //valor total ganho em um det dia e em determinada praça de pedágio
    // usuario informa qual a praça ele quer ver 
    //informa a data e o sistema exibe o valor total arrecadado na data e a qtde de veiculos q passaram anquele dia
    //método cadastrar: pr~ça, pedagio, veiculo
}
