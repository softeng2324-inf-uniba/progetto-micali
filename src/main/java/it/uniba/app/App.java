package it.uniba.app;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.nio.charset.StandardCharsets;

import it.uniba.app.elements.Game;
import it.uniba.app.elements.Move;
import it.uniba.app.elements.MoveParser;
import it.uniba.app.elements.Player;
import it.uniba.app.elements.Table;
import it.uniba.app.features.ColorShell;
import it.uniba.app.features.CommandType;
import it.uniba.app.features.PlayTime;
import it.uniba.app.features.ViewResult;
import it.uniba.app.interfaces.HandleModule;
import it.uniba.app.features.Utilities;

/**
 * <Boundary> Class
 * The main class of the application.
 */
public final class App {

    private App() {
        throw new AssertionError("Non istanziare una classe di utilità");
    }

    private static boolean exit = false;
    private static final Map<CommandType, HandleModule> PRE_COMMAND = new HashMap<>();
    private static final Map<CommandType, HandleModule> POST_COMMAND = new HashMap<>();
    private static final PlayTime playTime = new PlayTime();
    private static Table tavoliere = Table.getInstance(Utilities.DIMENSION);
    private static volatile Game game;

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
     * @param value the command entered by the user.
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
    static {
        PRE_COMMAND.put(CommandType.HELP, App::handleHelp);
        PRE_COMMAND.put(CommandType.EXIT, App::handleExit);
        PRE_COMMAND.put(CommandType.START, App::handlePlay);
        PRE_COMMAND.put(CommandType.EMPTY, App::handleEmpty);
        PRE_COMMAND.put(CommandType.BLOCKCELL, App::handleBlockCell);
        POST_COMMAND.put(CommandType.GIVE_UP, App::handleGiveUp);
        POST_COMMAND.put(CommandType.SHOW_MOVES, App::handleShowMoves);
        POST_COMMAND.put(CommandType.HELP, App::handleHelp);
        POST_COMMAND.put(CommandType.TABLE, App::handleTable);
        POST_COMMAND.put(CommandType.CAPTURE, App::handleCapture);
        POST_COMMAND.put(CommandType.OLD_MOVES, App::handleOldMoves);
        POST_COMMAND.put(CommandType.TIME, App::handleTime); 
    }

