package pl.edu.agh.hotelmanagerfrontend.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GuestTest {

    @Test
    void test()
    {
        var guest = new Guest("A", "B", 1);
        assertEquals("A", guest.getFirstName());
        assertEquals("B", guest.getLastName());
        assertEquals(1, guest.getPhoneNumber());

        String data = "A B 1";
        guest = new Guest(data);
        assertEquals("A", guest.getFirstName());
        assertEquals("B", guest.getLastName());
        assertEquals(1, guest.getPhoneNumber());
    }
}