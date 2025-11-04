package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import modelo.Produto;
import modelo.Categoria;
import modelo.RegistroMovimentacao;

public interface EstoqueService extends Remote {

    // ========== PRODUTO ==========
    void criarProduto(Produto p) throws RemoteException;
    void atualizarProduto(Produto p) throws RemoteException;
    void excluirProduto(int id) throws RemoteException;
    List<Produto> listarProdutos() throws RemoteException;
    Produto buscarProdutoPorId(int id) throws RemoteException;
    Produto buscarProdutoPorNome(String nome) throws RemoteException;

    // ========== CATEGORIA ==========
    void criarCategoria(Categoria c) throws RemoteException;
    void atualizarCategoria(Categoria c) throws RemoteException;
    void excluirCategoria(int id) throws RemoteException;
    List<Categoria> listarCategorias() throws RemoteException;

    // ========== MOVIMENTAÇÃO ==========
    void registrarMovimentacao(RegistroMovimentacao m) throws RemoteException;
    List<RegistroMovimentacao> listarMovimentacoes() throws RemoteException;
    List<RegistroMovimentacao> listarMovimentacoesPorProduto(int produtoId) throws RemoteException;
    
    // ========== RELATÓRIOS ==========
    // Lista de Preços: produtos em ordem alfabética (preço, unidade, categoria)
    List<Produto> listarProdutosOrdenadosPorNome() throws RemoteException;
    
    // Produtos abaixo da quantidade mínima
    List<Produto> listarProdutosAbaixoMinimo() throws RemoteException;
    
    // Quantidade de produtos por categoria
    List<String[]> listarQuantidadePorCategoria() throws RemoteException;
    
    // Balanço Físico/Financeiro: produtos com valor total de cada um e valor total do estoque
    List<Object[]> listarBalancoFisicoFinanceiro() throws RemoteException;
    double calcularValorTotalEstoque() throws RemoteException;
    
    // Produto que mais teve saída e entrada
    String[] produtoComMaisEntrada() throws RemoteException;
    String[] produtoComMaisSaida() throws RemoteException;
    
    // ========== FUNCIONALIDADES ESPECIAIS ==========
    // Reajustar preços de todos os produtos em um percentual
    boolean reajustarPrecosPercentual(double percentual) throws RemoteException;

}
