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
     * @param name The name of the player.
     * @param color The color of the player's pawns.
     */
    public Player(String name, String color) {
        this.name = name;
        this.color = color;
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
     * @param timeSpent The time spent in milliseconds.
     */
    public void setTimeSpent(long timeSpent) {
        this.timeSpent = timeSpent;
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
     * @param capturedPawns The number of captured pawns.
     */
    public void setCapturedPawns(int capturedPawns) {
        this.capturedPawns = capturedPawns;
    }

    /**
     * Increases the number of pawns captured by the player.
     * @param additionalPawns The number of additional pawns captured.
     */
    public void addCapturedPawns(int additionalPawns) {
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
    public String toString() {
        return "Player{name='" + name + "', color='" + color + "'}";
    }
}
