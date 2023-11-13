package pl.edu.agh.hotelmanagerfrontend.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExitTest {

    @Test
    void test() {
        assertFalse(Exit.shouldExit());
    }
}