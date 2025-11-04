package server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import service.EstoqueServiceImpl;

public class ServidorRMI {
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
