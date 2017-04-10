
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VeiculoDAO {
    private Connection connection;
    //objeto definido para usar os métodos execute,executeQuery e executeUpdate
    private PreparedStatement stmt;

    public VeiculoDAO() {
        //inicializa a conexão com o BD
        this.connection = ConnectionFactory.getConnection();
    }

    public void cadastrar(VeiculoBEAN c) {
        String sql = "insert into veiculo (veitipo,"
                + " veivalor) values (?,?);";
        try {
            // prepared statement para inserção
            stmt = connection.prepareStatement(sql);
            // seta os valores
            stmt.setString(1, c.getVeitipo());
            stmt.setFloat(2, c.getVeivalor());
            
            // executa
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<VeiculoBEAN> listarALL() {
        String sql = "select * from veiculo;";
        ArrayList<VeiculoBEAN> veiculoAL = new ArrayList<VeiculoBEAN>();
        try {
            // prepared statement para seleção
            stmt = connection.prepareStatement(sql);            

            ResultSet rs = stmt.executeQuery();                        
            while (rs.next()) {
                VeiculoBEAN c = new VeiculoBEAN();
                c.setVeicod(rs.getInt(1));
                c.setVeitipo(rs.getString(2));
                c.setVeivalor(rs.getFloat(3));
                veiculoAL.add(c);
            }
            stmt.close();//fecha conexão
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return veiculoAL;

    }

    public VeiculoBEAN localizar(int codigo) {
        String sql = "select * from veiculo where veicod = ?;";
        VeiculoBEAN c = new VeiculoBEAN(); //crio objeto c para retorno
        try {
            // prepared statement para localização
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, codigo);

            // executa a consulta SQL
            ResultSet rs = stmt.executeQuery();

            //joga resultado da consulta no ArrayList
            while (rs.next()) {
                c.setVeicod(rs.getInt(1));
                c.setVeitipo(rs.getString(2));
                c.setVeivalor(rs.getFloat(3));
            }
            stmt.close();
            return c;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean editar(VeiculoBEAN c) {
        String sql = "update veiculo set veitipo = ?,veivalor = ? where veicod = ?;";

        try {
            /* metodo usado para criar um objeto que representa a instrução 
            SQL que será executada, sendo que é invocado através do objeto 
            Connection.
            
            Deve ser colocado dentro de um bloco try catch */
            stmt = connection.prepareStatement(sql);
            // seta os valores
            stmt.setString(1, c.getVeitipo());
            stmt.setFloat(2, c.getVeivalor());
            stmt.setInt(3, c.getVeicod());
            
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
        String sql = "delete from veiculo where veicod = ?;";

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
