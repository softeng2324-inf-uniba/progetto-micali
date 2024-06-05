package it.uniba.app.exception;


public class InvalidMoveException extends Exception {
    /**
     * Costruttore della classe InvalidMoveException.
     * @param message
     */
    public InvalidMoveException(final String message) {
        super(message);
    }
}