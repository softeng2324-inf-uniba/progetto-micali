package it.uniba.app.elements;


/**
 * The Table class represents the game board for a specific game.
 * It contains methods to initialize the board, set up the game, and print the board.
 */
/**
 * Represents a game table with pawns arranged in a grid.
 */
public class Table {

    private static Table instance;
    private Pawn[][] map;
    private int size;

    /**
     * Constructs a new Table object with the specified size.
     *
     * @param size The size of the table (number of rows and columns).
     */
    public Table(int size) {
        this.size = size;
        this.map = new Pawn[size][size];
        resetMap();
    }

    /**
     * Returns the singleton instance of the Table class.
     *
     * @param size The size of the table (number of rows and columns).
     * @return The singleton instance of the Table class.
     */
    public static Table getInstance(int size) {
        if (instance == null) {
            instance = new Table(size);
        }
        return instance;
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
     * Sets the color of specific cells on the game map.
     */
    public void setColor() {
        map[0][1] = new Pawn("color", ' ', "\u001B[43m", 0, 0);
        map[1][0] = new Pawn("color", ' ', "\u001B[43m", 0, 0);
        map[1][1] = new Pawn("color", ' ', "\u001B[43m", 0, 0);
        map[0][2] = new Pawn("color", ' ', "\u001B[48;5;202m", 0, 0);
        map[1][2] = new Pawn("color", ' ', "\u001B[48;5;202m", 0, 0);
        map[2][0] = new Pawn("color", ' ', "\u001B[48;5;202m", 0, 0);
        map[2][1] = new Pawn("color", ' ', "\u001B[48;5;202m", 0, 0);
        map[2][2] = new Pawn("color", ' ', "\u001B[48;5;202m", 0, 0);
        map[2][0] = new Pawn("color", ' ', "\u001B[48;5;202m", 2, 0);
        map[4][4] = new Pawn("color", ' ', "\u001B[48;5;202m", 0, 0);
        map[4][5] = new Pawn("color", ' ', "\u001B[48;5;202m", 0, 0);
        map[4][6] = new Pawn("color", ' ', "\u001B[48;5;202m", 0, 0);
        map[5][4] = new Pawn("color", ' ', "\u001B[48;5;202m", 0, 0);
        map[6][4] = new Pawn("color", ' ', "\u001B[48;5;202m", 0, 0);
        map[5][5] = new Pawn("color", ' ', "\u001B[43m", 0, 0);
        map[5][6] = new Pawn("color", ' ', "\u001B[43m", 0, 0);
        map[6][5] = new Pawn("color", ' ', "\u001B[43m", 0, 0);
    }
}