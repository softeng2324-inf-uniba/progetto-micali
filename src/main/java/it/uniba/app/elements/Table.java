package it.uniba.app.elements;

import it.uniba.app.features.Utilities;
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
    private boolean[][] blockedCells;
    private int blockedCount = 0; // Contatore delle caselle attualmente bloccate
    private boolean isBlocked = false; // Aggiunto per controllare se il blocco è stato eseguito
    /**
     * Constructs a new Table object with the specified size.
     *
     * @param tableSize The size of the table (number of rows and columns).
     */
    private Table(final int tableSize) {
        this.size = tableSize;
        this.map = new Pawn[tableSize][tableSize];
        this.colorMap = new String[tableSize][tableSize];
        this.blockedCells = new boolean[size][size];
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
                blockedCells[i][j] = false;  // Resetta lo stato bloccato delle celle
            }
        }
        blockedCount = 0;
        isBlocked = false;  // Resetta lo stato bloccato
    }
    /**
     * Sets up the game by configuring the pawns with the appropriate coordinates.
     */
    public void setupGioco() {
        map[0][0] = new Pawn("nero", '\u26C0', "\u001B[40m", 0, 0); // Bianco
        map[0][size - 1] = new Pawn("bianco", '\u26C2', "\u001B[40m", 0, size - 1); // Nero
        map[size - 1][0] = new Pawn("bianco", '\u26C2', "\u001B[40m", size - 1, 0); // Nero
        map[size - 1][size - 1] = new Pawn("nero", '\u26C0', "\u001B[40m", size - 1, size - 1); // Bianco
    }
    /**
     * Sets up the color map for specific player positions.
     * This method assigns specific ANSI color codes to certain positions on the game board,
     * representing the colors of player territories.
     */
    public void setColor() {
        colorMap[Utilities.PLAYER_POSITION_1][Utilities.PLAYER_POSITION_2] = Utilities.ANSI_YELLOW_BACKGROUND;
        colorMap[Utilities.PLAYER_POSITION_2][Utilities.PLAYER_POSITION_1] = Utilities.ANSI_YELLOW_BACKGROUND;
        colorMap[Utilities.PLAYER_POSITION_2][Utilities.PLAYER_POSITION_2] = Utilities.ANSI_YELLOW_BACKGROUND;
        colorMap[Utilities.PLAYER_POSITION_1][Utilities.PLAYER_POSITION_3] = Utilities.ANSI_CUSTOM_ORANGE_BACKGROUND;
        colorMap[Utilities.PLAYER_POSITION_2][Utilities.PLAYER_POSITION_3] = Utilities.ANSI_CUSTOM_ORANGE_BACKGROUND;
        colorMap[Utilities.PLAYER_POSITION_3][Utilities.PLAYER_POSITION_1] = Utilities.ANSI_CUSTOM_ORANGE_BACKGROUND;
        colorMap[Utilities.PLAYER_POSITION_3][Utilities.PLAYER_POSITION_2] = Utilities.ANSI_CUSTOM_ORANGE_BACKGROUND;
        colorMap[Utilities.PLAYER_POSITION_3][Utilities.PLAYER_POSITION_3] = Utilities.ANSI_CUSTOM_ORANGE_BACKGROUND;
        colorMap[Utilities.PLAYER_POSITION_4][Utilities.PLAYER_POSITION_4] = Utilities.ANSI_CUSTOM_ORANGE_BACKGROUND;
        colorMap[Utilities.PLAYER_POSITION_4][Utilities.PLAYER_POSITION_5] = Utilities.ANSI_CUSTOM_ORANGE_BACKGROUND;
        colorMap[Utilities.PLAYER_POSITION_4][Utilities.PLAYER_POSITION_6] = Utilities.ANSI_CUSTOM_ORANGE_BACKGROUND;
        colorMap[Utilities.PLAYER_POSITION_5][Utilities.PLAYER_POSITION_4] = Utilities.ANSI_CUSTOM_ORANGE_BACKGROUND;
        colorMap[Utilities.PLAYER_POSITION_6][Utilities.PLAYER_POSITION_4] = Utilities.ANSI_CUSTOM_ORANGE_BACKGROUND;
        colorMap[Utilities.PLAYER_POSITION_5][Utilities.PLAYER_POSITION_5] = Utilities.ANSI_YELLOW_BACKGROUND;
        colorMap[Utilities.PLAYER_POSITION_5][Utilities.PLAYER_POSITION_6] = Utilities.ANSI_YELLOW_BACKGROUND;
        colorMap[Utilities.PLAYER_POSITION_6][Utilities.PLAYER_POSITION_5] = Utilities.ANSI_YELLOW_BACKGROUND;
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
                if (pawn != null && pawn.getOwner() != null && !pawn.getOwner().isEmpty()) {
                    // Assicurati di stampare solo il carattere Unicode della pedina
                    String display = String.format(" %c ", pawn.getUnicodeCharacter());
                    System.out.print(display + "|");
                } else {
                    System.out.print("   |"); // Celle vuote
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
                String color = (colorMap[i][j] != null) ? colorMap[i][j] : Utilities.ANSI_RESET;
                String content = "  ";  // Due spazi per default
                if (pawn != null && pawn.getOwner() != null && !pawn.getOwner().isEmpty()) {
                    content = " " + pawn.getUnicodeCharacter();
                }
                System.out.print(String.format("%s%-3s%s|",
                    color, content, Utilities.ANSI_RESET));
            }
            System.out.println(" " + (i + 1));
            System.out.println("  +---+---+---+---+---+---+---+");
        }
        System.out.println("   a   b   c   d   e   f   g");
    }
    /**
     * Prints the map with the current state of the game.
     * Each cell is represented by a character and may have a background color.
     * Blocked cells are displayed with a grey background.
     */
    public void printMap3() {
        System.out.println("   a   b   c   d   e   f   g");
        System.out.println("  +---+---+---+---+---+---+---+");
        for (int i = 0; i < size; i++) {
            System.out.print((i + 1) + " |");
            for (int j = 0; j < size; j++) {
                String cellBackground = Utilities.ANSI_RESET;
                String content = "   ";  // Default empty content
                if (blockedCells[i][j]) {
                    cellBackground = Utilities.ANSI_GREY_BACKGROUND;  // Grey background for blocked cells
                    content = " X ";  // Content for blocked cells
                } else if (map[i][j] != null && !map[i][j].getOwner().isEmpty()) {
                    content = " " + map[i][j].getUnicodeCharacter() + " ";  // Content for occupied cells
                }
                System.out.print(String.format("%s%-3s%s|", cellBackground, content, Utilities.ANSI_RESET));
            }
            System.out.println(" " + (i + 1));
            System.out.println("  +---+---+---+---+---+---+---+");
        }
        System.out.println("   a   b   c   d   e   f   g");
    }
    /**
     * Ottiene la pedina nella posizione specificata.
     * @param x la coordinata x (riga) della pedina.
     * @param y la coordinata y (colonna) della pedina.
     * @return la pedina nella posizione specificata, o null se non c'è nessuna pedina.
     */
    public Pawn getPawnAt(final int x, final int y) {
        if (x >= 0 && x < size && y >= 0 && y < size) {
            return map[x][y];
        }
        return null; // Restituisce null se le coordinate sono fuori dai limiti.
    }

    /**
     * Imposta una pedina in una posizione specificata del tavoliere.
     *
     * @param x la coordinata x (riga) dove posizionare la pedina.
     * @param y la coordinata y (colonna) dove posizionare la pedina.
     * @param pawn la pedina da posizionare.
     */
    public void setPawnAt(final int x, final int y, final Pawn pawn) {
        if (x >= 0 && x < size && y >= 0 && y < size) {
            map[x][y] = pawn; // Assicurati che questo aggiorni effettivamente l'array
        }
    }
    /**
     * Rimuove una pedina da una posizione specificata del tavoliere.
     *
     * @param x la coordinata x (riga) da cui rimuovere la pedina.
     * @param y la coordinata y (colonna) da cui rimuovere la pedina.
     */
    public void clearPawnAt(final int x, final int y) {
        if (x >= 0 && x < size && y >= 0 && y < size) {
            map[x][y] = new Pawn("", ' ', "", x, y); // Imposta una pedina vuota
        }
    }
    /**
     * Counts the number of pawns owned by a specific player.
     *
     * @param owner the owner of the pawns to count.
     * @return the number of pawns owned by the specified player.
     */
    public int countPawns(final String owner) {
        int count = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Pawn pawn = getPawnAt(i, j);
                if (pawn != null && pawn.getOwner().equals(owner)) {
                    count++;
                }
            }
        }
        return count;
    }
     /**
     * Blocks a cell at the specified coordinates.
     * @param x the x-coordinate (row) of the cell to block.
     * @param y the y-coordinate (column) of the cell to block.
     * @return true if the cell was successfully blocked, false otherwise.
     */
    public boolean blockCell(final int x, final int y) {
        if (canBlockCell(x, y)) {
            blockedCells[x][y] = true;
            blockedCount++;
            isBlocked = true;  // Imposta lo stato bloccato su vero
            return true;
        }
        return false;
    }

        /**
     * Checks if a position is a start position.
     *
     * @param x the x-coordinate (row) to check.
     * @param y the y-coordinate (column) to check.
     * @return true if the position is a start position, false otherwise.
     */
    private boolean isStartPosition(final int x, final int y) {
        return (x == 0 && (y == 0 || y == size - 1)) || (x == size - 1 && (y == 0 || y == size - 1));
    }
     /**
     * Checks if a cell can be blocked.
     * @param x the x-coordinate (row) of the cell.
     * @param y the y-coordinate (column) of the cell.
     * @return true if the cell can be blocked, false otherwise.
     */
    private boolean canBlockCell(final int x, final int y) {
        if (!isValidPosition(x, y) || blockedCells[x][y] || blockedCount >= Utilities.MAX_BLOCKS
        || isStartPosition(x, y) || isInitialPawnPosition(x, y)) {
            return false;
        }
        // Salva lo stato attuale delle celle bloccate
        boolean[][] tempBlockedCells = new boolean[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                tempBlockedCells[i][j] = blockedCells[i][j];
            }
        }

        // Prova a bloccare la cella
        tempBlockedCells[x][y] = true;

        // Verifica se ci sono almeno due vie libere per la duplicazione o il salto delle pedine di partenza
        return hasTwoFreePaths(tempBlockedCells);
    }

