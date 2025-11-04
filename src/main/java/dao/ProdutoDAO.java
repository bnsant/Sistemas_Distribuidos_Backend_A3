package dao;

import modelo.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    ArrayList<Produto>minhaLista = new ArrayList();
//Função para cadastrar um novo produto.
    public boolean CadastrarProduto(Produto produto) {
        Conexao conexao = new Conexao();
        try (Connection conn = conexao.conectar()) {
            String sql = "INSERT INTO produto (nome, unidade,quantidade, preco, min, max, categoria) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement st = conn.prepareStatement(sql);

            st.setString(1, produto.getNome());
            st.setString(2, produto.getUnidade());
            st.setInt(3,produto.getQuantidade());
            st.setDouble(4, produto.getPreco());
            st.setInt(5, produto.getMin());
            st.setInt(6, produto.getMax());
            st.setString(7, produto.getCategoria());

            st.execute();
            st.close();

            System.out.println("Produto cadastrado com sucesso!");
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar produto: " + e.getMessage());
            return false;
        }
    }
//Função para procurar um produto a partir do id
    public Produto ProcurarProdutoID(int id) {
        Conexao conexao = new Conexao();
        Produto produto = new Produto();
        try (Connection conn = conexao.conectar()) {
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM produto WHERE id = " + id);

            if (res.next()) {
                produto.setId(res.getInt("id"));
                produto.setNome(res.getString("nome"));
                produto.setUnidade(res.getString("unidade"));
                produto.setQuantidade(res.getInt("quantidade"));
                produto.setPreco(res.getDouble("preco"));
                produto.setMin(res.getInt("min"));
                produto.setMax(res.getInt("max"));
                produto.setCategoria(res.getString("categoria"));
            }

            res.close();
            stmt.close();
        } catch (SQLException erro) {
            System.out.println("Erro: " + erro.getMessage());
        }

        return produto;
    }
//Função para procurar um produto no banco de dados a partir do nome.
    public Produto ProcurarProdutoNome(String nome) {
        Conexao conexao = new Conexao();
        Produto produto = new Produto();
        try (Connection conn = conexao.conectar()) {
            String sql = "SELECT * FROM produto WHERE nome = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);

            ResultSet res = stmt.executeQuery();

            if (res.next()) {
                produto.setId(res.getInt("id"));
                produto.setNome(res.getString("nome"));
                produto.setUnidade(res.getString("unidade"));
                produto.setQuantidade(res.getInt("quantidade"));
                produto.setPreco(res.getDouble("preco"));
                produto.setMin(res.getInt("min"));
                produto.setMax(res.getInt("max"));
                produto.setCategoria(res.getString("categoria"));
            }

            res.close();
            stmt.close();
        } catch (SQLException erro) {
            System.out.println("Erro: " + erro.getMessage());
        }

        return produto;
    }
