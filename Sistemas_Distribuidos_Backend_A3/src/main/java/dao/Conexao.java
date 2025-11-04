package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private final String DRIVER;
    private final String SERVER;
    private final String DATABASE;
    private final String URL;
    private final String USER;
    private final String PASSWORD;

    public Conexao() {
        this.DRIVER = "com.mysql.cj.jdbc.Driver";
        this.SERVER = "localhost";
        this.DATABASE = "db_produto";
        this.URL = "jdbc:mysql://" + SERVER + ":3306/" + DATABASE + "?useTimezone=true&serverTimezone=UTC";
        this.USER = "root";
        this.PASSWORD = "TrabalhoA3";
    }

    public Connection conectar() {
        try {
            Class.forName(DRIVER);

            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Status: Conectado!");
            return connection;

        } catch (ClassNotFoundException e) {
            System.err.println("Erro: Driver JDBC não encontrado: " + e.getMessage());
            //System.exit(1);

        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            //System.exit(1);
        }
        return null;
    }
}