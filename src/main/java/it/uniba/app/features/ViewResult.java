package it.uniba.app.features;

/**
 * <p>Enum representing different view results.</p>
 */
public enum ViewResult {
    /** Welcome message with red color. */
    WELCOME("\n"
    + "   _____    __                           \n"
    + "  /  _  \\ _/  |_ _____   ___  ______  ___\n"
    + " /  /_\\  \\\\  __\\\\__  \\  \\  \\/  /\\  \\/  /\n"
    + "/    |    \\|  |   / __ \\_ >    <  >    < \n"
    + "\\____|__  /|__|  (____  //__/\\_ \\/__/\\_ \\\n"
    + "        \\/            \\/       \\/      \\/\n"
    + "\n", ColorShell.RED);

    private final String msg;
    private final ColorShell color;

    /**
     * Constructs a ViewResult enum with the given message and color.
     * @param value the message associated with the enum
     * @param enumColor the color associated with the enum
     */
    ViewResult(final String value, final ColorShell enumColor) {
        this.msg = value;
        this.color = enumColor;
    }

    /**
     * Gets the message associated with the enum.
     * @return the message
     */
    public String getMsg() {
        return this.msg;
    }

    /**
     * Gets the color associated with the enum.
     * @return the color
     */
    public ColorShell getColor() {
        return this.color;
    }

    /**
     * Gets the message with the color of the enum.
     * @return the message with the color
     */
    public String getMessage() {
        return this.color.getValue() + this.msg + ColorShell.END_ESCAPE.getValue();
    }
}
