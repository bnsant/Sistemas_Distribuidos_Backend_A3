package dao;

import modelo.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    private Connection connection;


    public CategoriaDAO() {
        Conexao conexao = new Conexao();
        this.connection = conexao.conectar();

        if (this.connection == null) {
            throw new RuntimeException("Erro ao conectar com o banco de dados");
        }
    }

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