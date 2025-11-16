package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import modelo.RegistroMovimentacao;

/**
 * Interface remota para operações relacionadas a Movimentações de Estoque.
 * Define os serviços disponíveis para gerenciamento de movimentações (entradas e saídas).
 * 
 * @author bnsant
 * @version 1.0
 */
public interface MovimentacaoService extends Remote {

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

}

