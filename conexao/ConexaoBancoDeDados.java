package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBancoDeDados {
    private static ConexaoBancoDeDados instanciador;
    private Connection conector;
    private final String url = "jdbc:postgresql://localhost:5432/gerenciamento";
    private final String usuario = "postgres";
    private final String senha = "08050200";

    private ConexaoBancoDeDados(){
        try {
            this.conector = DriverManager.getConnection(url, usuario,senha);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Verifica se ja existe conexão entre o banco de dados, ou seja, não permite que sejam abertas duas instâncias do banco de dados
    public static synchronized ConexaoBancoDeDados getInstanciador(){
        if(instanciador == null){
            instanciador = new ConexaoBancoDeDados();
        }
        return instanciador;
    }

    // Cria a conexão com o banco de dados
    public Connection getConector(){
        return conector;
    }

    // Fim da conexão com o banco de dados
    public void fecharConector(){
        if(conector != null){
            try {
                conector.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
