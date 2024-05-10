package it.uniba.app;

import java.io.IOException;
import java.util.Scanner;


import javax.annotation.Syntax;

import it.uniba.app.features.CommandType;

/**
 * Main class of the application.
 */
public final class App {

    


    /**
     * Get a greeting sentence.
     *
     * @return the "Hello World!" string.
     */
    public static String getGreeting() {
        return "Hello World!!";
    }

    public static void main(final String[] args) {
        System.out.println(getGreeting()); 
    }


        public static void handleHelp(final Scanner input , final Scanner value , final CommandType command) throws IOException {
        System.out.println("Comandi disponibili: ");
        System.out.println("/gioca - Inizia una nuova partita");
        System.out.println("/esci - Termina il gioco");
        System.out.println("/aiuto - Mostra l'elenco dei comandi disponibili");
        System.out.println("/vuoto - Crea un tavoliere vuoto");
        System.out.println("/tavoliere - Mostra il tavoliere di gioco");
        System.out.println("/qualimosse - Mostra le mosse disponibili");
        System.out.println("/abbandona - Abbandona la partita in corso");
    }
}
