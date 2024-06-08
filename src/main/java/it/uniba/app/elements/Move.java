package it.uniba.app.elements;

/**
 * The Move class represents a move made by a player in the Ataxx game.
 */
public class Move {
    private Coordinate start;
    private Coordinate end;
    private Player player;

    /**
     * Constructs a Move with the specified start and end coordinates and the player who made the move.
     *
     * @param startCoordinate  The starting coordinate of the move.
     * @param endCoordinate    The ending coordinate of the move.
     * @param movePlayer The player who made the move.
     */
    public Move(final Coordinate startCoordinate, final Coordinate endCoordinate, final Player movePlayer) {
        this.start = startCoordinate;
        this.end = endCoordinate;
        this.player = new Player(movePlayer.getName(), movePlayer.getColor());
    }

    /**
     * Gets the starting coordinate of the move.
     *
     * @return The starting coordinate of the move.
     */
    public Coordinate getStart() {
        return start;
    }

    /**
     * Gets the ending coordinate of the move.
     *
     * @return The ending coordinate of the move.
     */
    public Coordinate getEnd() {
        return end;
    }

    /**
     * Gets the player who made the move.
     *
     * @return The player who made the move.
     */
    public Player getPlayer() {
        return new Player(player.getName(), player.getColor());
    }

    /**
     * Checks if the move is a jump.
     * Assumes that jumps are moves that cross two squares.
     *
     * @return True if the move is a jump, false otherwise.
     */
    public boolean isJump() {
        return (Math.abs(start.getX() - end.getX()) == 2 || Math.abs(start.getY() - end.getY()) == 2);
    }
}
