package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import modelo.Produto;
import modelo.Categoria;
import modelo.RegistroMovimentacao;

public interface EstoqueService extends Remote {

    void criarProduto(Produto p) throws RemoteException;
    void atualizarProduto(Produto p) throws RemoteException;
    void excluirProduto(int id) throws RemoteException;
    List<Produto> listarProdutos() throws RemoteException;
    Produto buscarProdutoPorId(int id) throws RemoteException;
    Produto buscarProdutoPorNome(String nome) throws RemoteException;

    void criarCategoria(Categoria c) throws RemoteException;
    void atualizarCategoria(Categoria c) throws RemoteException;
    void excluirCategoria(int id) throws RemoteException;
    List<Categoria> listarCategorias() throws RemoteException;

    void registrarMovimentacao(RegistroMovimentacao m) throws RemoteException;
    List<RegistroMovimentacao> listarMovimentacoes() throws RemoteException;
    List<RegistroMovimentacao> listarMovimentacoesPorProduto(int produtoId) throws RemoteException;
    
    List<Produto> listarProdutosOrdenadosPorNome() throws RemoteException;
    List<Produto> listarProdutosAbaixoMinimo() throws RemoteException;
    List<String[]> listarQuantidadePorCategoria() throws RemoteException;
    List<Object[]> listarBalancoFisicoFinanceiro() throws RemoteException;
    double calcularValorTotalEstoque() throws RemoteException;
    String[] produtoComMaisEntrada() throws RemoteException;
    String[] produtoComMaisSaida() throws RemoteException;
    boolean reajustarPrecosPercentual(double percentual) throws RemoteException;

}
