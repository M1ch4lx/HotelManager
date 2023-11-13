package pl.edu.agh.hotelmanagerfrontend.command;

import pl.edu.agh.hotelmanagerfrontend.model.Hotel;

import java.util.Scanner;

public abstract class Command {

    public abstract void execute(Hotel hotel);

    public Scanner getScanner() {
        return new Scanner(System.in);
    }
}
