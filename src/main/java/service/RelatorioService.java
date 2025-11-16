package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Interface remota para operações relacionadas a Relatórios e Consultas Consolidadas.
 * Define os serviços disponíveis para geração de relatórios e análises do estoque.
 * 
 * @author bnsant
 * @version 1.0
 */
public interface RelatorioService extends Remote {

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

