package pl.edu.agh.hotelmanagerfrontend.command;

import pl.edu.agh.hotelmanagerfrontend.model.Hotel;

public class ShowRooms extends Command {

    @Override
    public void execute(Hotel hotel) {
        for(var room : hotel.getRooms()) {
            System.out.println(room);
        }
    }
}