/**
 * Checks if a position is an initial pawn position.
 *
 * @param x the x-coordinate (row) to check.
 * @param y the y-coordinate (column) to check.
 * @return true if the position is an initial pawn position, false otherwise.
 */
    private boolean isInitialPawnPosition(final int x, final int y) {
        return (x == 0 && y == 0) || (x == 0 && y == size - 1)
        || (x == size - 1 && y == 0) || (x == size - 1 && y == size - 1);
    }

    /**
     * Checks if there are at least two free paths for the starting positions.
     *
     * @param tempBlockedCells the temporary blocked cells map.
     * @return true if there are at least two free paths, false otherwise.
     */
    private boolean hasTwoFreePaths(final boolean[][] tempBlockedCells) {
        int[][] directions = {
            {1, 0}, {-1, 0}, {0, 1}, {0, -1},
            {1, 1}, {-1, -1}, {1, -1}, {-1, 1},
            {Utilities.POSITIVE_TWO, 0}, {Utilities.NEGATIVE_TWO, 0}, {0, Utilities.POSITIVE_TWO},
            {0, Utilities.NEGATIVE_TWO}, {Utilities.POSITIVE_TWO, Utilities.POSITIVE_TWO},
            {Utilities.NEGATIVE_TWO, Utilities.NEGATIVE_TWO}, {Utilities.POSITIVE_TWO, Utilities.NEGATIVE_TWO},
            {Utilities.NEGATIVE_TWO, Utilities.POSITIVE_TWO}
        };
    int[][] startPositions = {
        {0, 0}, {0, size - 1}, {size - 1, 0}, {size - 1, size - 1}
    };

    for (int[] startPos : startPositions) {
        int freePaths = 0;
        for (int[] dir : directions) {
            int newX = startPos[0] + dir[0];
            int newY = startPos[1] + dir[1];
            if (isValidPosition(newX, newY) && !tempBlockedCells[newX][newY]) {
                freePaths++;
                if (freePaths >= 2) {
                    break;
                }
            }
        }
        if (freePaths < 2) {
            return false; // Se una posizione di partenza non ha almeno due vie libere, ritorna false
        }
    }
    return true; // Se tutte le posizioni di partenza hanno almeno due vie libere, ritorna true
}



    /**
     * Checks if a position is valid within the table.
     *
     * @param x the x-coordinate (row) to check.
     * @param y the y-coordinate (column) to check.
     * @return true if the position is valid, false otherwise.
     */
    private boolean isValidPosition(final int x, final int y) {
        // Verifica che la posizione non sia fuori dai limiti del tavolo
        return x >= 0 && x < size && y >= 0 && y < size;
    }
    /**
     * Processes a block command from the user input.
     *
     * @param cell the cell to block, in the format "a1", "b2", etc.
     * @return true if the cell was successfully blocked, false otherwise.
     */
    public boolean processBlockCommand(final String cell) {
        try {
            int y = cell.charAt(0) - 'a';
            int x = Integer.parseInt(cell.substring(1)) - 1;
            if (blockCell(x, y)) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Errore: Formato di cella non valido - " + cell);
        }
        return false;
    }

    /**
     * Applies blocked status to cells on the game board.
     * This method iterates through each cell in the game board and if a cell is marked as blocked,
     * it places a 'blocked' pawn on that cell, visually representing the block with a specified color.
     */
    public void applyBlockedCells() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (blockedCells[i][j]) {
                    map[i][j] = new Pawn("blocked", 'X', Utilities.ANSI_GREY_BACKGROUND, i, j);
                }
            }
        }
    }

    /**
     * Returns the count of cells that are currently blocked on the game board.
     *
     * @return The number of blocked cells.
     */
    public int getBlockedCount() {
        return blockedCount;
    }

    /**
     * Sets the blocked status of an entity (such as a cell or player).
     *
     * @param status The new blocked status to set (true for blocked, false for not blocked).
     */
    public void setBlocked(final boolean status) {
        isBlocked = status;
    }

    /**
     * Checks if the entity is currently blocked.
     *
     * @return True if the entity is blocked, false otherwise.
     */
    public boolean getIsBlocked() {
        return isBlocked;
    }

    /**
     * Checks if the table has any blocked cells.
     *
     * @return true if the table has blocked cells, false otherwise.
     */
    public boolean hasBlockedCells() {
        return isBlocked;
    }

     /**
     * Returns the size of the table.
     *
     * @return the size of the table.
     */
    public int getSize() {
        return size;
    }
}
