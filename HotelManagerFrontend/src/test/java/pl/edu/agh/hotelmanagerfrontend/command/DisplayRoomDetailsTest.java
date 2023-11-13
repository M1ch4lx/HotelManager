package pl.edu.agh.hotelmanagerfrontend.command;

import org.junit.jupiter.api.Test;
import pl.edu.agh.hotelmanagerfrontend.model.Hotel;
import pl.edu.agh.hotelmanagerfrontend.model.Room;

import static org.junit.jupiter.api.Assertions.*;

class DisplayRoomDetailsTest {

    @Test
    void test() {
        var hotel = new Hotel();
        hotel.addRoom(new Room(101, "Nice room", 300.0));
        hotel.addRoom(new Room(102, "Very nice room", 250.0));
        hotel.addRoom(new Room(201, "Mediocre room", 150.0));
        hotel.addRoom(new Room(202, "Terrible room", 100.0));

        assertDoesNotThrow(() -> {
            new DisplayRoomDetails().execute(hotel);
        });
    }
}