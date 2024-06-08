package it.uniba.app.elements;

/**
 * Class representing a pawn in the game. This class is immutable.
 */
public class Pawn {
    /** Who owns the pawn. */
    private final String owner;

    /** Unicode representation of the pawn. */
    private final char unicodeCharacter;

    /** Color code for visualization. */
    private final String color;

    /** x position (row). */
    private final int x;

    /** y position (column). */
    private final int y;

    /**
     * Constructs a new Pawn object with the specified owner, unicode character, color, x position, and y position.
     *
     * @param pawnOwner The owner of the pawn
     * @param unicodeChar The Unicode character of the pawn
     * @param pawnColor The color code of the pawn
     * @param posX The x position of the pawn
     * @param posY The y position of the pawn
     */
    public Pawn(final String pawnOwner, final char unicodeChar, final String pawnColor,
                final int posX, final int posY) {
        this.owner = pawnOwner;
        this.unicodeCharacter = unicodeChar;
        this.color = pawnColor;
        this.x = posX;
        this.y = posY;
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
     * Returns the y position of the pawn.
     *
     * @return The y position of the pawn
     */
    public int getY() {
        return y;
    }
}
