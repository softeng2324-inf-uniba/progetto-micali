package it.uniba.app.features;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

/**<Entity> class
 *
 * Enum representing different types of commands.
 */
public enum CommandType {

    /**
     * Command to start the game.
     */
    START(new Pattern[] {Pattern.compile("^\\/gioca$"),
        Pattern.compile("^\\/play$")}),

    /**
     * Command to exit the game.
     */
    EXIT(new Pattern[] {Pattern.compile("^\\/esci$"),
        Pattern.compile("^\\/exit$")}),

    /**
     * Command to get help.
     */
    HELP(new Pattern[] {Pattern.compile("^\\/aiuto$"),
        Pattern.compile("^\\/help$"),
        Pattern.compile("^\\--help$"),
        Pattern.compile("^\\-h$")}),

    /**
     * Command for an empty action.
     */
    EMPTY(new Pattern[] {Pattern.compile("^\\/vuoto$"),
        Pattern.compile("^\\/empty$")}),

    /**
     * Command to display the game table.
     */
    TABLE(new Pattern[] {Pattern.compile("^\\/tavoliere$"),
        Pattern.compile("^\\/table$")}),

    /**
     * Command to show available moves.
     */
    SHOW_MOVES(new Pattern[] {Pattern.compile("^\\/qualimosse$"),
        Pattern.compile("^\\/moves$")}),

    /**
     * Command to give up the game.
     */
    GIVE_UP(new Pattern[] {Pattern.compile("^\\/abbandona$"),
        Pattern.compile("^\\/giveup$")}),

    CAPTURE(new Pattern[] {Pattern.compile("^[a-g][1-7]-[a-g][1-7]$")}),


    OLD_MOVES(new Pattern[] {Pattern.compile("^\\/mosse$"),
        Pattern.compile("^\\/oldmoves$")}),

    BLOCKCELL(new Pattern[] {Pattern.compile("^\\/bloccaxn$"),
    Pattern.compile("^\\/blockxn$")});

    private final Set<Pattern> compiled;

    /**
     * Constructor for CommandType enum.
     * @param commcompiled an array of compiled patterns representing the command aliases
     * @throws IllegalArgumentException if the commcompiled array is empty
     */
    CommandType(final Pattern[] commcompiled) throws IllegalArgumentException {
        if (commcompiled.length == 0) {
            throw new IllegalArgumentException(
                    "compiled deve contenere almeno un elemento.");
        }
        this.compiled = new HashSet<>(Arrays.asList(commcompiled));
    }

    /**
     * Get the set of compiled patterns representing the command aliases.
     * @return a set of patterns
     */
    public HashSet<Pattern> getCommandPattern() {
        return new HashSet<>(this.compiled);
    }
}

