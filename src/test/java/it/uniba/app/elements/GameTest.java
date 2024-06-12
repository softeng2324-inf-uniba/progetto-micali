package it.uniba.app.elements;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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
        System.setOut(new PrintStream(outContent)); // Set up System.out to capture print statements.
    }

    @Test
    @DisplayName("Test Game Initialization")
    void testGameInitialization() {
        assertNotNull(game.getTable(), "Table should be initialized");
    }

    @Test
    @DisplayName("Test Current Player Initialization")
    void testCurrentPlayerInitialization() {
        assertNotNull(game.getCurrentPlayer(), "Current player should be initialized");
    }

    @Test
    @DisplayName("Test Initial Game State Is False")
    void testInitialGameState() {
        assertFalse(game.getStateGame(), "Game state should be false initially");
    }

    @Test
    @DisplayName("Test Game State Can Be Set")
    void testGameStateCanBeSet() {
        game.setStateGame(true);
        assertTrue(game.getStateGame(), "Game state should be true after setting it");
    }

    @Test
    @DisplayName("Test Print Moves")
    void testPrintMoves() {
        game.setStateGame(true);
        game.addMove("a1");
        game.addMove("a2");
        game.printMoves();  // Testing print functionality indirectly
        assertTrue(outContent.toString().contains("a1; a2;"), "Output should contain moves");
    }

    @Test
    @DisplayName("Test Display Results")
    void testDisplayResults() {
        game.setStateGame(true);
        game.getTable().setPawnAt(0, 0, new Pawn("bianco", '\u2659', "", 0, 0));
        game.getTable().setPawnAt(MAX_TABLE_SIZE - 1, MAX_TABLE_SIZE - 1,
            new Pawn("nero", '\u265F', "", MAX_TABLE_SIZE - 1, MAX_TABLE_SIZE - 1));
        game.calculateWinnerDueToForfeit();
        game.displayResults();
        assertTrue(outContent.toString().contains("Il vincitore e'"), "Should display winner info");
    }

    @Test
    @DisplayName("Test Board Is Full After Filling")
    void testBoardIsFullAfterFilling() {
        fillBoardWithPawns();
        assertTrue(game.isBoardFull(), "Board should be full after filling all positions");
    }

    private void fillBoardWithPawns() {
        for (int i = 0; i < MAX_TABLE_SIZE; i++) {
            for (int j = 0; j < MAX_TABLE_SIZE; j++) {
                game.getTable().setPawnAt(i, j, new Pawn("bianco", '\u2659', "", i, j));
            }
        }
    }
}
