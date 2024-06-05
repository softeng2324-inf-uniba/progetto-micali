package it.uniba.app.elements;

import java.util.ArrayList;
import java.util.List;

/**
 * The Game class represents a game instance.
 */
public class Game {
    private boolean stateGame = false;
    private static final int MAX_SIZE = 7;
    private final Table table;
    private final TurnManager turnManager;
    //private List<String> whiteMoves;
    //private List<String> blackMoves;
    private Player winner;

    /**
     * Constructs a new Game instance.
     * The initial state of the game is set to false.
     *
     * @param whitePlayer The player with the white pawns.
     * @param blackPlayer The player with the black pawns.
     */
    public Game(Player whitePlayer, Player blackPlayer) {
        this.stateGame = false;
        this.table = Table.getInstance(MAX_SIZE);
        this.turnManager = new TurnManager(whitePlayer, blackPlayer);
        //this.whiteMoves = new ArrayList<>();
        //this.blackMoves = new ArrayList<>();
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
     * @param newStateGame The new state of the game.
     */
    public void setStateGame(final boolean newStateGame) {
        this.stateGame = newStateGame;
    }

    /**
     * Retrieves the current player from the turn manager.
     *
     * @return The current player.
     */
    public Player getCurrentPlayer() {
        Player currentPlayer = turnManager.getCurrentPlayer();
        System.out.println("Current player in the game: " + currentPlayer.getName() + " (" + currentPlayer.getColor() + ")");
        return currentPlayer;
    }

    /**
     * Retrieves the move manager for the game.
     *
     * @return The move manager.
     */
    public MoveManager getMoveManager() {
        return new MoveManager(table, turnManager, this);
    }

    /**
     * Calculates the winner of the game due to forfeiture.
     */
    public void calculateWinnerDueToForfeit() {
        Player currentPlayer = turnManager.getCurrentPlayer();
        Player opponent = turnManager.getOpponent();
        int currentPlayerCount = table.countPawns(currentPlayer.getColor());
        int opponentPlayerCount = table.countPawns(opponent.getColor());

        // Determine the winner based on pawn counts
        winner = (currentPlayerCount > opponentPlayerCount) ? currentPlayer : opponent;
    }

    /**
     * Displays the results of the game.
     */
    public void displayResults() {
        // Display the final result of the game, including the winner and pawn count
        System.out.println("Il vincitore è : " + winner.getName());
        System.out.println("Pedine rimaste - " + winner.getName() + ": " + table.countPawns(winner.getColor()));
        System.out.println("Pedine rimaste - " + turnManager.getOpponent().getName() + ": " + table.countPawns(turnManager.getOpponent().getColor()));
    }
}
