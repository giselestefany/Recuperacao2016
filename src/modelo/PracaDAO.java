
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class PracaDAO {
    private Connection connection;
    //objeto definido para usar os métodos execute,executeQuery e executeUpdate
    private PreparedStatement stmt;

    public PracaDAO() {
        //inicializa a conexão com o BD
        this.connection = ConnectionFactory.getConnection();
    }

    public void cadastrar(PracaBEAN c) {
        String sql = "insert into praca (pranome) values (?);";
        try {
            // prepared statement para inserção
            stmt = connection.prepareStatement(sql);
            // seta os valores
            stmt.setString(1, c.getPranome());
            
            // executa
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<PracaBEAN> listarALL() {
        String sql = "select * from praca;";
        ArrayList<PracaBEAN> pracaAL = new ArrayList<PracaBEAN>();
        try {
            // prepared statement para seleção
            stmt = connection.prepareStatement(sql);            

            ResultSet rs = stmt.executeQuery();                        
            while (rs.next()) {
                PracaBEAN c = new PracaBEAN();
                c.setPracod(rs.getInt(1));
                c.setPranome(rs.getString(2));
                
                pracaAL.add(c);
            }
            stmt.close();//fecha conexão
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pracaAL;

    }

    public PracaBEAN localizar(int codigo) {
        String sql = "select * from praca where pracod = ?;";
        PracaBEAN c = new PracaBEAN(); //crio objeto c para retorno
        try {
            // prepared statement para localização
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, codigo);

            // executa a consulta SQL
            ResultSet rs = stmt.executeQuery();

            //joga resultado da consulta no ArrayList
            while (rs.next()) {
                c.setPracod(rs.getInt(1));
                c.setPranome(rs.getString(2));                
            }
            stmt.close();
            return c;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean editar(PracaBEAN c) {
        String sql = "update praca set pranome = ? where pracod = ?;";

        try {
            /* metodo usado para criar um objeto que representa a instrução 
            SQL que será executada, sendo que é invocado através do objeto 
            Connection.
            
            Deve ser colocado dentro de um bloco try catch */
            stmt = connection.prepareStatement(sql);
            // seta os valores
            stmt.setString(1, c.getPranome());
            stmt.setInt(5, c.getPracod());

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
        String sql = "delete from praca where pracod = ?;";

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
