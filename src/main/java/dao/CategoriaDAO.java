package dao;

import modelo.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe de acesso a dados (DAO) para operações relacionadas a categorias.
 * Fornece métodos para criar, listar, atualizar e excluir categorias no banco de dados.
 * 
 * @author bnsant
 * @version 1.0
 */
public class CategoriaDAO {

    /**
     * Conexão com o banco de dados.
     */
    private Connection connection;

    /**
     * Construtor que inicializa a conexão com o banco de dados.
     * Lança RuntimeException se não conseguir conectar.
     * 
     * @throws RuntimeException Se não conseguir estabelecer conexão com o banco
     */
    public CategoriaDAO() {
        Conexao conexao = new Conexao();
        this.connection = conexao.conectar();

        if (this.connection == null) {
            throw new RuntimeException("Erro ao conectar com o banco de dados");
        }
    }

    /**
     * Salva uma nova categoria no banco de dados.
     * 
     * @param categoria Categoria a ser salva
     * @throws SQLException Se ocorrer erro na operação de banco de dados
     */
    public void salvar(Categoria categoria) throws SQLException {
        String sql = "INSERT INTO categoria (nome, tamanho, embalagem) VALUES (?, ?, ?)";
        try (
                Connection conn = new Conexao().conectar();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, categoria.getNomeCategoria());
            stmt.setString(2, categoria.getTamanho());
            stmt.setString(3, categoria.getEmbalagem());
            
            stmt.executeUpdate();
        }
    }


    /**
     * Lista todas as categorias cadastradas no banco de dados.
     * 
     * @return Lista de categorias
     * @throws SQLException Se ocorrer erro na operação de banco de dados
     */
    public List<Categoria> listarCategorias() throws SQLException {
        List<Categoria> categorias = new ArrayList<>();
        String sql = "SELECT * FROM categoria";

        try (
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql)
        ) {
            while (rs.next()) {
                Categoria c = new Categoria(
                        rs.getInt("idcategoria"),
                        rs.getString("nome"),
                        rs.getString("tamanho"),
                        rs.getString("embalagem")
                );
                categorias.add(c);
            }
        }

        return categorias;
    }

    /**
     * Atualiza os dados de uma categoria existente no banco de dados.
     * 
     * @param categoria Categoria com os dados atualizados
     * @throws SQLException Se ocorrer erro na operação de banco de dados
     */
    public void atualizar(Categoria categoria) throws SQLException {
        String sql = "UPDATE categoria SET nome = ?, tamanho = ?, embalagem = ? WHERE idcategoria = ?";

        try (
                PreparedStatement stmt = connection.prepareStatement(sql)
        ) {
            stmt.setString(1, categoria.getNomeCategoria());
            stmt.setString(2, categoria.getTamanho());
            stmt.setString(3, categoria.getEmbalagem());
            stmt.setInt(4, categoria.getId());
            stmt.executeUpdate();
        }
    }

    /**
     * Exclui uma categoria do banco de dados pelo ID.
     * 
     * @param id ID da categoria a ser excluída
     * @throws SQLException Se ocorrer erro na operação de banco de dados
     */
    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM categoria WHERE idcategoria = ?";

        try (
                PreparedStatement stmt = connection.prepareStatement(sql)
        ) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}