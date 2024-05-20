package it.uniba.app.elements;


/**<Entity> class
 * The Game class represents a game instance.
 */
public class Game {

    private boolean stateGame = false;

    private Table table = new Table(7);

    /**
     * Constructs a new Game instance.
     * The initial state of the game is set to false.
     */
    public Game() {
        stateGame = false;
    }

    /**
     * Retrieves the table associated with the game.
     *
     * @return The table object.
     */
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
     * @param stateGame The new state of the game.
     */
    public void setStateGame(boolean stateGame) {
        this.stateGame = stateGame;
    }
}