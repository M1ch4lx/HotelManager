package pl.edu.agh.hotelmanagerfrontend.command;

import pl.edu.agh.hotelmanagerfrontend.model.Hotel;

public class Exit extends Command {

    @Override
    public void execute(Hotel hotel) {
        System.exit(0);
    }
}
