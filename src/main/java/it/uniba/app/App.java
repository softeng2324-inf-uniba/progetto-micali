package it.uniba.app;

import it.uniba.app.elements.Game;
import it.uniba.app.elements.Table;
import it.uniba.app.features.ColorShell;
import it.uniba.app.features.ViewResult;
import it.uniba.app.interfaces.HandleModule;

import java.io.IOException;
import java.util.Scanner;
import it.uniba.app.features.CommandType;
import java.util.Map;
import java.util.HashMap;
import java.util.regex.Pattern;


/**
 * Main class of the application.
 */
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.io.IOException;

/**
 * The main class of the application.
 */
public final class App {

    public static boolean exit = false;
    public static final Map<CommandType, HandleModule> PRE_COMMAND = new HashMap<>();
    public static final Map<CommandType, HandleModule> POST_COMMAND = new HashMap<>();
    public static final Table table = Table.getInstance(7);
    private static Game game;

    /**
     * Get a greeting sentence.
     *
     * @return the "Hello World!" string.
     */
    public static String getGreeting() {
        return "Hello World!!";
    }

    /**
     * Get a message from a ViewResult.
     *
     * @param msg the ViewResult object.
     * @return the message string.
     */
    public static String getMessage(final ViewResult msg) {
        return msg.getMessage();
    }

    /**
     * Print the welcome message.
     *
     * @param color the color of the message.
     */
    public static void printWelcome(final ColorShell color) {
        System.out.print(getMessage(ViewResult.WELCOME));
        System.out.println("/help per avere un aiuto !");
    }

    /**
     * Check if the given command is valid.
     *
     * @param commands the map of commands.
     * @param value    the command entered by the user.
     * @return the CommandType if the command is valid, null otherwise.
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
     * Initialize the map of available commands for the game.
     */
    static {
        PRE_COMMAND.put(CommandType.HELP,  App::handleHelp);
        PRE_COMMAND.put(CommandType.EXIT,  App::handleExit);
        PRE_COMMAND.put(CommandType.START, App::handlePlay);
        PRE_COMMAND.put(CommandType.EMPTY, App::handleEmpty);
        POST_COMMAND.put(CommandType.GIVE_UP, App::handleGiveUp);
        POST_COMMAND.put(CommandType.SHOW_MOVES, App::handleShowMoves);
        POST_COMMAND.put(CommandType.HELP, App::handleHelp);
        POST_COMMAND.put(CommandType.TABLE, App::handleTable);
    }

