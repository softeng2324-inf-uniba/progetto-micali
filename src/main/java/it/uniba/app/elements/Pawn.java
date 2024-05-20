package it.uniba.app.elements;

/**
 * Class representing a pawn in the game.
 */

public class Pawn {
    private String owner;            // Who owns the pawn
    private char unicodeCharacter;   // Unicode representation of the pawn
    private String color;            // Color code for visualization
    private int x;                   // x position (row)
    private int y;                   // y position (column)

    /**
     * Constructs a new Pawn object with the specified owner, unicode character, color, x position, and y position.
     *
     * @param owner The owner of the pawn
     * @param unicodeCharacter The Unicode character of the pawn
     * @param color The color code of the pawn
     * @param x The x position of the pawn
     * @param y The y position of the pawn
     */
    public Pawn(String owner, char unicodeCharacter, String color, int x, int y) {
        this.owner = owner;
        this.unicodeCharacter = unicodeCharacter;
        this.color = color;
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the owner of the pawn.
     *
     * @return The owner of the pawn
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Returns the Unicode character of the pawn.
     *
     * @return The Unicode character of the pawn
     */
    public char getUnicodeCharacter() {
        return unicodeCharacter;
    }

    /**
     * Returns the color code of the pawn.
     *
     * @return The color code of the pawn
     */
    public String getColor() {
        return color;
    }

    /**
     * Returns the x position of the pawn.
     *
     * @return The x position of the pawn
     */
    public int getX() {
        return x;
    }

    /**
     * Sets the x position of the pawn.
     *
     * @param x The new x position of the pawn
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Returns the y position of the pawn.
     *
     * @return The y position of the pawn
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the y position of the pawn.
     *
     * @param y The new y position of the pawn
     */
    public void setY(int y) {
        this.y = y;
    }
}