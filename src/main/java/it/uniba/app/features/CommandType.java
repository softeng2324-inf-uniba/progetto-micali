package it.uniba.app.features;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

    public enum CommandType {
        START(new Pattern[] {Pattern.compile("^\\/gioca$"),
            Pattern.compile("^\\/play$")}),
        EXIT(new Pattern[] {Pattern.compile("^\\/esci$"),
            Pattern.compile("^\\/exit$")}),
        HELP(new Pattern[] {Pattern.compile("^\\/aiuto$"),
            Pattern.compile("^\\/help$"),
            Pattern.compile("^\\--help$"),
            Pattern.compile("^\\-h$")}),   
        EMPTY(new Pattern[] {Pattern.compile("^\\/vuoto$"),
            Pattern.compile("^\\/empty$")}),
        TABLE(new Pattern[] {Pattern.compile("^\\/tavoliere$"),
            Pattern.compile("^\\/table$")}),
        SHOW_MOVES(new Pattern[] {Pattern.compile("^\\/qualimosse$"),
            Pattern.compile("^\\/moves$")}),
        GIVE_UP(new Pattern[] {Pattern.compile("^\\/abbandona$"),
            Pattern.compile("^\\/give up$")});   

        private final Set<Pattern> compiled;

        CommandType(final Pattern[] commcompiled) throws IllegalArgumentException {
        if (commcompiled.length == 0) {
            throw new IllegalArgumentException(
                    "compiled deve contenere almeno un elemento.");
        }
        this.compiled = new HashSet<>(Arrays.asList(commcompiled));
    }

     /**
     * @return Set di stringhe che rappresenta gli alias dei comandi.
     */
    public HashSet<Pattern> getCommandPattern() {
        return new HashSet<>(this.compiled);
    }
  }