    /**
     * The main method of the application.
     *
     * @param args the command line arguments.
     */
    public static void main(final String[] args) {
        try (Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8)) {
            printWelcome(ColorShell.RED);
            while (!exit) {
                System.out.print("\nMENU : ");
                String input = scanner.next().toLowerCase().trim();
                CommandType command = checkCommand(PRE_COMMAND, input);
                if (command != null) {
                    HandleModule handler = PRE_COMMAND.get(command);
                    handler.handle(scanner, new Scanner(input), command); // Non è necessario specificare il charset qui
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
     * @param input the input scanner.
     * @param value the value scanner.
     * @param command the command type.
     * @throws IOException if an I/O error occurs.
     */
    public static void handleHelp(final Scanner input, final Scanner value, final CommandType command)
    throws IOException {
        String format = "%-20s %s%n";

        System.out.println("\nComandi disponibili:");
        System.out.printf(format, "/gioca/play", "Inizia una nuova partita");
        System.out.printf(format, "/esci/exit", "Termina il gioco");
        System.out.printf(format, "/aiuto/help", "Mostra l'elenco dei comandi disponibili");
        System.out.printf(format, "/vuoto/empty", "Crea un tavoliere vuoto");
        System.out.printf(format, "/tavoliere/table", "Mostra il tavoliere di gioco");
        System.out.printf(format, "/qualimosse/moves", "Mostra le mosse disponibili");
        System.out.printf(format, "/bloccaxn/blockxn", "Blocca le celle del tavoliere (MAX 9 celle)");

        System.out.println("\nLista di comandi eseguibili dopo l'avvio di una partita:");
        System.out.printf(format, "/mosse/oldmoves", "Mostra le mosse effettuate");
        System.out.printf(format, "/aiuto/help", "Mostra l'elenco dei comandi disponibili");
        System.out.printf(format, "/tavoliere/table", "Mostra il tavoliere di gioco");
        System.out.printf(format, "/abbandona/giveup", "Abbandona la partita in corso");
        System.out.printf(format, "/qualimosse/moves", "Mostra le mosse disponibili");
        System.out.printf(format, "/tempo/time", "Mostra il tempo trascorso durante la partita");
    }
    // Ensure other methods are properly formatted, no trailing spaces, and do not exceed 120 characters per line.

    /**
     * Handle the play command.
     *
     * @param input   the input scanner.
     * @param value   the value scanner.
     * @param command the command type.
     */
    public static synchronized void handlePlay(final Scanner input, final Scanner value, final CommandType command)
    throws IOException {
    if (command == CommandType.START) {
        if (game == null || !game.getStateGame()) {
            Player whitePlayer = new Player("Player 1", "bianco");
            Player blackPlayer = new Player("Player 2", "nero");
            game = new Game(whitePlayer, blackPlayer);
            game.setStateGame(true);
            playTime.start();
            if (tavoliere.hasBlockedCells()) {
                tavoliere.applyBlockedCells();
            } else {
                tavoliere.setupGioco();
            }
            tavoliere.printMap3(); // Stampa la mappa dopo aver applicato eventuali celle bloccate
        } else {
            System.out.println("Una partita è già in corso. Terminare la partita corrente \n"
                + "prima di iniziarne una nuova.");
            return;
        }
    }
    while (game.getStateGame()) {
        try {
            System.out.print("\n\n | GAME | : ");
            String inputPlay = input.next().toLowerCase().trim();
            CommandType commPlay = checkCommand(POST_COMMAND, inputPlay);
            if (commPlay != null) {
                HandleModule handler = POST_COMMAND.get(commPlay);
                if (handler != null) {
                    handler.handle(input, new Scanner(inputPlay), commPlay);
                }
            } else {
                System.out.println("Comando non valido, riprova");
            }
        } catch (Exception e) {
            System.out.println("Errore di I/O: " + e.getMessage());
        }
    }
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
    public static void handleExit(final Scanner input, final Scanner value, final CommandType command)
            throws IOException {
        boolean handleLoop = true;
        while (handleLoop) {
            System.out.print("\nSicuro di voler uscire? (si/no) > ");
            String choice = input.next();
            choice = choice.trim();
            switch (choice.toLowerCase()) {
                case "si" -> {
                    System.out.println("\nArrivederci e grazie per aver giocato con noi!");
                    System.out.println("Chiusura in corso..");
                    exit = true;
                    handleLoop = false;
                }
                case "no" -> {
                    System.out.println("\nTornando al menu principale..");
                    handleLoop = false;
                }
                default -> System.out.println("\nScelta non valida, riprova..");
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
    public static void handleEmpty(final Scanner input, final Scanner value, final CommandType command)
     throws IOException {
        tavoliere.resetMap();
        tavoliere.printMap();
        System.out.println("Tavoliere vuoto creato");
    }

    /**
     * Handle the give up command.
     *
     * @param input   the input scanner.
     * @param value   the value scanner.
     * @param command the command type.
     */
        // Supponendo che questi metodi siano quelli intorno alle righe menzionate:
    public static void handleGiveUp(final Scanner input, final Scanner value, final CommandType command)
        throws IOException {
        System.out.println("Sicuro di voler abbandonare la partita? (si/no) > ");
        String choice = input.next().trim();
        if (choice.equalsIgnoreCase("si")) {
        System.out.println("\nOk! Hai deciso di abbandonare la partita.");
        if (game != null) {
            game.calculateWinnerDueToForfeit();
            game.displayResults();
            game.setStateGame(false);
            if(game.getStateGame()){
                playTime.stop();
                System.out.println("Tempo trascorso (hh:mm:ss): " + playTime.getElapsedTimeFormatted());
            }
            tavoliere.resetMap();
            tavoliere.setBlocked(false);
        } else {
            System.out.println("Nessuna partita attiva al momento.");
        }
        return;
        } else if (choice.equalsIgnoreCase("no")) {
        System.out.println("\nLa partita continua. Puoi effettuare nuovi tentativi.");
        } else {
        System.out.println("\nScelta non valida, riprova..");
        }
    }

    /**
     * Handle the show moves command.
     * @param input   the input scanner.
     * @param value   the value scanner.
     * @param command the command type.
     * @throws IOException if an I/O error occurs.
     */
    public static void handleShowMoves(final Scanner input, final Scanner value, final CommandType command)
        throws IOException {
        System.out.println("\na)in giallo le caselle raggiungibili con mosse che generano una nuova pedina\n"
        + "b) in arancione raggiungibili con mosse che consentono un salto");
        tavoliere = new Table(tavoliere.getSize());
        tavoliere.setupGioco();
        tavoliere.setColor();
        tavoliere.printMap2();
    }

    /**
     * Handle the table command.
     *
     * @param input   the input scanner.
     * @param value   the value scanner.
     * @param command the command type.
     */
    public static void handleTable(final Scanner input, final Scanner value, final CommandType command)
     throws IOException {
        game.getTable().printMap();
    }

        /**
     * Handles the capture operation in a game based on user input.
     * This method reads a move from the user, attempts to parse and execute it. If the move is valid,
     * it updates the game state and displays the game board. If the move is invalid, it prompts the user to try again.
     * This method can throw an IOException if there is a problem with input handling.
     *
     * @param input     The Scanner instance used for additional input operations. Not used in this method.
     * @param value     The Scanner instance used to read the move input from the user.
     * @param command   The CommandType instance that specifies the type of command. Not used in this method.
     * @throws IOException If an input or output exception occurred.
     */
    public static void handleCapture(final Scanner input, final Scanner value, final CommandType command)
    throws IOException {
        String moveInput = value.nextLine().trim();
        Player currentPlayer = game.getCurrentPlayer(); // Get the current player from the game
        try {
            Move move = MoveParser.parseMove(moveInput, currentPlayer);
            if (game.getMoveManager().makeMove(move)) {
                System.out.println("\nMossa valida!");
                game.getTable().printMap();
            } else {
                System.out.println("\nMossa non valida, riprova.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

        /**
     * Handles the display of previous moves in the game.
     * This method prints all previously executed moves to provide a gameplay history.
     * @param input     The Scanner instance used to read user input. Not used in this method.
     * @param value     Another Scanner instance, potentially for different input purposes. Not used in this method.
     * @param command   The CommandType instance that specifies the type of command. Not used in this method.
     */
    public static void handleOldMoves(final Scanner input, final Scanner value, final CommandType command) {
        game.printMoves();
    }

    /**
     * Handles the operation of blocking cells on the game board.
     * This method resets the game board, prompts the user to enter coordinates of cells to block until the maximum
     * number of blockable cells is reached or the user types 'fine'.
     * It confirms each blocked cell and provides feedback on successful or unsuccessful attempts to block a cell.
     *
     * @param input     The Scanner instance used to read user input. It reads the coordinates of cells to block.
     * @param value     Another Scanner instance, potentially for different input purposes. Not used in this method.
     * @param command   The CommandType instance that specifies the type of command. Not used in this method.
     */
    public static void handleBlockCell(final Scanner input, final Scanner value, final CommandType command) {
        tavoliere.resetMap();  // Ensure the board is clear before starting to block cells
        System.out.println("Inserisci le coordinate delle celle da bloccare (es. d4, d5), "
                            + "digita 'fine' per terminare:");
        tavoliere.setupGioco();
        tavoliere.printMap3();  // Show the empty board for initial confirmation
        String inputCell;
        inputCell = input.next().trim();
        while (tavoliere.getBlockedCount() < Utilities.MAX_BLOCKS && !inputCell.equalsIgnoreCase("fine")) {
            if (tavoliere.processBlockCommand(inputCell)) {
                System.out.println("Cella " + inputCell + " bloccata.");
                System.out.println("\nPuoi ancora bloccare "
                   + (Utilities.MAX_BLOCKS - tavoliere.getBlockedCount()) + " celle.");
                   tavoliere.printMap3();  // Update and show the board every time a cell is blocked
            } else {
                System.out.println("Impossibile bloccare la cella " + inputCell + ", riprova.");
            }
            if (tavoliere.getBlockedCount() < Utilities.MAX_BLOCKS) {
                System.out.println("Inserisci una nuova cella da bloccare o digita 'fine' per terminare:");
            }
            inputCell = input.next().trim(); // Ripeti l'assegnazione alla fine del ciclo
        }
        if (tavoliere.getBlockedCount() >= Utilities.MAX_BLOCKS) {
            System.out.println("Hai raggiunto il limite massimo di celle bloccate.");
        }
        System.out.println("\nBlocco delle celle completato.");
    }

    public static void handleTime(final Scanner input, final Scanner value, final CommandType command)
    throws IOException {
        System.out.println("Tempo trascorso (hh:mm:ss): " + playTime.getElapsedTimeFormatted());
    }
}
