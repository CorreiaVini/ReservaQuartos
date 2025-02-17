package ReservaDeQuartos;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public interface RoomManager extends Remote {
    Map<Integer, Integer> listRooms() throws RemoteException;
    boolean bookRoom(int roomType, String guestName) throws RemoteException;
    List<String> listGuests() throws RemoteException;
}