package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import modelo.Produto;
import modelo.Categoria;
import modelo.RegistroMovimentacao;

/**
 * Interface remota que define os serviços disponíveis para gerenciamento de estoque.
 * Esta interface estende Remote para permitir chamadas RMI.
 * 
 * @author Sistema de Controle de Estoque
 * @version 1.0
 */
public interface EstoqueService extends Remote {

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
     * Cria uma nova categoria no sistema.
     * 
     * @param c Categoria a ser criada
     * @throws RemoteException Se ocorrer erro na comunicação remota
     */
    void criarCategoria(Categoria c) throws RemoteException;
    
    /**
     * Atualiza os dados de uma categoria existente.
     * 
     * @param c Categoria com os dados atualizados
     * @throws RemoteException Se ocorrer erro na comunicação remota
     */
    void atualizarCategoria(Categoria c) throws RemoteException;
    
    /**
     * Exclui uma categoria do sistema pelo ID.
     * 
     * @param id ID da categoria a ser excluída
     * @throws RemoteException Se ocorrer erro na comunicação remota
     */
    void excluirCategoria(int id) throws RemoteException;
    
    /**
     * Lista todas as categorias cadastradas no sistema.
     * 
     * @return Lista de categorias
     * @throws RemoteException Se ocorrer erro na comunicação remota
     */
    List<Categoria> listarCategorias() throws RemoteException;

    /**
     * Registra uma nova movimentação de estoque (entrada ou saída).
     * 
     * @param m Registro de movimentação a ser salvo
     * @throws RemoteException Se ocorrer erro na comunicação remota
     */
    void registrarMovimentacao(RegistroMovimentacao m) throws RemoteException;
    
    /**
     * Lista todas as movimentações registradas no sistema.
     * 
     * @return Lista de registros de movimentação
     * @throws RemoteException Se ocorrer erro na comunicação remota
     */
    List<RegistroMovimentacao> listarMovimentacoes() throws RemoteException;
    
    /**
     * Lista todas as movimentações de um produto específico.
     * 
     * @param produtoId ID do produto
     * @return Lista de registros de movimentação do produto
     * @throws RemoteException Se ocorrer erro na comunicação remota
     */
    List<RegistroMovimentacao> listarMovimentacoesPorProduto(int produtoId) throws RemoteException;
    
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
    
    /**
     * Lista a quantidade de produtos agrupados por categoria.
     * 
     * @return Lista de arrays contendo [categoria, quantidade] para cada categoria
     * @throws RemoteException Se ocorrer erro na comunicação remota
     */
    List<String[]> listarQuantidadePorCategoria() throws RemoteException;
    
    /**
     * Lista o balanço físico e financeiro de todos os produtos.
     * 
     * @return Lista de arrays contendo informações de cada produto
     * @throws RemoteException Se ocorrer erro na comunicação remota
     */
    List<Object[]> listarBalancoFisicoFinanceiro() throws RemoteException;
    
    /**
     * Calcula o valor total do estoque.
     * 
     * @return Valor total do estoque
     * @throws RemoteException Se ocorrer erro na comunicação remota
     */
    double calcularValorTotalEstoque() throws RemoteException;
    
    /**
     * Identifica o produto que teve mais entradas no estoque.
     * 
     * @return Array com [nome do produto, total de entradas]
     * @throws RemoteException Se ocorrer erro na comunicação remota
     */
    String[] produtoComMaisEntrada() throws RemoteException;
    
    /**
     * Identifica o produto que teve mais saídas do estoque.
     * 
     * @return Array com [nome do produto, total de saídas]
     * @throws RemoteException Se ocorrer erro na comunicação remota
     */
    String[] produtoComMaisSaida() throws RemoteException;
    
    /**
     * Reajusta os preços de todos os produtos aplicando um percentual.
     * 
     * @param percentual Percentual de reajuste (ex: 10.0 para 10% de aumento)
     * @return true se o reajuste foi aplicado com sucesso, false caso contrário
     * @throws RemoteException Se ocorrer erro na comunicação remota
     */
    boolean reajustarPrecosPercentual(double percentual) throws RemoteException;

}
