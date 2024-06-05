package it.uniba.app.elements;

/**
 * The MoveManager class manages moves in the Ataxx game.
 */
public class MoveManager {
    private Table table;
    private TurnManager turnManager;
    private Game game; // Game object

    /**
     * Constructs a MoveManager with the specified Table, TurnManager, and Game.
     *
     * @param table       The table of the game.
     * @param turnManager The turn manager of the game.
     * @param game        The game object.
     */
    public MoveManager(Table table, TurnManager turnManager, Game game) {
        this.table = table;
        this.turnManager = turnManager;
        this.game = game; // Initialize the game member with the passed object
    }

    /**
     * Makes a move in the game.
     *
     * @param move The move to be made.
     * @return True if the move is valid and executed successfully, false otherwise.
     */
    public boolean makeMove(Move move) {
        if (validateMove(move)) {
            executeMove(move);
            turnManager.nextTurn();  // Change turn after a valid move
            System.out.println("Valid move made by " + turnManager.getCurrentPlayer().getName());
            return true;
        }
        System.out.println("Invalid move: " + move);
        return false;
    }

    /**
     * Validates a move.
     *
     * @param move The move to be validated.
     * @return True if the move is valid, false otherwise.
     */
    private boolean validateMove(Move move) {
        int startX = move.getStart().getX();
        int startY = move.getStart().getY();
        int endX = move.getEnd().getX();
        int endY = move.getEnd().getY();

        Pawn originPawn = table.getPawnAt(startX, startY);
        Pawn targetPawn = table.getPawnAt(endX, endY);

        System.out.println("Start coordinates: (" + startX + ", " + startY + ")");
        System.out.println("End coordinates: (" + endX + ", " + endY + ")");
        System.out.println("Origin pawn: " + (originPawn != null ? originPawn.toString() : "none"));
        System.out.println("Target pawn: " + (targetPawn != null ? targetPawn.toString() : "none"));

        // Ensure the origin pawn exists and is valid
        if (originPawn == null || originPawn.getUnicodeCharacter() == ' ' || originPawn.getOwner() == null || originPawn.getOwner().isEmpty()) {
            System.out.println("The start position does not contain a valid pawn of yours.");
            return false;
        }

        // Check if the origin pawn belongs to the current player
        if (!originPawn.getOwner().equals(turnManager.getCurrentPlayer().getColor())) {
            System.out.println("The start pawn does not belong to the current player.");
            return false;
        }

        // Check if the destination position is empty
        if (targetPawn != null && targetPawn.getUnicodeCharacter() != ' ') {
            System.out.println("The destination position is not empty.");
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

        System.out.println("Invalid move, neither adjacent nor jump.");
        return false;
    }

    /**
     * Executes a valid move.
     *
     * @param move The move to be executed.
     */
    private void executeMove(Move move) {
        int startX = move.getStart().getX();
        int startY = move.getStart().getY();
        int endX = move.getEnd().getX();
        int endY = move.getEnd().getY();

        Pawn movingPawn = table.getPawnAt(startX, startY);

        // Check if the move is a jump
        if (Math.abs(startX - endX) > 1 || Math.abs(startY - endY) > 1) {
            // Jump move: move the pawn and clear the original position
            table.setPawnAt(endX, endY, new Pawn(movingPawn.getOwner(), movingPawn.getUnicodeCharacter(), movingPawn.getColor(), endX, endY));
            table.clearPawnAt(startX, startY);  // Remove the pawn from the start position
        } else {
            // Normal move: duplicate the pawn at the destination position
            table.setPawnAt(endX, endY, new Pawn(movingPawn.getOwner(), movingPawn.getUnicodeCharacter(), movingPawn.getColor(), endX, endY));
        }
    }
}
