package pl.edu.agh.hotelmanagerfrontend.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {

    @Test
    void test() {
        var room = new Room(1, "A", 100.0);

        assertEquals("1: A | 100.0 | wolny", room.toString());

        assertTrue(room.isAvailable());
    }
}