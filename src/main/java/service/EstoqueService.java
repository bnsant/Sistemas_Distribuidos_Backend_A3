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

    
    void criarCategoria(Categoria c) throws RemoteException;
    void atualizarCategoria(Categoria c) throws RemoteException;
    void excluirCategoria(int id) throws RemoteException;
    List<Categoria> listarCategorias() throws RemoteException;

    
    void registrarMovimentacao(RegistroMovimentacao m) throws RemoteException;
    List<RegistroMovimentacao> listarMovimentacoes() throws RemoteException;

}
