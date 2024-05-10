package it.uniba.app;




import javax.annotation.Syntax;

/**
 * Main class of the application.
 */
public final class App {

    


    /**
     * Get a greeting sentence.
     *
     * @return the "Hello World!" string.
     */
    public static String getGreeting() {
        return "Hello World!!";
    }

    public static void main(final String[] args) {
        System.out.println(getGreeting()); 
    }
}
