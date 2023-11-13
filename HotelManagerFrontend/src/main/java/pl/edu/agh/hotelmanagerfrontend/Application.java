package pl.edu.agh.hotelmanagerfrontend;

import pl.edu.agh.hotelmanagerfrontend.command.*;
import pl.edu.agh.hotelmanagerfrontend.model.Hotel;
import pl.edu.agh.utils.MyMap;

import java.security.InvalidParameterException;
import java.util.Scanner;

public class Application {

    public static MyMap<String, Command> createCommands() {
        var commands = new MyMap<String, Command>();

        commands.put("list", new ShowRooms());
        commands.put("view", new DisplayRoomDetails());
        commands.put("checkin", new Checkin());
        commands.put("checkout", new Checkout());
        commands.put("save", new SaveToFile());
        commands.put("exit", new Exit());

        return commands;
    }

    public static void main(String[] args) {

        var commands = createCommands();

        var scanner = new Scanner(System.in);

        var hotel = new Hotel();
        hotel.loadFromFile();

        System.out.println("[HotelManager v1]");

        while(!Exit.shouldExit()) {

            try {
                var commandName = scanner.next();

                if(!commands.containsKey(commandName)) {
                    throw new InvalidParameterException("Podana komenda nie istnieje: " + commandName);
                }

                var command = commands.get(commandName);
                command.execute(hotel);
            }
            catch(Exception exception) {
                System.err.println(exception.getMessage());
            }
        }
    }
}
