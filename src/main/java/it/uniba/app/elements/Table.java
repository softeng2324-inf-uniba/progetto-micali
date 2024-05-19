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
                map[i][j] = new Pawn("", ' ', "", i, j);  // Inizializza con valori predefiniti
            }
        }
    }

    public void setupGioco() {
        // Configura le pedine con le coordinate appropriate
        map[0][0] = new Pawn("Player1", '\u2b24', "\u001B[31m", 0, 0);
        map[0][size - 1] = new Pawn("Player2", '\u2b24', "\u001B[34m", 0, size - 1);
        map[size - 1][0] = new Pawn("Player2", '\u2b24', "\u001B[34m", size - 1, 0);
        map[size - 1][size - 1] = new Pawn("Player1", '\u2b24', "\u001B[31m", size - 1, size - 1);
    }

    public void printMap() {
        System.out.println("   a   b   c   d   e   f   g");
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
        System.out.println("   a   b   c   d   e   f   g");
    }
}