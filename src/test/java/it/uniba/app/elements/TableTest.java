package it.uniba.app.elements;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;



class TableTest {
    private static final int TABLE_SIZE = 7;
    private static final int BLOCK_COORDINATE_X = 4;
    private static final int BLOCK_COORDINATE_Y = 4;
    private Table table;

    @BeforeEach
    void setUp() {
        table = new Table(TABLE_SIZE);
    }

    @Test
    @DisplayName("Test Dimensione Tavolo")
    void testTableSize() {
        assertEquals(TABLE_SIZE, table.getSize(), "La dimensione del tavolo dovrebbe essere " + TABLE_SIZE);
    }

    @Test
    @DisplayName("Test Blocco Cella")
    void testBlockCell() {
        boolean result = table.blockCell(BLOCK_COORDINATE_X, BLOCK_COORDINATE_Y);
        assertTrue(result, "La cella dovrebbe essere bloccata correttamente");
    }

    @Test
    @DisplayName("Test Tavolo Con Celle Bloccate")
    void testTableHasBlockedCells() {
        table.blockCell(BLOCK_COORDINATE_X, BLOCK_COORDINATE_Y);
        assertTrue(table.hasBlockedCells(), "Il tavolo dovrebbe avere celle bloccate");
    }
}
