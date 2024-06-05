package it.uniba.app.elements;

/**
 * Class that represents a move in the game Ataxx.
 * <b>Class Type:</b> <Entity>
 * <b>Responsibilities:</b>
 * - Store the start and end coordinates of a move.
 * - Store the player making the move.
 * - Determine if the move is a jump.
 */
public class Move {
    private Coordinate start;
    private Coordinate end;
    private Player player;

    /**
     * Constructor for the Move class.
     * @param start The starting coordinate of the move.
     * @param end The ending coordinate of the move.
     * @param player The player making the move.
     */
    public Move(Coordinate start, Coordinate end, Player player) {
        this.start = start;
        this.end = end;
        this.player = player;
    }

    /**
     * Gets the starting coordinate of the move.
     * @return The starting coordinate.
     */
    public Coordinate getStart() {
        return start;
    }

    /**
     * Gets the ending coordinate of the move.
     * @return The ending coordinate.
     */
    public Coordinate getEnd() {
        return end;
    }

    /**
     * Gets the player making the move.
     * @return The player making the move.
     */
    public Player getPlayer() {
        return player;
    }
}
