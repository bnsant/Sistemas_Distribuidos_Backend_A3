package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe responsável por gerenciar a conexão com o banco de dados MySQL.
 * Fornece métodos para estabelecer conexão com o banco de dados do sistema de estoque.
 * 
 * @author bnsant
 * @version 1.0
 */
public class Conexao {

    /**
     * Driver JDBC para MySQL.
     */
    private final String DRIVER;
    
    /**
     * Endereço do servidor de banco de dados.
     */
    private final String SERVER;
    
    /**
     * Nome do banco de dados.
     */
    private final String DATABASE;
    
    /**
     * URL completa de conexão ao banco de dados.
     */
    private final String URL;
    
    /**
     * Usuário para autenticação no banco de dados.
     */
    private final String USER;
    
    /**
     * Senha para autenticação no banco de dados.
     */
    private final String PASSWORD;

    /**
     * Construtor que inicializa os parâmetros de conexão com o banco de dados.
     */
    public Conexao() {
        this.DRIVER = "com.mysql.cj.jdbc.Driver";
        this.SERVER = "localhost";
        this.DATABASE = "db_produto";
        this.URL = "jdbc:mysql://" + SERVER + ":3306/" + DATABASE + "?useTimezone=true&serverTimezone=UTC";
        this.USER = "root";
        this.PASSWORD = "TrabalhoA3";
    }

    /**
     * Estabelece conexão com o banco de dados MySQL.
     * Carrega o driver JDBC e cria uma conexão usando os parâmetros configurados.
     * 
     * @return Objeto Connection representando a conexão com o banco de dados,
     *         ou null se a conexão falhar
     */
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