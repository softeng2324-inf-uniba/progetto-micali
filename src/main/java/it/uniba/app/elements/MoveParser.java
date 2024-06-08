package it.uniba.app.elements;

/**
 * The MoveParser class parses move input strings into Move objects.
 */
public final class MoveParser {

    // Private constructor to prevent instantiation
    private MoveParser() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    /**
     * Parses the move input string into a Move object.
     *
     * @param moveInput     The move input string in the format "startCoordinate-endCoordinate".
     * @param currentPlayer The player making the move.
     * @return The Move object representing the parsed move.
     * @throws IllegalArgumentException If the move input string format is invalid.
     */
    public static Move parseMove(final String moveInput, final Player currentPlayer)
    throws IllegalArgumentException {
        String[] parts = moveInput.split("-");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid move format.");
        }
        Coordinate start = parseCoordinate(parts[0]);
        Coordinate end = parseCoordinate(parts[1]);
        return new Move(start, end, currentPlayer);
    }

    /**
     * Parses the coordinate string into a Coordinate object.
     *
     * @param coordinate The coordinate string in the format "columnRow" (e.g., "a1").
     * @return The Coordinate object representing the parsed coordinate.
     * @throws IllegalArgumentException If the coordinate string format is invalid.
     */
    private static Coordinate parseCoordinate(final String coordinate)
    throws IllegalArgumentException {
        if (coordinate.length() != 2
            || coordinate.charAt(0) < 'a'
            || coordinate.charAt(0) > 'g'
            || coordinate.charAt(1) < '1'
            || coordinate.charAt(1) > '7') {
            throw new IllegalArgumentException("Invalid coordinate format: " + coordinate);
        }
        int row = coordinate.charAt(1) - '1'; // Convert '1' - '7' to 0 - 6
        int column = coordinate.charAt(0) - 'a'; // Convert 'a' - 'g' to 0 - 6
        return new Coordinate(row, column);
    }
}
