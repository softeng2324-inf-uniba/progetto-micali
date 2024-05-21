package it.uniba.app.elements;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * The Table class represents the game board.
 * It contains methods to initialize the board, set up the game, and print the board.
 */
public final class Table {

    private static volatile Table instance;
    private final Pawn[][] map;
    private final int size;
    private final String[][] colorMap;

    private static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    private static final String ANSI_CUSTOM_ORANGE_BACKGROUND = "\u001B[48;5;202m";
    private static final String ANSI_RESET = "\u001B[0m"; // Per resettare il colore alla fine di ogni cella

    private static final int PLAYER_POSITION_1 = 0;
    private static final int PLAYER_POSITION_2 = 1;
    private static final int PLAYER_POSITION_3 = 2;
    private static final int PLAYER_POSITION_4 = 4;
    private static final int PLAYER_POSITION_5 = 5;
    private static final int PLAYER_POSITION_6 = 6;

    /**
     * Constructs a new Table object with the specified size.
     *
     * @param tableSize The size of the table (number of rows and columns).
     */
    private Table(final int tableSize) {
        this.size = tableSize;
        this.map = new Pawn[tableSize][tableSize];
        this.colorMap = new String[tableSize][tableSize];
        initializeMap();
    }

    /**
     * Returns the singleton instance of the Table class.
     *
     * @param tableSize The size of the table (number of rows and columns).
     * @return The singleton instance of the Table class.
     */
    @SuppressFBWarnings(value = "MS_EXPOSE_REP", justification = "Rationale for why this is safe")

    public static synchronized Table getInstance(final int tableSize) {
        if (instance == null) {
            instance = new Table(tableSize);
        }
        return instance;
    }

    /**
     * Initializes the game map by setting up pawns and colors.
     */
    private void initializeMap() {
        resetMap();
        setupGioco();
        setColor();
    }

    /**
     * Resets the game map by initializing all cells with default values.
     */
    public void resetMap() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                map[i][j] = new Pawn("", ' ', "", i, j);  // Initialize with default values
            }
        }
    }

    /**
     * Sets up the game by configuring the pawns with the appropriate coordinates.
     */
    public void setupGioco() {
        // Configure pawns with appropriate coordinates
        map[0][0] = new Pawn("Player1", '\u26C0', "\u001B[40m", 0, 0); // Bianco
        map[0][size - 1] = new Pawn("Player2", '\u26C2', "\u001B[40m", 0, size - 1); // Nero
        map[size - 1][0] = new Pawn("Player2", '\u26C2', "\u001B[40m", size - 1, 0); // Nero
        map[size - 1][size - 1] = new Pawn("Player1", '\u26C0', "\u001B[40m", size - 1, size - 1); // Bianco
    }

    /**
     * Sets up the color map for specific player positions.
     * This method assigns specific ANSI color codes to certain positions on the game board,
     * representing the colors of player territories.
     */
    public void setColor() {
        colorMap[PLAYER_POSITION_1][PLAYER_POSITION_2] = ANSI_YELLOW_BACKGROUND;
        colorMap[PLAYER_POSITION_2][PLAYER_POSITION_1] = ANSI_YELLOW_BACKGROUND;
        colorMap[PLAYER_POSITION_2][PLAYER_POSITION_2] = ANSI_YELLOW_BACKGROUND;
        colorMap[PLAYER_POSITION_1][PLAYER_POSITION_3] = ANSI_CUSTOM_ORANGE_BACKGROUND;
        colorMap[PLAYER_POSITION_2][PLAYER_POSITION_3] = ANSI_CUSTOM_ORANGE_BACKGROUND;
        colorMap[PLAYER_POSITION_3][PLAYER_POSITION_1] = ANSI_CUSTOM_ORANGE_BACKGROUND;
        colorMap[PLAYER_POSITION_3][PLAYER_POSITION_2] = ANSI_CUSTOM_ORANGE_BACKGROUND;
        colorMap[PLAYER_POSITION_3][PLAYER_POSITION_3] = ANSI_CUSTOM_ORANGE_BACKGROUND;
        colorMap[PLAYER_POSITION_4][PLAYER_POSITION_4] = ANSI_CUSTOM_ORANGE_BACKGROUND;
        colorMap[PLAYER_POSITION_4][PLAYER_POSITION_5] = ANSI_CUSTOM_ORANGE_BACKGROUND;
        colorMap[PLAYER_POSITION_4][PLAYER_POSITION_6] = ANSI_CUSTOM_ORANGE_BACKGROUND;
        colorMap[PLAYER_POSITION_5][PLAYER_POSITION_4] = ANSI_CUSTOM_ORANGE_BACKGROUND;
        colorMap[PLAYER_POSITION_6][PLAYER_POSITION_4] = ANSI_CUSTOM_ORANGE_BACKGROUND;
        colorMap[PLAYER_POSITION_5][PLAYER_POSITION_5] = ANSI_YELLOW_BACKGROUND;
        colorMap[PLAYER_POSITION_5][PLAYER_POSITION_6] = ANSI_YELLOW_BACKGROUND;
        colorMap[PLAYER_POSITION_6][PLAYER_POSITION_5] = ANSI_YELLOW_BACKGROUND;
    }

    /**
     * Prints the game map to the console.
     */
    public void printMap() {
        System.out.println("   a   b   c   d   e   f   g");
        System.out.println("  +---+---+---+---+---+---+---+");
        for (int i = 0; i < size; i++) {
            System.out.print(" " + (i + 1) + "|");
            for (int j = 0; j < size; j++) {
                Pawn pawn = map[i][j];
                if (pawn != null && pawn.getOwner() != null) {
                    // Print the pawn with the specified color
                    System.out.print(pawn.getColor() + " " + pawn.getUnicodeCharacter() + " \u001B[0m|");
                } else {
                    // Empty cells
                    System.out.print("   |");
                }
            }
            System.out.println(" " + (i + 1));
            System.out.println("  +---+---+---+---+---+---+---+");
        }
        System.out.println("   a   b   c   d   e   f   g");
    }

    /**
     * Prints the game map to the console with colors.
     */
    public void printMap2() {
        System.out.println("   a   b   c   d   e   f   g");
        System.out.println("  +---+---+---+---+---+---+---+");
        for (int i = 0; i < size; i++) {
            System.out.print((i + 1) + " |");  // Stampa l'indice di riga con uno spazio
            for (int j = 0; j < size; j++) {
                Pawn pawn = map[i][j];
                String color = (colorMap[i][j] != null) ? colorMap[i][j] : ANSI_RESET;  // Colore dalla mappa o reset
                String content = "  ";  // Due spazi per default
                if (pawn != null && pawn.getOwner() != null && !pawn.getOwner().isEmpty()) {
                    content = " " + pawn.getUnicodeCharacter();  // Uno spazio e il carattere della pedina
                }
                // Utilizza String.format per garantire che il contenuto abbia sempre lo stesso numero di spazi visibili
                System.out.print(String.format("%s%-3s%s|",
                    color, content, ANSI_RESET));  // Allinea a sinistra con larghezza totale di 3 caratteri
            }
            System.out.println(" " + (i + 1));
            System.out.println("  +---+---+---+---+---+---+---+");
        }
        System.out.println("   a   b   c   d   e   f   g");
    }
}
