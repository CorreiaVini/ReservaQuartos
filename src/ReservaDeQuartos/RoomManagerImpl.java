package ReservaDeQuartos;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class RoomManagerImpl extends UnicastRemoteObject implements RoomManager {
    private final Map<Integer, Integer> rooms;
    private final Map<Integer, Integer> prices;
    private final List<String> guests;

    public RoomManagerImpl() throws RemoteException {
        super();
        rooms = new HashMap<>();
        prices = new HashMap<>();
        guests = new ArrayList<>();

        rooms.put(0, 10);
        rooms.put(1, 20);
        rooms.put(2, 5);
        rooms.put(3, 3);
        rooms.put(4, 2);

        prices.put(0, 55);
        prices.put(1, 75);
        prices.put(2, 80);
        prices.put(3, 150);
        prices.put(4, 230);
    }

    public synchronized Map<Integer, Integer> listRooms() throws RemoteException {
        return new HashMap<>(rooms);
    }

    public synchronized boolean bookRoom(int roomType, String guestName) throws RemoteException {
        if (rooms.containsKey(roomType) && rooms.get(roomType) > 0) {
            rooms.put(roomType, rooms.get(roomType) - 1);
            guests.add(guestName);
            return true;
        }
        return false;
    }

    public synchronized List<String> listGuests() throws RemoteException {
        return new ArrayList<>(guests);
    }
}