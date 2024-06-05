package it.uniba.app.elements;

/**
 * Class that parses moves for the game Ataxx.
 * <b>Class Type:</b> <Utility>
 * <b>Responsibilities:</b>
 * - Parse the move input and convert it into a Move object.
 * - Validate the format of the move and coordinates.
 */
public class MoveParser {

    /**
     * Parses the move input and creates a Move object.
     * @param moveInput The input string representing the move.
     * @param currentPlayer The player making the move.
     * @return The Move object representing the parsed move.
     * @throws IllegalArgumentException if the move input format is invalid.
     */
    public static Move parseMove(String moveInput, Player currentPlayer) throws IllegalArgumentException {
        String[] parts = moveInput.split("-");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid move format.");
        }
        Coordinate start = parseCoordinate(parts[0]);
        Coordinate end = parseCoordinate(parts[1]);
        return new Move(start, end, currentPlayer);
    }

    /**
     * Parses the coordinate input and creates a Coordinate object.
     * @param coordinate The input string representing the coordinate.
     * @return The Coordinate object representing the parsed coordinate.
     * @throws IllegalArgumentException if the coordinate format is invalid.
     */
    private static Coordinate parseCoordinate(String coordinate) throws IllegalArgumentException {
        if (coordinate.length() != 2 || coordinate.charAt(0) < 'a' || coordinate.charAt(0) > 'g' || 
            coordinate.charAt(1) < '1' || coordinate.charAt(1) > '7') {
            throw new IllegalArgumentException("Invalid coordinate format: " + coordinate);
        }
        int row = coordinate.charAt(1) - '1'; // convert '1' - '7' to 0 - 6
        int column = coordinate.charAt(0) - 'a'; // convert 'a' - 'g' to 0 - 6
        return new Coordinate(row, column);
    }
}
