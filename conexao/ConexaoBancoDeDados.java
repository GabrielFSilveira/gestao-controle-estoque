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

    public static synchronized ConexaoBancoDeDados getInstanciador(){
        if(instanciador == null){
            instanciador = new ConexaoBancoDeDados();
        }

        return instanciador;
    }

    public Connection getConector(){
        return conector;
    }

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
