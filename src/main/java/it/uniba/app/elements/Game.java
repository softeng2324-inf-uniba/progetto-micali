package it.uniba.app.elements;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * <Entity> class
 * The Game class represents a game instance.
 */
public class Game {

    private boolean stateGame = false;
    private static final int MAX_SIZE = 7;

    private final Table table;

    /**
     * Constructs a new Game instance.
     * The initial state of the game is set to false.
     */
    public Game() {
        this.stateGame = false;
        this.table = Table.getInstance(MAX_SIZE); // Usa il singleton per ottenere l'istanza di Table
    }

    /**
     * Retrieves the table associated with the game.
     *
     * @return The table object.
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Table is immutable and safe to return")
    public Table getTable() {
        return table;
    }

    /**
     * Retrieves the state of the game.
     *
     * @return The state of the game.
     */
    public boolean getStateGame() {
        return stateGame;
    }

    /**
     * Sets the state of the game.
     *
     * @param newStateGame The new state of the game.
     */
    public void setStateGame(final boolean newStateGame) {
        this.stateGame = newStateGame;
    }
}
