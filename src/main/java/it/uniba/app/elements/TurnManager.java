package it.uniba.app.elements;

/**
 * Manages the turns of players in the Ataxx game.
 */
public class TurnManager {
    private Player whitePlayer;
    private Player blackPlayer;
    private boolean isWhiteTurn; // True if it's white player's turn, false if it's black player's turn

    /**
     * Constructor for the TurnManager class.
     *
     * @param whitePlayer The player with the white pieces.
     * @param blackPlayer The player with the black pieces.
     */
    public TurnManager(Player whitePlayer, Player blackPlayer) {
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        this.isWhiteTurn = true; // Assume the white player starts first
        System.out.println("White player: " + whitePlayer.getName());
        System.out.println("Black player: " + blackPlayer.getName());
    }

    /**
     * Retrieves the current player.
     *
     * @return the current player.
     */
    public Player getCurrentPlayer() {
        System.out.println("Current player: " + (isWhiteTurn ? whitePlayer.getName() : blackPlayer.getName()));
        return isWhiteTurn ? whitePlayer : blackPlayer;
    }

    /**
     * Retrieves the opponent player.
     *
     * @return the opponent player.
     */
    public Player getOpponent() {
        return isWhiteTurn ? blackPlayer : whitePlayer;
    }

    /**
     * Switches to the next player's turn.
     */
    public void nextTurn() {
        isWhiteTurn = !isWhiteTurn; // Switch turn to the opposite player
        notifyTurnChange();
        System.out.println("Turn changed. It is now: " + getCurrentPlayer().getName() + "'s turn");
    }

    /**
     * Notifies that the turn has changed.
     */
    private void notifyTurnChange() {
        System.out.println("It is now: " + getCurrentPlayer().getName() + "'s turn");
    }

    /**
     * Retrieves the white player.
     *
     * @return the white player.
     */
    public Player getWhitePlayer() {
        return whitePlayer;
    }

    /**
     * Retrieves the black player.
     *
     * @return the black player.
     */
    public Player getBlackPlayer() {
        return blackPlayer;
    }
}
