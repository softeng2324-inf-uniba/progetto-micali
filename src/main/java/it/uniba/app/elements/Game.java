package it.uniba.app.elements;

import java.util.ArrayList;
import java.util.List;


/**
 * <Entity> class
 * The Game class represents a game instance.
 */
public class Game {

    private boolean stateGame = false;
    private static final int MAX_SIZE = 7;
    private final Table table;
    private final TurnManager turnManager; // Aggiungi TurnManager come membro della classe
    private List<String> whiteMoves;
    private List<String> blackMoves;    
    private Player winner; // Variabile di istanza per memorizzare il vincitore

    /**
     * Constructs a new Game instance.
     * The initial state of the game is set to false.
     */
    public Game(Player whitePlayer, Player blackPlayer) {
        this.stateGame = false;
        this.table = Table.getInstance(MAX_SIZE); // Usa il singleton per ottenere l'istanza di Table
        this.turnManager = new TurnManager(whitePlayer, blackPlayer); // Inizializza TurnManager
        this.whiteMoves = new ArrayList<>();
        this.blackMoves = new ArrayList<>();
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
        // Implementation of determining the winner when a player forfeits
        // This should depend on which player is current and count the pawns of the opponent
        Player currentPlayer = turnManager.getCurrentPlayer();
        Player opponent = turnManager.getOpponent();
        int currentPlayerCount = table.countPawns(currentPlayer.getColor());
        int opponentPlayerCount = table.countPawns(opponent.getColor());
        
        // Determine the winner based on the pawn counts
        winner = (currentPlayerCount > opponentPlayerCount) ? currentPlayer : opponent;
    }

    /**
     * Displays the final results of the game, including the name of the winner and the count of remaining pawns for each player.
     * This method prints out the winner's name and pawn count, along with the pawn count of the opponent.
     */
    public void displayResults() {
        // Displays the final result of the game, including the winner and the pawn count
        System.out.println("Il vincitore e': " + winner.getName());
        System.out.println("Pedine rimaste - " + winner.getName() + ": " + table.countPawns(winner.getColor()));
        System.out.println("Pedine rimaste - " + turnManager.getOpponent().getName() + ": " + table.countPawns(turnManager.getOpponent().getColor()));
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
     * It prints the game results, including each player's pawn count, and declares the winner. Also resets the game board
     * and changes the game state to indicate that the game is finished.
     */
    public void endGame() {
        int whitePawns = table.countPawns("bianco");
        int blackPawns = table.countPawns("nero");
        Player winner = whitePawns > blackPawns ? turnManager.getWhitePlayer() : turnManager.getBlackPlayer();
        System.out.println("Fine del gioco! Il vincitore e' " + winner.getName() + " con " + Math.max(whitePawns, blackPawns) + " pedine.");
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
}