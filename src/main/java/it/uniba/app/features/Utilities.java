package it.uniba.app.features;

/**
 * Utility class containing constants used throughout the application.
 * This class is final to prevent instantiation and inheritance.
 */
public final class Utilities {

    // Prevent instantiation
    private Utilities() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    // ANSI color codes
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_CUSTOM_ORANGE_BACKGROUND = "\u001B[48;5;202m";
    public static final String ANSI_GREY_BACKGROUND = "\u001B[100m";
    public static final String ANSI_RESET = "\u001B[0m";  // Reset color to default after each cell use

    // Player positions
    public static final int PLAYER_POSITION_1 = 0;
    public static final int PLAYER_POSITION_2 = 1;
    public static final int PLAYER_POSITION_3 = 2;
    public static final int PLAYER_POSITION_4 = 3;  // Corrected from 4 to 3
    public static final int PLAYER_POSITION_5 = 4;  // Corrected from 5 to 4
    public static final int PLAYER_POSITION_6 = 5;  // Corrected from 6 to 5
    public static final int POSITIVE_TWO = 2;
    public static final int NEGATIVE_TWO = -2;
    public static final int DIMENSION = 7;


    // Maximum number of blocks that can be set in a game
    public static final int MAX_BLOCKS = 9;
}
