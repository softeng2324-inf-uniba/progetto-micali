package it.uniba.app.interfaces;

import it.uniba.app.features.CommandType;
import java.io.IOException;
import java.util.Scanner;

public interface HandleModule {
    /**
     * Funzione richiamata per gestire il comando.
     * @param input
     * @param value
     * @param command
     * @throws java.io.IOException
     */
     void handle(Scanner input, Scanner value, CommandType command) throws IOException;
        // Method implementation goes here
    
}