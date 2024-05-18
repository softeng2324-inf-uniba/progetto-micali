package it.uniba.app.elements;


public class Pawn {
    private String owner;            // Who owns the pawn
    private char unicodeCharacter;   // Visual representation
    private String color;            // Color code for display

    public Pawn(String owner, char unicodeCharacter, String color) {
        this.owner = owner;
        this.unicodeCharacter = unicodeCharacter;
        this.color = color;
    }

    // Getter for owner
    public String getOwner() {
        return owner;
    }

    // Getter for unicode character
    public char getUnicodeCharacter() {
        return unicodeCharacter;
    }

    // Getter for color
    public String getColor() {
        return color;
    }
}

