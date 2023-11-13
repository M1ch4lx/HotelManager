package pl.edu.agh.hotelmanagerfrontend.command;

import pl.edu.agh.hotelmanagerfrontend.model.Hotel;

public class DisplayRoomDetails extends Command {

    @Override
    public void execute(Hotel hotel) {
        var scanner = getScanner();

        System.out.print("Podaj numer pokoju: ");

        var roomNumber = scanner.nextInt();

        var room = hotel.getRoom(roomNumber);

        System.out.println(room);

        if(!room.isAvailable()) {
            System.out.println(room.getGuest());
        }
    }
}