//função para atualizar um produto já existente.
 public boolean AtualizarProduto(Produto produto) {
    String sql = "UPDATE produto SET nome=?, unidade=?, quantidade=?, preco=?, min=?, max=?, categoria=? WHERE id=?";
    Conexao conexao = new Conexao();

    try (Connection conn = conexao.conectar()) {
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, produto.getNome());
        stmt.setString(2, produto.getUnidade());
        stmt.setInt(3, produto.getQuantidade());
        stmt.setDouble(4, produto.getPreco());
        stmt.setInt(5, produto.getMin());
        stmt.setInt(6, produto.getMax());
        stmt.setString(7, produto.getCategoria());
        stmt.setInt(8, produto.getId());
        stmt.executeUpdate();
        stmt.close();

        System.out.println("Produto atualizado com sucesso!");
        return true;
    } catch (SQLException erro) {
        System.out.println("Erro ao atualizar produto: " + erro.getMessage());
        return false;
    }
}   
//função para deletar um produto a partir do id dele.
    public boolean DeletarProdutoID(int id) {
        Conexao conexao = new Conexao();

        try (Connection conn = conexao.conectar()) {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM produto WHERE id = " + id);
            stmt.close();

            System.out.println("Produto deletado com sucesso!");
            return true;
        } catch (SQLException erro) {
            System.out.println("Erro: " + erro.getMessage());
            return false;
        }
    }

    
    public ArrayList<Produto>getMinhaListaProdutos() {
        
        minhaLista.clear();
        Conexao conexao = new Conexao();
        
        try(Connection conn = conexao.conectar()){
            Statement stmt = conn.createStatement();          
            ResultSet res = stmt.executeQuery("SELECT * FROM produto");
            
            while(res.next()){
                int id = res.getInt("id");
                String nome = res.getString("nome");
                String unidade = res.getString("unidade");
                int quantidade = res.getInt("quantidade");
                double preco = res.getDouble("preco");
                int min = res.getInt("min");
                int max = res.getInt("max");
                String categoria = res.getString("categoria");
                
                Produto produto = new Produto(id,nome,unidade,preco,quantidade,min,max,categoria);
                minhaLista.add(produto);
}
                
                res.close();
                stmt.close();

            }catch(SQLException ex){
                    System.out.println("Erro: "+ex);
                    }
            return minhaLista;
    }
 

    public int MaiorID(){
       Conexao conexao = new Conexao();
        int MaiorID = 0;
        
        try(Connection conn = conexao.conectar()){
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery("SELECT MAX(id) id from produto");
            res.next();
            MaiorID = res.getInt("id");
            stmt.close();
                
        
    }catch(SQLException ex){
        System.out.println("Erro: "+ex);
    }
        return MaiorID;
    }
       public ArrayList<String> buscarCategorias() {
        ArrayList<String> lista = new ArrayList<>();
        Conexao conexao = new Conexao();

        String sql = "SELECT DISTINCT categoria FROM produto ORDER BY categoria";

        try (Connection conn = conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(rs.getString("categoria"));
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar categorias: " + e.getMessage());
        }

        return lista;
    }
       public List<Produto> buscarPorCategoria(String categoria) throws SQLException { // pra filtrar por categoria
    List<Produto> lista = new ArrayList<>();
    String sql = "SELECT * FROM produto WHERE categoria = ?";

    try (
            Connection conn = new Conexao().conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, categoria);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
        Produto p = new Produto(
          rs.getInt("id"),
          rs.getString("nome"),
          rs.getString("unidade"),
          rs.getDouble("preco"),
          rs.getInt("quantidade"),
          rs.getInt("min"),
          rs.getInt("max"),
          rs.getString("categoria")             
      );
            lista.add(p);
        }
    }
    return lista;
       }
       public List<Produto> buscarPorNome(String nome) throws SQLException { // esse aqui é pra buscar por nome
        List<Produto> lista = new ArrayList<>();
        String sql = "SELECT * FROM produto WHERE nome LIKE ?";
        try (Connection conn = new Conexao().conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + nome + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Produto p = new Produto(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("unidade"),
                        rs.getDouble("preco"),
                        rs.getInt("quantidade"),
                        rs.getInt("min"),
                        rs.getInt("max"),
                        rs.getString("categoria")
                );
                lista.add(p);
            }
        }
        return lista;
    }
       public List<Produto> buscarPorNomeECategoria(String nome, String categoria) throws SQLException { // esse é por nome + categoria
        List<Produto> lista = new ArrayList<>();
        String sql = "SELECT * FROM produto WHERE nome LIKE ? AND categoria = ?";
        try (Connection conn = new Conexao().conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + nome + "%");
            stmt.setString(2, categoria);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Produto p = new Produto(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("unidade"),
                        rs.getDouble("preco"),
                        rs.getInt("quantidade"),
                        rs.getInt("min"),
                        rs.getInt("max"),
                        rs.getString("categoria")
                );
                lista.add(p);
            }
        }
        return lista;
    }
       public boolean RegistrarEntradaProduto(int produtoId, int quantidadeEntrada, String observacao){
           Conexao conexao = new Conexao();
           Produto produto = ProcurarProdutoID(produtoId);
           
           if(produto.getId() == 0){
               return false;
           }
           
           try(Connection conn = conexao.conectar()){
               conn.setAutoCommit(false);
               
               String sqlUpdateProduto = "UPDATE produto SET quantidade = quantidade + ? WHERE id = ?";
               try(PreparedStatement stmtUpdate = conn.prepareStatement(sqlUpdateProduto)){
                   stmtUpdate.setInt(1, quantidadeEntrada);
                   stmtUpdate.setInt(2, produtoId);
                   int linhasAfetadas = stmtUpdate.executeUpdate();
                   
                   if(linhasAfetadas == 0){
                       conn.rollback();
                       return false;
                   }
               }conn.commit();
               return true;
               
                    
           }catch (SQLException e){
               System.out.println("erro: "+e);
           }return false;
           
           
       }
       public boolean RegistrarSaidaProduto(int produtoId, int quantidadeSaida,String observacao){
           Conexao conexao = new Conexao();
           Produto produto = ProcurarProdutoID(produtoId);
           
           if(produto.getId() == 0){
               return false;
           }
           if(produto.getQuantidade() < quantidadeSaida)
               if (produto.getQuantidade() < quantidadeSaida) {
                   System.out.println("Quantidade insuficiente.");
                   return false;
               }
           
           try(Connection conn = conexao.conectar()){
               conn.setAutoCommit(false);
               
               String sqlUpdateProduto = "UPDATE produto SET quantidade = quantidade - ? WHERE id = ?";
               try(PreparedStatement stmtUpdate = conn.prepareStatement(sqlUpdateProduto)){
                   stmtUpdate.setInt(1, quantidadeSaida);
                   stmtUpdate.setInt(2, produtoId);
                   int linhasAfetadas = stmtUpdate.executeUpdate();
                   
                   if(linhasAfetadas == 0){
                       conn.rollback();
                       return false;
                   }
               }conn.commit();
               return true;
               
                    
           }catch (SQLException e){
               System.out.println("erro: "+e);
           }return false;
           
       }
       
     public void atualizarPreco(int idProduto, double novoPreco) throws SQLException {
        String sql = "UPDATE produto SET preco = ? WHERE id = ?";
        try (
                Connection conn = new Conexao().conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, novoPreco);
            stmt.setInt(2, idProduto);
            stmt.executeUpdate();
        }
    }
     public List<Produto> listarProdutoOrdenadoPorNome(){
         
         String sql = "SELECT * FROM produto ORDER BY nome ASC";
         
         List<Produto> listaDeProdutos = new ArrayList<>();
         try(Connection conn = new Conexao().conectar();
                 PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()){
             
             while (rs.next()){
                 
                 int id = rs.getInt("id");
                 String nome = rs.getString("nome");
                 String unidade = rs.getString("unidade");
                 double preco = rs.getDouble("preco");
                 int quantidade = rs.getInt("quantidade");
                 int min = rs.getInt("min");
                 int max = rs.getInt("max");
                 String categoria = rs.getString("categoria");
                 
                 Produto produto = new Produto(id, nome, unidade, preco, quantidade, min, max, categoria);
                 
                 listaDeProdutos.add(produto);
             }
             
         }catch (SQLException e){
             System.out.println("Erro ao buscar a lista de produtos ordenada: "+ e.getMessage());
         }
         return listaDeProdutos;
     }
     
         public List<Produto> listarProdutosAbaixoMinMax() {
        List<Produto> lista = new ArrayList<>();
        String sql = "SELECT * FROM produto WHERE quantidade < min OR quantidade > max";

        try (Connection conn = new Conexao().conectar(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Produto produto = new Produto(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("unidade"),
                        rs.getDouble("preco"),
                        rs.getInt("quantidade"),
                        rs.getInt("min"),
                        rs.getInt("max"),
                        rs.getString("categoria")
                );
                lista.add(produto);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar produtos abaixo do min/max: " + e.getMessage());
        }

        return lista;
    }
    public List<String[]> listarQuantidadePorCategoria() {
        List<String[]> lista = new ArrayList<>();
        String sql = "SELECT categoria, COUNT(*) AS total FROM produto GROUP BY categoria ORDER BY categoria";

        try (Connection conn = new Conexao().conectar(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String categoria = rs.getString("categoria");
                String total = String.valueOf(rs.getInt("total"));
                lista.add(new String[]{categoria, total});
            }

        } catch (SQLException e) {
            System.out.println("Erro ao contar produtos por categoria: " + e.getMessage());
        }

        return lista;
    }
    
    // Reajustar preços de todos os produtos em um percentual
    public boolean reajustarPrecosPercentual(double percentual) throws SQLException {
        String sql = "UPDATE produto SET preco = preco * (1 + ? / 100)";
        try (Connection conn = new Conexao().conectar(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, percentual);
            int linhasAfetadas = stmt.executeUpdate();
            System.out.println("Preços reajustados em " + percentual + "% para " + linhasAfetadas + " produtos.");
            return linhasAfetadas > 0;
        }
    }
    
    // Listar produtos abaixo da quantidade mínima (apenas abaixo, não acima do max)
    public List<Produto> listarProdutosAbaixoMinimo() {
        List<Produto> lista = new ArrayList<>();
        String sql = "SELECT * FROM produto WHERE quantidade < min ORDER BY nome ASC";

        try (Connection conn = new Conexao().conectar(); 
             PreparedStatement stmt = conn.prepareStatement(sql); 
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Produto produto = new Produto(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("unidade"),
                        rs.getDouble("preco"),
                        rs.getInt("quantidade"),
                        rs.getInt("min"),
                        rs.getInt("max"),
                        rs.getString("categoria")
                );
                lista.add(produto);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar produtos abaixo do mínimo: " + e.getMessage());
        }

        return lista;
    }
    
    // Balanço Físico/Financeiro: produtos com valor total de cada um e valor total do estoque
    public List<Object[]> listarBalancoFisicoFinanceiro() {
        List<Object[]> lista = new ArrayList<>();
        String sql = "SELECT id, nome, unidade, categoria, preco, quantidade, (preco * quantidade) as valor_total FROM produto ORDER BY nome ASC";

        try (Connection conn = new Conexao().conectar(); 
             PreparedStatement stmt = conn.prepareStatement(sql); 
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Object[] linha = new Object[7];
                linha[0] = rs.getInt("id");
                linha[1] = rs.getString("nome");
                linha[2] = rs.getString("unidade");
                linha[3] = rs.getString("categoria");
                linha[4] = rs.getDouble("preco");
                linha[5] = rs.getInt("quantidade");
                linha[6] = rs.getDouble("valor_total");
                lista.add(linha);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar balanço físico/financeiro: " + e.getMessage());
        }

        return lista;
    }
    
    // Calcular valor total do estoque
    public double calcularValorTotalEstoque() {
        double valorTotal = 0.0;
        String sql = "SELECT SUM(preco * quantidade) as total FROM produto";

        try (Connection conn = new Conexao().conectar(); 
             PreparedStatement stmt = conn.prepareStatement(sql); 
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                valorTotal = rs.getDouble("total");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao calcular valor total do estoque: " + e.getMessage());
        }

        return valorTotal;
    }
}
       
       


    
