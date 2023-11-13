package pl.edu.agh.hotelmanagerfrontend.command;

import pl.edu.agh.hotelmanagerfrontend.model.Hotel;

public class Checkout extends Command {

    @Override
    public void execute(Hotel hotel) {
        var scanner = getScanner();

        System.out.print("Podaj numer pokoju: ");

        var roomNumber = scanner.nextInt();

        hotel.checkOutGuest(roomNumber);
    }
}