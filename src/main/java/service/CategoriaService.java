package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import modelo.Categoria;

/**
 * Interface remota para operações relacionadas a Categorias.
 * Define os serviços disponíveis para gerenciamento de categorias no estoque.
 * 
 * @author bnsant
 * @version 1.0
 */
public interface CategoriaService extends Remote {

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

}

