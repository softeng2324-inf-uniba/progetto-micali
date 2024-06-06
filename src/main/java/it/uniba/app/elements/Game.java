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
    private final TurnManager turnManager; // Add TurnManager as a member of the class
    private Player winner; // Instance variable to store the winner
    private List<String> whiteMoves;
    private List<String> blackMoves;

    /**
     * Constructs a new Game instance.
     * The initial state of the game is set to false.
     *
     * @param whitePlayer The player with white pawns.
     * @param blackPlayer The player with black pawns.
     */
    public Game(Player whitePlayer, Player blackPlayer) {
        this.stateGame = false;
        this.table = Table.getInstance(MAX_SIZE); // Use singleton to get the Table instance
        this.turnManager = new TurnManager(whitePlayer, blackPlayer); // Initialize TurnManager
        this.whiteMoves=new ArrayList<>();
        this.blackMoves=new ArrayList<>();
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
        return new MoveManager(table, turnManager, this); // Pass 'this' to the MoveManager constructor
    }

    /**
     * Calculates the winner of the game when a player forfeits.
     */
    public void calculateWinnerDueToForfeit() {
        Player currentPlayer = turnManager.getCurrentPlayer();
        Player opponent = turnManager.getOpponent();
        int currentPlayerCount = table.countPawns(currentPlayer.getColor());
        int opponentPlayerCount = table.countPawns(opponent.getColor());
        
        // Declare the winner Player variable
     
        // Determine the winner based on the pawn counts
        winner = (currentPlayerCount > opponentPlayerCount) ? currentPlayer : opponent;
    }

    /**
     * Displays the final result of the game, including the winner and pawn counts.
     */
    public void displayResults() {
        // Display the final result of the game, including the winner and pawn counts
        System.out.println("The winner is: " + winner.getName());
        System.out.println("Remaining pawns - " + winner.getName() + ": " + table.countPawns(winner.getColor()));
        System.out.println("Remaining pawns - " + turnManager.getOpponent().getName() + ": " + table.countPawns(turnManager.getOpponent().getColor()));
    }

    /** 
     * @param move
     */
    // Metodo per aggiungere mosse alla lista
    public void addMove(String move) {
        if (turnManager.getCurrentPlayer().getColor().equals("bianco")) {
            whiteMoves.add(move);
        } else {
            blackMoves.add(move);
        }
    }

    public void printMoves() {
        System.out.println("Mosse giocatore bianco:");
        for (String move : whiteMoves) {
            System.out.print(move + "; ");
        }
        System.out.println("\nMosse giocatore nero:");
        for (String move : blackMoves) {
            System.out.print(move + "; ");
        }
    }
}
