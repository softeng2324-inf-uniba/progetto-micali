package it.uniba.app.elements;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

class GameTest {

    private Game game;
    private Player whitePlayer;
    private Player blackPlayer;
    private static final int MAX_TABLE_SIZE = 7;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        whitePlayer = new Player("Player 1", "bianco");
        blackPlayer = new Player("Player 2", "nero");
        game = new Game(whitePlayer, blackPlayer);
        System.setOut(new PrintStream(outContent, true, StandardCharsets.UTF_8));
    }

    @Test
    @DisplayName("Test Inizializzazione Gioco")
    void testGameInitialization() {
        assertNotNull(game.getTable(), "La tavola dovrebbe essere inizializzata");
    }

    @Test
    @DisplayName("Test Inizializzazione Giocatore Corrente")
    void testCurrentPlayerInitialization() {
        assertNotNull(game.getCurrentPlayer(), "Il giocatore corrente dovrebbe essere inizializzato");
    }

    @Test
    @DisplayName("Test Stato di Gioco Iniziale")
    void testInitialGameState() {
        assertFalse(game.getStateGame(), "Lo stato del gioco dovrebbe essere inizialmente falso");
    }

    @Test
    @DisplayName("Test Stato di Gioco Impostato Correttamente")
    void testGameStateCanBeSet() {
        game.setStateGame(true);
        assertTrue(game.getStateGame(), "Lo stato del gioco dovrebbe essere vero dopo l'impostazione");
    }

    @Test
    @DisplayName("Test Mosse Aggiunte Correttamente")
    void testPrintMoves() {
        game.setStateGame(true);
        game.addMove("a1");
        game.addMove("a2");
        game.printMoves();  // Test della funzionalità di stampa indirettamente
        assertTrue(outContent.toString(StandardCharsets.UTF_8)
                   .contains("a1; a2;"), "L'output dovrebbe contenere le mosse");
    }

    @Test
    @DisplayName("Test Visualizzazione Risultati")
    void testDisplayResults() {
        game.setStateGame(true);
        game.getTable().setPawnAt(0, 0, new Pawn("bianco", '\u2659', "", 0, 0));
        game.getTable().setPawnAt(MAX_TABLE_SIZE - 1, MAX_TABLE_SIZE - 1,
            new Pawn("nero", '\u265F', "", MAX_TABLE_SIZE - 1, MAX_TABLE_SIZE - 1));
        game.calculateWinnerDueToForfeit();
        game.displayResults();
        String output = outContent.toString(StandardCharsets.UTF_8);
        System.out.println("Contenuto catturato: " + output);
        assertTrue(output.contains("Il vincitore e'"),
                "Dovrebbe visualizzare le informazioni sul vincitore");
    }
    @Test
    @DisplayName("Tavoliere Riempito Correttamente")
    void testBoardIsFullAfterFilling() {
        fillBoardWithPawns();
        assertTrue(game.isBoardFull(), "Il tavoliere dovrebbe essere pieno");
    }

    private void fillBoardWithPawns() {
        for (int i = 0; i < MAX_TABLE_SIZE; i++) {
            for (int j = 0; j < MAX_TABLE_SIZE; j++) {
                game.getTable().setPawnAt(i, j, new Pawn("bianco", '\u2659', "", i, j));
            }
        }
    }
}
