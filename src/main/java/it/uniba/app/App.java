package it.uniba.app;

import it.uniba.app.features.ColorShell;
import it.uniba.app.features.ViewResult;
import it.uniba.app.interfaces.HandleModule;
import java.io.IOException;
import java.util.Scanner;
import java.io.IOException;
import it.uniba.app.features.CommandType;
import java.util.Map;
import java.util.HashMap;
import java.util.regex.Pattern;
import javax.annotation.Syntax;

/**
 * Main class of the application.
 */
public final class App {

    public static boolean exit = false;   
    public static final Map<CommandType, HandleModule> COMMAND = new HashMap<>();



    /**
     * Get a greeting sentence.
     *
     * @return the "Hello World!" string.
     */
    public static String getGreeting() {
        return "Hello World!!";
    }

     public static String getMessage(final ViewResult msg) {
        return msg.getMessage();
    }

    public static void printWelcome(final ColorShell color) {
        System.out.print(getMessage(ViewResult.WELCOME));
        System.out.println("/help per avere un aiuto !");
    }

            /**
     * Metodo che controlla se il comando inserito dall'utente è presente tra i
     * comandi disponibili.
     *
     * @param commands Mappa dei comandi Map<CommandType, CommandHandler>
     * @param value    comando inserito dall'utente
     *
     * @return il comando inserito dall'utente se alias corretto | null altrimenti
     */
    public static CommandType checkCommand(final Map<CommandType, HandleModule> commands, final String value) {
        for (CommandType iteration : commands.keySet()) {
            for (Pattern patt : iteration.getCommandPattern()) {
                if (patt.matcher(value).matches()) {
                    return iteration;
                }
            }
        }
        return null;
    }

    /** 
     * Metodo che crea la mappa dei comandi disponibili per il gioco.
     * @return Map<String, CommandType>
     */
    static {
        COMMAND.put(CommandType.HELP,  App::handleHelp);
        //COMMAND.put(CommandType.EXIT,  App::handleExit);
        //COMMAND.put(CommandType.START, App::handlePlay);
        //COMMAND.put(CommandType.TABLE, App::handleBoard);
        //COMMAND.put(CommandType.EMPTY, App::handleEmpty);
        //COMMAND.put(CommandType.MOVES, App::handleMoves);
       }


    public static void main(final String[] args) {
        printWelcome(ColorShell.RED);

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
