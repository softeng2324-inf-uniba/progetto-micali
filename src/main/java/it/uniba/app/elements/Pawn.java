package it.uniba.app.elements;

public class Pawn {
    private String owner;            // Chi possiede la pedina
    private char unicodeCharacter;   // Rappresentazione Unicode della pedina
    private String color;            // Codice colore per la visualizzazione
    private int x;                   // Posizione x (riga)
    private int y;                   // Posizione y (colonna)

    public Pawn(String owner, char unicodeCharacter, String color, int x, int y) {
        this.owner = owner;
        this.unicodeCharacter = unicodeCharacter;
        this.color = color;
        this.x = x;
        this.y = y;
    }

    // Getter and setter methods
    public String getOwner() {
        return owner;
    }

    public char getUnicodeCharacter() {
        return unicodeCharacter;
    }

    public String getColor() {
        return color;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}