package pl.edu.agh.hotelmanagerfrontend.command;

import pl.edu.agh.hotelmanagerfrontend.model.Guest;
import pl.edu.agh.hotelmanagerfrontend.model.Hotel;

public class Checkin extends Command {

    @Override
    public void execute(Hotel hotel) {
        var scanner = getScanner();

        System.out.print("Podaj numer pokoju: ");

        var roomNumber = scanner.nextInt();

        hotel.throwIfRoomNotAvailable(roomNumber);

        System.out.println("Wprowadź dane gościa");

        System.out.print("Imię: ");
        var firstName = scanner.next();

        System.out.print("Nazwisko: ");
        var lastName = scanner.next();

        System.out.print("Nr telefonu: ");
        var phoneNumber = scanner.nextInt();

        hotel.checkInGuest(roomNumber, new Guest(firstName, lastName, phoneNumber));
    }
}
