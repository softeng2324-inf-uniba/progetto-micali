package it.uniba.app.elements;

/**
 * Manages the turns of players in the Ataxx game.
 */
public class TurnManager {
    private final Player whitePlayer;
    private final Player blackPlayer;
    private boolean isWhiteTurn;
    /**
     * Constructor for the TurnManager class.
     * Assumes Player is immutable or creates a defensive copy.
     * @param initialWhitePlayer The player with the white pieces.
     * @param initialBlackPlayer The player with the black pieces.
     */
    public TurnManager(final Player initialWhitePlayer, final Player initialBlackPlayer) {
        this.whitePlayer = new Player(initialWhitePlayer.getName(), initialWhitePlayer.getColor());
        this.blackPlayer = new Player(initialBlackPlayer.getName(), initialBlackPlayer.getColor());
        this.isWhiteTurn = true; // Assume the white player starts first
    }

    /**
     * Retrieves the current player by returning a defensive copy.
     *
     * @return a new Player instance representing the current player.
     */
    public Player getCurrentPlayer() {
        return isWhiteTurn ? new Player(whitePlayer.getName(), whitePlayer.getColor())
                           : new Player(blackPlayer.getName(), blackPlayer.getColor());
    }

    /**
     * Retrieves the opponent player by returning a defensive copy.
     *
     * @return a new Player instance representing the opponent player.
     */
    public Player getOpponent() {
        return isWhiteTurn ? new Player(blackPlayer.getName(), blackPlayer.getColor())
                           : new Player(whitePlayer.getName(), whitePlayer.getColor());
    }

    /**
     * Retrieves the white player.
     *
     * @return a new Player instance representing the white player.
     */
    public Player getWhitePlayer() {
        return new Player(whitePlayer.getName(), whitePlayer.getColor());
    }

    /**
     * Retrieves the black player.
     *
     * @return a new Player instance representing the black player.
     */
    public Player getBlackPlayer() {
        return new Player(blackPlayer.getName(), blackPlayer.getColor());
    }

    /**
     * Switches to the next player's turn.
     */
    public void nextTurn() {
        isWhiteTurn = !isWhiteTurn; // Switch turn to the opposite player
        notifyTurnChange();
    }

    /**
     * Notifies that the turn has changed.
     */
    private void notifyTurnChange() {
        System.out.println("Turno cambiato. È il turno di " + getCurrentPlayer().getName());
    }
}
