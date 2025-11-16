package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import modelo.Produto;

/**
 * Interface remota para operações relacionadas a Produtos.
 * Define os serviços disponíveis para gerenciamento de produtos no estoque.
 * 
 * @author bnsant
 * @version 1.0
 */
public interface ProdutoService extends Remote {

    /**
     * Cria um novo produto no sistema.
     * 
     * @param p Produto a ser criado
     * @throws RemoteException Se ocorrer erro na comunicação remota
     */
    void criarProduto(Produto p) throws RemoteException;
    
    /**
     * Atualiza os dados de um produto existente.
     * 
     * @param p Produto com os dados atualizados
     * @throws RemoteException Se ocorrer erro na comunicação remota
     */
    void atualizarProduto(Produto p) throws RemoteException;
    
    /**
     * Exclui um produto do sistema pelo ID.
     * 
     * @param id ID do produto a ser excluído
     * @throws RemoteException Se ocorrer erro na comunicação remota
     */
    void excluirProduto(int id) throws RemoteException;
    
    /**
     * Lista todos os produtos cadastrados no sistema.
     * 
     * @return Lista de produtos
     * @throws RemoteException Se ocorrer erro na comunicação remota
     */
    List<Produto> listarProdutos() throws RemoteException;
    
    /**
     * Busca um produto pelo ID.
     * 
     * @param id ID do produto
     * @return Produto encontrado ou null se não encontrado
     * @throws RemoteException Se ocorrer erro na comunicação remota
     */
    Produto buscarProdutoPorId(int id) throws RemoteException;
    
    /**
     * Busca um produto pelo nome.
     * 
     * @param nome Nome do produto
     * @return Produto encontrado ou null se não encontrado
     * @throws RemoteException Se ocorrer erro na comunicação remota
     */
    Produto buscarProdutoPorNome(String nome) throws RemoteException;
    
    /**
     * Lista todos os produtos ordenados alfabeticamente por nome.
     * 
     * @return Lista de produtos ordenados por nome
     * @throws RemoteException Se ocorrer erro na comunicação remota
     */
    List<Produto> listarProdutosOrdenadosPorNome() throws RemoteException;
    
    /**
     * Lista produtos que estão abaixo da quantidade mínima permitida.
     * 
     * @return Lista de produtos abaixo do mínimo
     * @throws RemoteException Se ocorrer erro na comunicação remota
     */
    List<Produto> listarProdutosAbaixoMinimo() throws RemoteException;

}

