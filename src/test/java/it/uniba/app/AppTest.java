package it.uniba.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import it.uniba.app.features.CommandType;
import it.uniba.app.interfaces.HandleModule;

/**
 * Main test class of the application.
 */
class AppTest {
    /**
     * Test the getGreeting method of the App class.
     */
    @Test
    @DisplayName("Test if App has a greeting")
    void appHasAGreeting() {
        assertEquals("Hello World!!", App.getGreeting(), "App should have a greeting");
    }

    /**
     * Test to verify that the command is valid.
     */
    @Test
    @DisplayName("testCheckCommandValid(): Test to verify that the command is valid")
    void testCheckCommandValid() {
        Map<CommandType, HandleModule> commands = new HashMap<>();
        commands.put(CommandType.HELP, App::handleHelp);
        commands.put(CommandType.START, App::handlePlay);

        String input = "/help";
        CommandType result = App.checkCommand(commands, input);

        assertEquals(CommandType.HELP, result, "Il comando non è valido");
    }

    /**
     * Test to verify that the command is invalid.
     */
    @Test
    @DisplayName("testCheckCommandInvalid(): Test to verify that the command is invalid")
    void testCheckCommandInvalid() {
        Map<CommandType, HandleModule> commands = new HashMap<>();
        commands.put(CommandType.HELP, App::handleHelp);
        commands.put(CommandType.START, App::handlePlay);

        String input = "/invalid";
        CommandType result = App.checkCommand(commands, input);

        assertNull(result, "Il comando è valido");
    }
}
