package it.uniba.app.features;

/**
 * <p>The ColorShell enum represents different colors that can be used in a shell or console output.</p>
 */
public enum ColorShell {
    /** Black color escape sequence. */
    BLACK("\033[0;30m"),   // BLACK

    /** Red color escape sequence. */
    RED("\033[0;31m"),     // RED

    /** Green color escape sequence. */
    GREEN("\033[0;32m"),   // GREEN

    /** Yellow color escape sequence. */
    YELLOW("\033[0;33m"),  // YELLOW

    /** Blue color escape sequence. */
    BLUE("\033[0;34m"),    // BLUE

    /** Purple color escape sequence. */
    PURPLE("\033[0;35m"),  // PURPLE

    /** Cyan color escape sequence. */
    CYAN("\033[0;36m"),    // CYAN

    /** White color escape sequence. */
    WHITE("\033[0;37m"),   // WHITE

    /** Rose color escape sequence. */
    ROSE("\033[1;35m"),    // ROSE

    /** Light orange color escape sequence. */
    ORANGE_1("\033[38;2;255;165;0m"), // ORANGE LIGHT

    /** Mid light orange color escape sequence. */
    ORANGE_2("\033[38;2;255;140;0m"), // ORANGE MID LIGHT

    /** Low light orange color escape sequence. */
    ORANGE_3("\033[38;2;255;127;80m"), // ORANGE LOW LIGHT

    /** Low orange color escape sequence. */
    ORANGE_4("\033[38;2;255;69;0m"), // ORANGE LOW

    /** Ending sequence escape. */
    END_ESCAPE("\u001B[0m"); // ENDING SEQUENCE

    private final String color;

    /**
     * Constructs a ColorShell enum constant with the specified color escape sequence.
     * @param col The color escape sequence.
     */
    ColorShell(final String col) {
        this.color = col;
    }

    /**
     * Returns the color escape sequence associated with the ColorShell enum constant.
     * @return The color escape sequence.
     */
    public String getValue() {
        return this.color;
    }
}
