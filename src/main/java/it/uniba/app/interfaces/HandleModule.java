package it.uniba.app.interfaces;

import it.uniba.app.features.CommandType;
import java.io.IOException;
import java.util.Scanner;

/**
 * <p>Interface for handling commands.</p>
 */
public interface HandleModule {
    /**
     * Method for handling the command.
     *
     * @param input the Scanner object for user input
     * @param value the Scanner object for additional values
     * @param command the command type to handle
     * @throws IOException if an I/O error occurs
     */
    void handle(Scanner input, Scanner value, CommandType command) throws IOException;
}
