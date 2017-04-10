
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class PedagioDAO {
    private Connection connection;
    //objeto definido para usar os métodos execute,executeQuery e executeUpdate
    private PreparedStatement stmt;

    public PedagioDAO() {
        //inicializa a conexão com o BD
        this.connection = ConnectionFactory.getConnection();
    }

    public void cadastrar(PedagioBEAN c) {
        String sql = "insert into pedagio (peddata,"
                + " praca_pracod,"
                + " veiculo_veicod) values (?,?,?);";
        try {
            // prepared statement para inserção
            stmt = connection.prepareStatement(sql);
            // seta os valores
            stmt.setString(1, c.getPed_data());
            stmt.setInt(2, c.getPed_pracod());
            stmt.setInt(3, c.getPed_veicod());
            // executa
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<PedagioBEAN> listarALL() {
        String sql = "select * from pedagio;";
        ArrayList<PedagioBEAN> pedagioAL = new ArrayList<PedagioBEAN>();
        try {
            // prepared statement para seleção
            stmt = connection.prepareStatement(sql);            

            ResultSet rs = stmt.executeQuery();                        
            while (rs.next()) {
                PedagioBEAN c = new PedagioBEAN();
                c.setPedcod(rs.getInt(1));                
                c.setPed_data(rs.getString(2));
                c.setPed_pracod(rs.getInt(3));                
                c.setPed_veicod(rs.getInt(4));                
                pedagioAL.add(c);
            }
            stmt.close();//fecha conexão
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pedagioAL;

    }

    public PedagioBEAN localizar(int codigo) {
        String sql = "select * from pedagio where pedcod = ?;";
        PedagioBEAN c = new PedagioBEAN(); //crio objeto c para retorno
        try {
            // prepared statement para localização
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, codigo);

            // executa a consulta SQL
            ResultSet rs = stmt.executeQuery();

            //joga resultado da consulta no ArrayList
            while (rs.next()) {
                c.setPedcod(rs.getInt(1));
                c.setPed_pracod(rs.getInt(2));             
                c.setPed_veicod(rs.getInt(3));
                c.setPed_data(rs.getString(4));
            }
            stmt.close();
            return c;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean editar(PedagioBEAN c) {
        String sql = "update pedagio set peddata = ? ped_pracod,"
                + " ped_veicod where pedcod = ?;";

        try {
            /* metodo usado para criar um objeto que representa a instrução 
            SQL que será executada, sendo que é invocado através do objeto 
            Connection.
            
            Deve ser colocado dentro de um bloco try catch */
            stmt = connection.prepareStatement(sql);
            // seta os valores
            stmt.setString(1, c.getPed_data());
            stmt.setInt(2, c.getPed_pracod());
            stmt.setInt(3, c.getPed_veicod());
            stmt.setInt(4, c.getPedcod());
            

            // executa update
            int linhasAtualizadas = stmt.executeUpdate();
            stmt.close();
             //apenas se quiser saber quantas linhas foram alteradas
            if (linhasAtualizadas > 0) {
                System.out.println("Foram alterados "+linhasAtualizadas+" registros");
            }
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean remover(int codigo) {
        String sql = "delete from pedagio where pedcod = ?;";

        try {
            // prepared statement para inserção
            stmt = connection.prepareStatement(sql);

            // seta os valores
            stmt.setInt(1, codigo);

            // executa
            stmt.execute();
            
            stmt.close();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
}
