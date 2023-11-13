package pl.edu.agh.hotelmanagerfrontend.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.*;

class HotelTest {

    Hotel hotel;

    Guest guestA;

    Guest guestB;

    @BeforeEach
    void createGuests() {
        guestA = new Guest("A", "a", 123456789);

        guestB = new Guest("B", "b", 987654321);
    }

    @BeforeEach
    void initHotel() {
        hotel = new Hotel();
        hotel.addRoom(new Room(101, "Nice room", 300.0));
        hotel.addRoom(new Room(102, "Very nice room", 250.0));
        hotel.addRoom(new Room(201, "Mediocre room", 150.0));
        hotel.addRoom(new Room(202, "Terrible room", 100.0));
    }

    @Test
    void isRoomAvailable() {
        assertTrue(hotel.isRoomAvailable(101));
        assertTrue(hotel.isRoomAvailable(102));
        assertTrue(hotel.isRoomAvailable(201));
        assertTrue(hotel.isRoomAvailable(202));

        assertThrows(InvalidParameterException.class, () -> {
            assertFalse(hotel.isRoomAvailable(301));
        });
    }

    @Test
    void checkInGuest() {
        hotel.checkInGuest(101, guestA);

        assertFalse(hotel.isRoomAvailable(101));

        assertThrows(InvalidParameterException.class, () -> {
            hotel.checkInGuest(101, guestB);
        });
    }

    @Test
    void checkOutGuest() {
        assertThrows(InvalidParameterException.class, () -> {
            hotel.checkOutGuest(101);
        });

        hotel.checkInGuest(101, guestA);

        assertDoesNotThrow(() -> {
            hotel.checkOutGuest(101);
        });
    }

    @Test
    void test() {
        assertThrows(InvalidParameterException.class, () -> {
            hotel.loadFromFile();
        });

        assertThrows(InvalidParameterException.class, () -> {
            hotel.throwIfRoomNotAvailable(100);
        });
    }
}