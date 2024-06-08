package it.uniba.app.exception;

/**
 * Exception thrown to indicate an invalid move in the game.
 */
public class InvalidMoveException extends Exception {
    /**
     * Constructs a new InvalidMoveException with the specified detail message.
     * @param message the detail message.
     */
    public InvalidMoveException(final String message) {
        super(message);
    }
}

// Make sure to add a newline here after the last closing brace
