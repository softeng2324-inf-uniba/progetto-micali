package it.uniba.app.elements;

/**
 * Manages the turns of players in the game Ataxx.
 */
public class TurnManager {
    private Player whitePlayer;
    private Player blackPlayer;
    private boolean isWhiteTurn; // True if it's the white player's turn, false if it's the black player's turn

    /**
     * Constructor for the TurnManager class.
     * @param whitePlayer The player with the white pawns.
     * @param blackPlayer The player with the black pawns.
     */
    public TurnManager(Player whitePlayer, Player blackPlayer) {
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        this.isWhiteTurn = true; // Assumes the white player starts first
        System.out.println("White player: " + whitePlayer.getName());
        System.out.println("Black player: " + blackPlayer.getName());
    }

    /**
     * Gets the current player.
     * @return The current player.
     */
    public Player getCurrentPlayer() {
        System.out.println("Current player: " + (isWhiteTurn ? whitePlayer.getName() : blackPlayer.getName()));
        return isWhiteTurn ? whitePlayer : blackPlayer;
    }

    /**
     * Gets the opponent player.
     * @return The opponent player.
     */
    public Player getOpponent() {
        return isWhiteTurn ? blackPlayer : whitePlayer;
    }

    /**
     * Switches to the next turn.
     */
    public void nextTurn() {
        isWhiteTurn = !isWhiteTurn; // Switches to the opposite player's turn
        notifyTurnChange();
        System.out.println("Turn changed. It's now: " + getCurrentPlayer().getName() + "'s turn.");
    }

    /**
     * Notifies that the turn has changed.
     */
    private void notifyTurnChange() {
        System.out.println("It's now: " + getCurrentPlayer().getName() + "'s turn.");
    }
}
