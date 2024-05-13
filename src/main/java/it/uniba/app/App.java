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
        COMMAND.put(CommandType.EXIT,  App::handleExit);
        //COMMAND.put(CommandType.START, App::handlePlay);
        //COMMAND.put(CommandType.TABLE, App::handleBoard);
        COMMAND.put(CommandType.EMPTY, App::handleEmpty);
        //COMMAND.put(CommandType.MOVES, App::handleMoves);
       }


    
       public static void main(final String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            printWelcome(ColorShell.RED);
            while (!exit) {
                System.out.print("\nMENU : ");
                String input = scanner.next().toLowerCase().trim();
                CommandType command = checkCommand(COMMAND, input);
                if (command != null) {
                    HandleModule handler = COMMAND.get(command);
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


    public static void handleExit(final Scanner input , final Scanner value , final CommandType command) throws IOException {
        
        Scanner choice = new Scanner(System.in);
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
    public static void handleEmpty(final Scanner input, final Scanner value, final CommandType command) throws IOException {
       // Creazione della tabella vuota
    char[][] table = new char[7][7];
    // Riempimento della tabella con spazi vuoti
    for (int i = 0; i < 7; i++) {
        for (int j = 0; j < 7; j++) {
            table[i][j] = ' ';
        }
    }
    
    // Visualizzazione della numerazione superiore
    System.out.println("   a   b   c   d   e   f   g");
    System.out.println(" +---+---+---+---+---+---+---+");
    // Visualizzazione della tabella
    for (int i = 0; i < 7; i++) {
        // Numerazione sinistra
        System.out.print((i + 1) + "| ");
        for (int j = 0; j < 7; j++) {
            System.out.print(table[i][j] + " | ");
        }
        // Numerazione destra
        System.out.println(" " + (i + 1));
        // Linea divisoria orizzontale
        if (i < 6) {
            System.out.println(" +---+---+---+---+---+---+---+");
        }
    }
    // Numerazione inferiore
    System.out.println(" +---+---+---+---+---+---+---+");
    System.out.println("   a   b   c   d   e   f   g");
    }
    }
    
    
