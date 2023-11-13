package pl.edu.agh.hotelmanagerfrontend;

import com.sun.source.tree.AssertTree;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationTest {

    @Test
    void test() {
        var commands = Application.createCommands();

        assertTrue(commands.containsKey("list"));
        assertEquals(6, commands.size());
    }
}