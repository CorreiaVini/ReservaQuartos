package ReservaDeQuartos;
import java.rmi.Naming;
import java.util.*;

public class HotelClient {
    public static void main(String[] args) {
        try {
            String serverAddress = "localhost";
            if (args.length > 0) {
                serverAddress = args[0];
            }
            RoomManager manager = (RoomManager) Naming.lookup("rmi://" + serverAddress + "/RoomManager");
            
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("\nComandos disponíveis: list, book <tipo_quarto> <nome>, clientes, exit");
                System.out.print("Digite um comando: ");
                String command = scanner.nextLine();
                String[] input = command.split(" ");
                
                switch (input[0]) {
                    case "list":
                        Map<Integer, Integer> rooms = manager.listRooms();
                        rooms.forEach((type, count) ->
                            System.out.println(count + " quartos do tipo " + type + " estão disponíveis por " + (type == 0 ? 55 : type == 1 ? 75 : type == 2 ? 80 : type == 3 ? 150 : 230) + " reais por noite"));
                        break;
                    case "book":
                        if (input.length < 3) {
                            System.out.println("Uso: book <tipo_quarto> <nome>");
                            break;
                        }
                        int roomType = Integer.parseInt(input[1]);
                        String guestName = input[2];
                        boolean success = manager.bookRoom(roomType, guestName);
                        System.out.println(success ? "Reserva realizada com sucesso." : "Nenhum quarto disponível desse tipo.");
                        break;
                    case "clientes":
                        List<String> guests = manager.listGuests();
                        guests.forEach(System.out::println);
                        break;
                    case "exit":
                        System.out.println("Encerrando cliente.");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Comando inválido.");
                }
            }
        } catch (Exception e) {
            System.err.println("Erro no cliente: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
