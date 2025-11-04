package server;

import dao.Conexao;
import java.sql.Connection;

public class TesteConexao {
    public static void main(String[] args) {
        Conexao c = new Conexao();
        Connection conn = c.conectar();

        if (conn != null) {
            System.out.println("Conectado ao banco com sucesso!");
        } else {
            System.out.println("Não foi possível conectar ao banco.");
        }
    }
}
