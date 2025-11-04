package dao;

import modelo.RegistroMovimentacao;
import modelo.Produto;
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
        String sql = "SELECT id, produto_id, tipo_movimentacao, quantidade, observacao, data_movimentacao FROM registro_movimentacao WHERE produto_id = ? ORDER BY id DESC";

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
    
    // Produto que mais teve entrada
    public String[] produtoComMaisEntrada() {
        Conexao conexao = new Conexao();
        String sql = "SELECT p.nome, SUM(rm.quantidade) as total_entrada " +
                     "FROM registro_movimentacao rm " +
                     "INNER JOIN produto p ON rm.produto_id = p.id " +
                     "WHERE rm.tipo_movimentacao = 'Entrada' " +
                     "GROUP BY rm.produto_id, p.nome " +
                     "ORDER BY total_entrada DESC " +
                     "LIMIT 1";

        try (Connection conn = conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                return new String[]{
                    rs.getString("nome"),
                    String.valueOf(rs.getInt("total_entrada"))
                };
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar produto com mais entrada: " + e.getMessage());
        }
        return new String[]{"Nenhum", "0"};
    }
    
    // Produto que mais teve saída
    public String[] produtoComMaisSaida() {
        Conexao conexao = new Conexao();
        String sql = "SELECT p.nome, SUM(rm.quantidade) as total_saida " +
                     "FROM registro_movimentacao rm " +
                     "INNER JOIN produto p ON rm.produto_id = p.id " +
                     "WHERE rm.tipo_movimentacao = 'Saída' " +
                     "GROUP BY rm.produto_id, p.nome " +
                     "ORDER BY total_saida DESC " +
                     "LIMIT 1";

        try (Connection conn = conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                return new String[]{
                    rs.getString("nome"),
                    String.valueOf(rs.getInt("total_saida"))
                };
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar produto com mais saída: " + e.getMessage());
        }
        return new String[]{"Nenhum", "0"};
    }
    
    // Registrar movimentação e atualizar saldo do produto automaticamente
    public boolean registrarMovimentacaoEAtualizarSaldo(RegistroMovimentacao registro, ProdutoDAO produtoDAO) {
        Conexao conexao = new Conexao();
        Connection conn = null;
        
        try {
            conn = conexao.conectar();
            conn.setAutoCommit(false);
            
            // 1. Registrar a movimentação
            String sqlMovimentacao = "INSERT INTO registro_movimentacao (produto_id, tipo_movimentacao, quantidade, observacao, data_movimentacao) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement stMov = conn.prepareStatement(sqlMovimentacao)) {
                stMov.setInt(1, registro.getProdutoId());
                stMov.setString(2, registro.getTipoMovimentacao());
                stMov.setInt(3, registro.getQuantidade());
                stMov.setString(4, registro.getObservacao());
                stMov.setDate(5, java.sql.Date.valueOf(LocalDate.now()));
                stMov.execute();
            }
            
            // 2. Atualizar saldo do produto
            String sqlUpdateProduto;
            if ("Entrada".equalsIgnoreCase(registro.getTipoMovimentacao())) {
                sqlUpdateProduto = "UPDATE produto SET quantidade = quantidade + ? WHERE id = ?";
            } else { // Saída
                sqlUpdateProduto = "UPDATE produto SET quantidade = quantidade - ? WHERE id = ?";
            }
            
            try (PreparedStatement stUpdate = conn.prepareStatement(sqlUpdateProduto)) {
                stUpdate.setInt(1, registro.getQuantidade());
                stUpdate.setInt(2, registro.getProdutoId());
                int linhasAfetadas = stUpdate.executeUpdate();
                
                if (linhasAfetadas == 0) {
                    conn.rollback();
                    return false;
                }
            }
            
            // 3. Verificar e avisar sobre quantidade mínima/máxima
            Produto produto = produtoDAO.ProcurarProdutoID(registro.getProdutoId());
            if (produto != null && produto.getId() != 0) {
                if ("Saída".equalsIgnoreCase(registro.getTipoMovimentacao()) && produto.getQuantidade() < produto.getMin()) {
                    System.out.println("⚠️ AVISO: Produto " + produto.getNome() + " está abaixo da quantidade mínima! Quantidade atual: " + produto.getQuantidade() + " | Mínima: " + produto.getMin());
                } else if ("Entrada".equalsIgnoreCase(registro.getTipoMovimentacao()) && produto.getQuantidade() > produto.getMax()) {
                    System.out.println("⚠️ AVISO: Produto " + produto.getNome() + " está acima da quantidade máxima! Quantidade atual: " + produto.getQuantidade() + " | Máxima: " + produto.getMax());
                }
            }
            
            conn.commit();
            return true;
            
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException rollbackEx) {
                    System.err.println("Erro ao fazer rollback: " + rollbackEx.getMessage());
                }
            }
            System.err.println("Erro ao registrar movimentação e atualizar saldo: " + e.getMessage());
            return false;
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    System.err.println("Erro ao fechar conexão: " + e.getMessage());
                }
            }
        }
    }
}