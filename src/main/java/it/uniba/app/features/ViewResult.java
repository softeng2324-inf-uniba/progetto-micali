package it.uniba.app.features;

public enum ViewResult {
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
    ViewResult(final String value, final ColorShell enumColor){
        this.msg = value;
        this.color = enumColor;
    }
    /** Getter dell'attributo msg.
     * @return msg.
     */
    public String getMsg() {
        return this.msg;
    }
    /** Getter dell'attributo color.
     * @return colore associato all'enumerativo.
     */
    public ColorShell getColor() {
        return this.color;
    }
    /** Metodo che restituisc il messaggio con il colore dell'enumerativo.
     * @return Messaggio con il colore dell'enumerativo.
     */
    public String getMessage() {
        return this.color.getValue() + this.msg + ColorShell.END_ESCAPE.getValue();
    }
}