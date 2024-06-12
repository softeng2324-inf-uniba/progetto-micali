package it.uniba.app.elements;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Manages the moves within the game, including move validation,
 * execution, and conversion of opponent's pieces.
 */
public class MoveManager {
    private Table table;
    private TurnManager turnManager;
    private Game game;

     /**
     * Manages moves in the game.
     * <p>
     * This constructor initializes the MoveManager with references to the game table, turn manager, and game objects.
     * </p>
     * @param tableParam the table on which the game is played.
     * @param turnManagerParam the manager responsible for handling turns.
     * @param gameParam the main game object.
     */
    @SuppressFBWarnings(
        value = "EI_EXPOSE_REP",
        justification = "Table, TurnManager, and Game are controlled and their exposure "
                    + "is considered safe in this context"
    )
    public MoveManager(final Table tableParam, final TurnManager turnManagerParam, final Game gameParam) {
        this.table = tableParam;
        this.turnManager = turnManagerParam;
        this.game = gameParam; // Initialize `game` with the passed object
    }
    /**
     * Processes and executes a move if it is valid.
     *
     * @param move the move to make
     * @return true if the move was made, false if the move was invalid
     */
    public boolean makeMove(final Move move) {
        if (validateMove(move)) {
            executeMove(move);
            // Build a description of the move and add it to the game's move list
            String moveDescription = move.getStart().toString() + "-" + move.getEnd().toString();
            game.addMove(moveDescription);  // Assumes 'game' has an addMove method to record moves
            convertAdjacentEnemies(move.getEnd());
            game.checkForVictory();
            turnManager.nextTurn();  // Change the turn after a valid move
            game.checkForEndOfGame(); // Check if the game has ended after the move
            return true;
        }
        return false;
    }
    /**
     * Validates a move based on game rules.
     * @param move the move to validate
     * @return true if the move is valid, false otherwise
     */
    private boolean validateMove(final Move move) {
        int startX = move.getStart().getX();
        int startY = move.getStart().getY();
        int endX = move.getEnd().getX();
        int endY = move.getEnd().getY();
        Pawn originPawn = table.getPawnAt(startX, startY);
        Pawn targetPawn = table.getPawnAt(endX, endY);
        // Ensure the origin pawn exists and is valid
        if (originPawn == null || originPawn.getUnicodeCharacter() == ' '
            || originPawn.getOwner() == null || originPawn.getOwner().isEmpty()) {
            System.out.println("La posizione di partenza non contiene un pedone valido.");
            return false;
        }
        // Verify that the origin pawn belongs to the current player
        if (!originPawn.getOwner().equals(turnManager.getCurrentPlayer().getColor())) {
            System.out.println("La pedina nella posizione di partenza non appartiene al giocatore corrente.");
            return false;
        }
        // Verify that the destination position is empty
        if (targetPawn != null && targetPawn.getUnicodeCharacter() != ' ') {
            System.out.println("La posizione di destinazione non e' vuota.");
            return false;
        }
        // Check if the move is adjacent or a jump
        int dx = Math.abs(startX - endX);
        int dy = Math.abs(startY - endY);
        if ((dx == 1 && dy <= 1) || (dy == 1 && dx <= 1)) {
            // Adjacent move
            return true;
        } else if ((dx == 2 && dy <= 2) || (dy == 2 && dx <= 2)) {
            // Jump move
            return true;
        }
        System.out.println("Mossa non valida, né adiacente né un salto.");
        return false;
    }
    /**
     * Executes a move by moving the pawn from the start to the end coordinate.
     *
     * @param move the move to execute
     */
    private void executeMove(final Move move) {
        int startX = move.getStart().getX();
        int startY = move.getStart().getY();
        int endX = move.getEnd().getX();
        int endY = move.getEnd().getY();
        Pawn movingPawn = table.getPawnAt(startX, startY);
        // Check if the move is a jump
        if (Math.abs(startX - endX) > 1 || Math.abs(startY - endY) > 1) {
            // Jump move: move the pawn and clear the original position
            table.setPawnAt(endX, endY, new Pawn(movingPawn.getOwner(), movingPawn.getUnicodeCharacter(),
            movingPawn.getColor(), endX, endY));
            table.clearPawnAt(startX, startY);  // Remove the pawn from the starting position
        } else {
            // Normal move: duplicate the pawn at the destination position
            table.setPawnAt(endX, endY, new Pawn(movingPawn.getOwner(), movingPawn.getUnicodeCharacter(),
            movingPawn.getColor(), endX, endY));
        }
    }

    /**
     * Converts adjacent enemy pieces after a move.
     *
     * @param end the ending coordinate of the last move
     */
    private void convertAdjacentEnemies(final Coordinate end) {
        int[][] directions = {
            {-1, -1}, {-1, 0}, {-1, 1},
            {0, -1},         {0, 1},
            {1, -1}, {1, 0}, {1, 1}
        };
        String currentPlayerColor = turnManager.getCurrentPlayer().getColor();
        char currentPlayerChar = currentPlayerColor.equals("bianco") ? '\u26C2' : '\u26C0';
        for (int[] dir : directions) {
            int checkX = end.getX() + dir[0];
            int checkY = end.getY() + dir[1];
            if (isValidPosition(checkX, checkY)) {
                Pawn targetPawn = table.getPawnAt(checkX, checkY);
                if (targetPawn != null && targetPawn.getOwner().equals(turnManager.getOpponent().getColor())) {
                    // Convert the pawn
                    Pawn newPawn = new Pawn(currentPlayerColor, currentPlayerChar,
                    targetPawn.getColor(), checkX, checkY);
                    table.setPawnAt(checkX, checkY, newPawn);
                }
            }
        }
    }
    /**
     * Checks if a position is within the bounds of the game table.
     *
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @return true if the position is valid, false otherwise
     */
    private boolean isValidPosition(final int x, final int y) {
        return x >= 0 && x < table.getSize() && y >= 0 && y < table.getSize();
    }
}
