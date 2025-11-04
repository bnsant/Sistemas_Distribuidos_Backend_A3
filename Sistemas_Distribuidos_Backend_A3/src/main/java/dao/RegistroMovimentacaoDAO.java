package dao;

import modelo.RegistroMovimentacao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RegistroMovimentacaoDAO {

    public boolean registrarMovimentacao(RegistroMovimentacao registro) {
        Conexao conexao = new Conexao();
        String sql = "INSERT INTO registro_movimentacao (produto_id, tipo_movimentacao, quantidade, observacao, data_movimentacao) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = conexao.conectar();
             PreparedStatement st = conn.prepareStatement(sql)) {
            
            System.out.println("Inserindo movimentação no banco...");
            System.out.println("Produto ID: " + registro.getProdutoId());
            System.out.println("Tipo: " + registro.getTipoMovimentacao());
            System.out.println("Quantidade: " + registro.getQuantidade());
            System.out.println("Data: " + registro.getDataMovimentacao());

            st.setInt(1, registro.getProdutoId());
            st.setString(2, registro.getTipoMovimentacao());
            st.setInt(3, registro.getQuantidade());
            st.setString(4, registro.getObservacao());
            st.setDate(5, java.sql.Date.valueOf(LocalDate.now()));

            st.execute();
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao registrar movimentação no banco de dados: " + e.getMessage());
            return false;
            
   
        }
 
    }

    public List<RegistroMovimentacao> listarTodasMovimentacoes() {
        List<RegistroMovimentacao> listaMovimentacoes = new ArrayList<>();
        Conexao conexao = new Conexao();
        String sql = "SELECT id, produto_id, tipo_movimentacao, quantidade, observacao, data_movimentacao FROM registro_movimentacao ORDER BY id DESC";

        try (Connection conn = conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                int produtoId = rs.getInt("produto_id");
                String tipoMovimentacao = rs.getString("tipo_movimentacao");
                int quantidade = rs.getInt("quantidade");
                String observacao = rs.getString("observacao");
                String dataMovimentacao = rs.getString("data_movimentacao"); 


                RegistroMovimentacao registro = new RegistroMovimentacao(
                        id, produtoId, tipoMovimentacao, quantidade, observacao, dataMovimentacao
                );
                listaMovimentacoes.add(registro);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar movimentações: " + e.getMessage());
        }
        return listaMovimentacoes;
    }

    public List<RegistroMovimentacao> listarMovimentacoesPorProduto(int produtoId) {
        List<RegistroMovimentacao> listaMovimentacoes = new ArrayList<>();
        Conexao conexao = new Conexao();
        String sql = "SELECT id, produto_id, tipo_movimentacao, quantidade, observacao FROM registro_movimentacao WHERE produto_id = ? ORDER BY id DESC";

        try (Connection conn = conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, produtoId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String tipoMovimentacao = rs.getString("tipo_movimentacao");
                    int quantidade = rs.getInt("quantidade");
                    String observacao = rs.getString("observacao");
                    String dataMovimentacao = rs.getString("data_movimentacao");

                    RegistroMovimentacao registro = new RegistroMovimentacao(id, produtoId, tipoMovimentacao, quantidade, observacao, dataMovimentacao);
                    listaMovimentacoes.add(registro);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar movimentações por produto: " + e.getMessage());
        }
        return listaMovimentacoes;
    }
}