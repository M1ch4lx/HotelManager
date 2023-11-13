package pl.edu.agh.hotelmanagerfrontend.command;

import pl.edu.agh.hotelmanagerfrontend.model.Hotel;

public class SaveToFile extends Command {

    @Override
    public void execute(Hotel hotel) {
        hotel.saveToFile();

        System.out.println("Zapisano aktualny stan pokoi do pliku");
    }
}