    /**
     * The main method of the application.
     *
     * @param args the command line arguments.
     */
    public static void main(final String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            printWelcome(ColorShell.RED);
            while (!exit) {
                System.out.print("\nMENU : ");
                String input = scanner.next().toLowerCase().trim();
                CommandType command = checkCommand(PRE_COMMAND, input);
                if (command != null) {
                    HandleModule handler = PRE_COMMAND.get(command);
                    handler.handle(scanner, new Scanner(input), command);
                } else {
                    System.out.println("Comando non valido, riprova");
                }
            }
        } catch (Exception e) {
            System.out.println("Errore di I/O");
            System.out.println("Errore: " + e.getMessage());
        }
    }

    /**
     * Handle the help command.
     *
     * @param input   the input scanner.
     * @param value   the value scanner.
     * @param command the command type.
     * @throws IOException if an I/O error occurs.
     */
    public static void handleHelp(final Scanner input , final Scanner value , final CommandType command) throws IOException {
        System.out.println("Comandi disponibili: ");
        System.out.println("/gioca/play - Inizia una nuova partita");
        System.out.println("/esci/exit -  Termina il gioco");
        System.out.println("/aiuto/help - Mostra l'elenco dei comandi disponibili");
        System.out.println("/vuoto/empty - Crea un tavoliere vuoto");
        System.out.println("/tavoliere/table -  Mostra il tavoliere di gioco");
        System.out.println("/qualimosse/moves - Mostra le mosse disponibili");
        System.out.println("/abbandona/giveup -  Abbandona la partita in corso");
        System.out.println("\nLista di comandi eseguibili dopo l'avvio di una partita: \n");
        System.out.println("/aiuto/help - Mostra l'elenco dei comandi disponibili");
        System.out.println("/tavoliere/table - Mostra il tavoliere di gioco");
        System.out.println("/abbandona/giveup - Abbandona la partita in corso");
        System.out.println("/qualimosse/moves - Mostra le mosse disponibili");
    }

    /**
     * Handle the play command.
     *
     * @param input   the input scanner.
     * @param value   the value scanner.
     * @param command the command type.
     */
    public static void handlePlay(final Scanner input, final Scanner value, final CommandType command) {
        if (command == CommandType.START) {
            if (game == null || !game.getStateGame()) {
                game = new Game();  // Crea una nuova istanza di Game
                game.setStateGame(true);  // Imposta lo stato del gioco a vero
                game.getTable().setupGioco();  // Configura il tavoliere per un nuovo gioco
                game.getTable().printMap();  // Stampa il tavoliere
            } else {
                System.out.println("Una partita è già in corso. Terminare la partita corrente prima di iniziarne una nuova.");
                return;
            }
        }

        while (game.getStateGame()) {
            try {
                System.out.print("\n | GAME | : ");
                String inputPlay = input.next().toLowerCase().trim();
                CommandType commPlay = checkCommand(POST_COMMAND, inputPlay);
                if (commPlay != null) {
                    HandleModule handler = POST_COMMAND.get(commPlay);
                    if (handler != null) {
                        try {
                            handler.handle(input, new Scanner(inputPlay), commPlay);
                            if (game.getStateGame() && !commPlay.equals(CommandType.GIVE_UP ) && !commPlay.equals(CommandType.TABLE)) {
                                game.getTable().printMap();  // Aggiungi questa riga se vuoi ristampare il tavoliere dopo ogni comando
                            }
                        } catch (IOException e) {
                            System.out.println("Errore di I/O: " + e.getMessage());
                        }
                    }
                } else {
                    System.out.println("Comando non valido, riprova");
                }
            } catch (Exception e) {
                System.out.println("Errore di I/O: " + e.getMessage());
            }
        }

        // Aggiunta di questa riga per chiarire che il gioco è finito e si sta tornando al menu principale.
        if (!game.getStateGame()) {
            System.out.println("Tornando al menu principale...");
        }
    }

    /**
     * Handle the exit command.
     *
     * @param input   the input scanner.
     * @param value   the value scanner.
     * @param command the command type.
     * @throws IOException if an I/O error occurs.
     */
    public static void handleExit(final Scanner input , final Scanner value , final CommandType command) throws IOException {

        try (Scanner choice = new Scanner(System.in)) {
            System.out.println("Sei sicuro di voler uscire dal gioco? (s/n)");
            while (exit == false) {
                String risposta = choice.next().toLowerCase().trim();
                if (risposta.equals("s")) {
                    exit = true;
                } else if (risposta.equals("n")) {
                    exit = false;
                    break;
                } else {
                    System.out.println("Risposta non valida, riprova");
                }
            }
        }
    }

    /**
     * Handle the empty command.
     *
     * @param input   the input scanner.
     * @param value   the value scanner.
     * @param command the command type.
     * @throws IOException if an I/O error occurs.
     */
    public static void handleEmpty(final Scanner input , final Scanner value , final CommandType command)throws IOException{
        table.resetMap();
        table.printMap();
    }

    /**
     * Handle the give up command.
     *
     * @param input   the input scanner.
     * @param value   the value scanner.
     * @param command the command type.
     */
    public static void handleGiveUp(final Scanner input, final Scanner value, final CommandType command) {
        System.out.println("Sicuro di voler abbandonare la partita? (si/no) > ");
        String choice = input.next().trim();
        if (choice.equalsIgnoreCase("si")) {
            System.out.println("\nOk! Hai deciso di abbandonare la partita.");
            game.setStateGame(false); // Imposta lo stato del gioco a falso per terminare la partita
            System.out.println("\nVince il bianco per abbandono!");
            System.out.println("Pedine rimanenti: 2");
            return; // Uscita immediata dal metodo per evitare ulteriori stampa della mappa
        } else if (choice.equalsIgnoreCase("no")) {
            System.out.println("\nLa partita continua. Puoi effettuare nuovi tentativi.");
        } else {
            System.out.println("\nScelta non valida, riprova..");
        }
    }

    /**
     * Handle the show moves command.
     *
     * @param input   the input scanner.
     * @param value   the value scanner.
     * @param command the command type.
     * @throws IOException if an I/O error occurs.
     */
    public static void handleShowMoves(final Scanner input, final Scanner value, final CommandType command)
            throws IOException {
        System.out.println("\n Mosse disponibili : ");
        table.setupGioco();
        table.setColor();
        table.printMap();
        System.out.println("\na) in giallo le caselle raggiungibili con mosse che generano una nuova pedina\r\n" + //
                "b) in arancione raggiungibili con mosse che consentono un salto ");
    }

    /**
     * Handle the table command.
     *
     * @param input   the input scanner.
     * @param value   the value scanner.
     * @param command the command type.
     */
    public static void handleTable(final Scanner input, final Scanner value, final CommandType command) {
        game.getTable().printMap();
    }
}
