package ReservaDeQuartos;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;


public class HotelServer {
    public static void main(String[] args) {
        try {
            RoomManagerImpl manager = new RoomManagerImpl();
            LocateRegistry.createRegistry(1099);
            Naming.rebind("rmi://localhost/RoomManager", manager);
            System.out.println("HotelServer pronto.");
        } catch (Exception e) {
            System.err.println("Erro no servidor: " + e.getMessage());
            e.printStackTrace();
        }
    }
}