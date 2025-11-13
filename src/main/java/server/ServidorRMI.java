package server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import service.EstoqueServiceImpl;

/**
 * Classe principal do servidor RMI.
 * Responsável por iniciar o registro RMI e disponibilizar o serviço de estoque.
 * 
 * @author Sistema de Controle de Estoque
 * @version 1.0
 */
public class ServidorRMI {
    
    /**
     * Método principal que inicia o servidor RMI.
     * Cria o registro RMI na porta 1099 e registra o serviço de estoque.
     * 
     * @param args Argumentos da linha de comando (não utilizados)
     */
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);

            EstoqueServiceImpl service = new EstoqueServiceImpl();

            Naming.rebind("EstoqueService", service);

            System.out.println("✅ Servidor RMI do estoque iniciado...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
