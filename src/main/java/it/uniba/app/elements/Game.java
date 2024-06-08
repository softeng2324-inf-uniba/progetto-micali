package it.uniba.app.elements;

import java.util.ArrayList;
import java.util.List;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Represents a game instance in Ataxx, managing game state, player turns, and moves.
 */
public class Game {

    private boolean stateGame = false;
    private static final int MAX_SIZE = 7;
    private final Table table;
    private final TurnManager turnManager;
    private List<String> whiteMoves;
    private List<String> blackMoves;
    private Player gameWinner;

    /**
     * Constructs a new Game instance initializing the players, table, and state.
     * @param whitePlayer Player representing the white pieces
     * @param blackPlayer Player representing the black pieces
     */
    public Game(final Player whitePlayer, final Player blackPlayer) {
        this.stateGame = false;
        this.table = Table.getInstance(MAX_SIZE);
        this.turnManager = new TurnManager(whitePlayer, blackPlayer);
        this.whiteMoves = new ArrayList<>();
        this.blackMoves = new ArrayList<>();
    }
    /**
     * Retrieves the table associated with the game.
     *
     * @return The table object.
     */@SuppressFBWarnings(value = "EI_EXPOSE_REP",
      justification = "L'esposizione di table è gestita e considerata sicura nel contesto dell'uso")
    public Table getTable() {
        return table;
    }
    /**
     * @param move
     */
    // Metodo per aggiungere mosse alla lista
    public void addMove(final String move) {
        if (turnManager.getCurrentPlayer().getColor().equals("bianco")) {
            whiteMoves.add(move);
        } else {
            blackMoves.add(move);
        }
    }
    /**
     * Print moves of each player.
     */
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
        return currentPlayer;
    }

    /**
     * Retrieves the move manager for the game.
     *
     * @return The move manager.
     */
    public MoveManager getMoveManager() {
        return new MoveManager(table, turnManager, this); // Passa `this` al costruttore di MoveManager
    }


    /**
     * Calculates the winner of the game when one player forfeits.
     * This method determines the winner based on the count of pawns each player has at the moment of forfeit.
     * It considers the current player and their opponent's pawn counts to declare the winner.
     */
    public void calculateWinnerDueToForfeit() {
        Player currentPlayer = turnManager.getCurrentPlayer();
        Player opponent = turnManager.getOpponent();
        int currentPlayerCount = table.countPawns(currentPlayer.getColor());
        int opponentPlayerCount = table.countPawns(opponent.getColor());
        gameWinner = (currentPlayerCount > opponentPlayerCount) ? currentPlayer : opponent;
    }

    /**
     * Displays the final results of the game, including the name of
     * the winner and the count of remaining pawns for each player.
     * This method prints out the winner's name and pawn count, along with the pawn count of the opponent.
     */
    public void displayResults() {
        System.out.println("Il vincitore e': " + gameWinner.getName());
        System.out.println("Pedine rimaste - " + gameWinner.getName() + ": " + table.countPawns(gameWinner.getColor()));
        System.out.println("Pedine rimaste - " + turnManager.getOpponent().getName()
        + ": " + table.countPawns(turnManager.getOpponent().getColor()));
    }


    /**
     * Checks if the game board is full.
     *
     * @return true if the game board is full, false otherwise.
     */
    public boolean isBoardFull() {
    for (int i = 0; i < table.getSize(); i++) {
        for (int j = 0; j < table.getSize(); j++) {
            if (table.getPawnAt(i, j) == null || table.getPawnAt(i, j).getOwner().isEmpty()) {
                return false; // Se trova almeno una cella vuota, il tavoliere non è pieno
            }
        }
    }
    return true; // Nessuna cella vuota trovata, il tavoliere è pieno
}



    /**
     * Checks if the game has reached its end.
     * If the game board is full, the game will be ended.
     */
    public void checkForEndOfGame() {
        if (isBoardFull()) {
            endGame();
        }
    }
    /**
     * Ends the current game session and determines the winner based on the number of pawns each player has.
     * It prints the game results, including each player's pawn count,
     * and declares the winner. Also resets the game board
     * and changes the game state to indicate that the game is finished.
     */
    public void endGame() {
        int whitePawns = table.countPawns("bianco");
        int blackPawns = table.countPawns("nero");
        Player winner = whitePawns > blackPawns ? turnManager.getWhitePlayer() : turnManager.getBlackPlayer();
        System.out.println("Fine del gioco! Il vincitore e' " + winner.getName()
        + " con " + Math.max(whitePawns, blackPawns) + " pedine.");
        System.out.println("Bianco: " + whitePawns + " pedine. Nero: " + blackPawns + " pedine.");
        setStateGame(false); // Set the game state to false, ending the game
        table.resetMap();
    }

    /**
     * Checks if a victory condition is met in the game.
     * A victory is declared if the opponent has no remaining pawns. If a victory is detected,
     * the game state is updated, the board is reset, and the results are displayed to the users.
     */
    public void checkForVictory() {
        int opponentPawns = table.countPawns(turnManager.getOpponent().getColor());
        if (opponentPawns == 0) {
            Player winner = turnManager.getCurrentPlayer();
            displayResults(); // Displays the results of the victory
            setStateGame(false); // Ends the game
            table.resetMap(); // Resets the game board
            System.out.println("Il gioco è finito. Vincitore: " + winner.getName());
        }
    }

    /**
     * Checks if the current player can make a move.
     * @return true if the current player can make a move, false otherwise.
     */
    public boolean canPlayerMove() {
        Player currentPlayer = turnManager.getCurrentPlayer();
        for (int i = 0; i < table.getSize(); i++) {
            for (int j = 0; j < table.getSize(); j++) {
                if (table.getPawnAt(i, j) != null
                && table.getPawnAt(i, j).getOwner().equals(currentPlayer.getColor())
                    && isMovePossible(i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks if a move is possible for a pawn at the given position.
     * @param x the x-coordinate of the pawn.
     * @param y the y-coordinate of the pawn.
     * @return true if a move is possible, false otherwise.
     */
    private boolean isMovePossible(final int x, final int y) {
        // Assume a move is possible if there is at least one empty adjacent cell
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                int newX = x + dx;
                int newY = y + dy;
                if (newX >= 0 && newX < table.getSize() && newY >= 0 && newY < table.getSize()
                    && (table.getPawnAt(newX, newY) == null || table.getPawnAt(newX, newY).getOwner().isEmpty())) {
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * Handles the turn of the current player.
     * If the player cannot make a move, the turn is passed to the next player.
     * Otherwise, it allows the player to make a move.
     */
    public void handleTurn() {
        if (!canPlayerMove()) {
            System.out.println("Nessuna mossa disponibile per "
            + turnManager.getCurrentPlayer().getName() + ". Turno passato.");
            turnManager.nextTurn();
        } else {
            // Here you can implement the logic to allow the player to make a move
            System.out.println("È il turno di " + turnManager.getCurrentPlayer().getName() + ".");
        }
    }
}
