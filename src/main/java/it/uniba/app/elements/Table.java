package it.uniba.app.elements;

import it.uniba.app.features.ColorShell;

public class Table {
    private static Table instance;
    private Pawn[][] map;
    private int size;

    public Table(int size) {
        this.size = size;
        this.map = new Pawn[size][size];
        resetMap();
    }

    public static Table getInstance(int size) {
        if (instance == null) {
            instance = new Table(size);
        }
        return instance;
    }

    public void resetMap() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                map[i][j] = new Pawn(null, ' ', "");  // Usa spazi per celle vuote
            }
        }
    }

    public void setupGioco() {
        // Imposta le pedine come richiesto
        map[0][0] = new Pawn("Player1", '\u2b24', "\u001B[31m"); // Pedina rossa
        map[0][size - 1] = new Pawn("Player2", '\u2b24', "\u001B[34m"); // Pedina blu
        map[size - 1][0] = new Pawn("Player2", '\u2b24', "\u001B[34m"); // Pedina blu
        map[size - 1][size - 1] = new Pawn("Player1", '\u2b24', "\u001B[31m"); // Pedina rossa
    }
    
    
    public void printMap() {
        System.out.println("   a   b   c   d   e   f    g");
        System.out.println("  +---+---+---+---+---+---+---+");
        for (int i = 0; i < size; i++) {
            System.out.print(" " + (i + 1) + "|");
            for (int j = 0; j < size; j++) {
                Pawn pawn = map[i][j];
                if (pawn != null && pawn.getOwner() != null) {
                    // Stampa la pedina con il colore specificato
                    System.out.print(pawn.getColor() + " " + pawn.getUnicodeCharacter() + " \u001B[0m|");
                } else {
                    // Celle vuote
                    System.out.print("   |");
                }
            }
            System.out.println(" " + (i + 1) );
            System.out.println("  +---+---+---+---+---+---+---+");
        }
        System.out.println("   a   b   c   d   e    f   g");
    }
}


    
    
    
    


/*
 *  public void setupCentralPawnAndColorMoves() {
        resetMap();
        int center = size / 2;
        map[center][center] = new Pawn("X", ColorShell.GREEN);
        for (int i = center - 1; i <= center + 1; i++) {
            for (int j = center - 1; j <= center + 1; j++) {
                if (!(i == center && j == center)) {
                    map[i][j] = new Pawn(" ", ColorShell.YELLOW); // Caselle gialle
                }
            }
        }
    }

    public void setupSecondBoardConfiguration() {
        resetMap();
        int center = size / 2;
        map[center][center] = new Pawn("X", ColorShell.GREEN);
        // Configura le mosse che consentono un salto
        int[] jumpMoves = {-2, 0, 2}; // Posizioni relative per salti
        for (int dx : jumpMoves) {
            for (int dy : jumpMoves) {
                int nx = center + dx;
                int ny = center + dy;
                if (nx >= 0 && nx < size && ny >= 0 && ny < size) {
                    map[nx][ny] = new Pawn(" ", ColorShell.ORANGE_1); // Caselle arancioni
                }
            }
        }
    }


    public void setupThirdBoardConfiguration() {
    resetMap();
    int center = size / 2;
    map[center][center] = new Pawn("X", ColorShell.GREEN);
    // Configura una combinazione di mosse di tipo a) e b)
    // Questo è solo un esempio, definisci le posizioni in base alle regole del tuo gioco
    setupCentralPawnAndColorMoves(); // Utilizza il primo metodo come base
    setupSecondBoardConfiguration(); // Sovrappone il secondo metodo per aggiungere ulteriori mosse
}
 * 
 */
   

    
    
    
    



