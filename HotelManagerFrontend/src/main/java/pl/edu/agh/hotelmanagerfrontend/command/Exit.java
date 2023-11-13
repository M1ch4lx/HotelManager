package pl.edu.agh.hotelmanagerfrontend.command;

import pl.edu.agh.hotelmanagerfrontend.model.Hotel;

public class Exit extends Command {

    private static boolean running = true;

    public static boolean shouldExit() {
        return !running;
    }

    private static void close() {
        running = false;
    }

    @Override
    public void execute(Hotel hotel) {
        close();
    }
}
