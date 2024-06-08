package it.uniba.app.elements;

/**
 * Class that manages the player in the game Ataxx.
 * <b>Class Type:</b> <Entity>
 * <b>Responsibilities:</b>
 * - Manage the time spent on each move.
 * - Track the number of captured pawns.
 */
public class Player {
    private String name;
    private String color;  // Player's pawn color, e.g., "white" or "black".
    private long timeSpent;  // Time spent in milliseconds for the current turn.
    private int capturedPawns;  // Number of opponent pawns captured.

    /**
     * Constructor for the player.
     * @param playerName The name of the player.
     * @param playerColor The color of the player's pawns.
     */
    public Player(final String playerName, final String playerColor) {
        this.name = playerName;
        this.color = playerColor;
        this.timeSpent = 0;
        this.capturedPawns = 0;
    }

    /**
     * Gets the name of the player.
     * @return The name of the player.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the color of the player's pawns.
     * @return The color of the player's pawns.
     */
    public String getColor() {
        return color;
    }

    /**
     * Gets the time spent for the current turn.
     * @return The time spent in milliseconds.
     */
    public long getTimeSpent() {
        return timeSpent;
    }

    /**
     * Sets the time spent for the current turn.
     * @param newTimeSpent The time spent in milliseconds.
     */
    public void setTimeSpent(final long newTimeSpent) {
        this.timeSpent = newTimeSpent;
    }

    /**
     * Gets the number of captured pawns.
     * @return The number of captured pawns.
     */
    public int getCapturedPawns() {
        return capturedPawns;
    }

    /**
     * Sets the number of captured pawns.
     * @param newCapturedPawns The number of captured pawns to be set.
     */
    public void setCapturedPawns(final int newCapturedPawns) {
        this.capturedPawns = newCapturedPawns;
    }

    /**
     * Increases the number of pawns captured by the player.
     * @param additionalPawns The number of additional pawns captured.
     */
    public void addCapturedPawns(final int additionalPawns) {
        this.capturedPawns += additionalPawns;
    }

    /**
     * Resets the time spent for the current turn.
     */
    public void resetTimeSpent() {
        this.timeSpent = 0;
    }

    /**
     * Returns the string representation of the player.
     * @return A string representing the player.
     */
    @Override
    public String toString() {
        return "Player{name='" + name + "', color='" + color + "'}";
    }
}
