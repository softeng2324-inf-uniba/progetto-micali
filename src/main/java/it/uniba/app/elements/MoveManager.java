package it.uniba.app.elements;

/**
 * Classe che gestisce le mosse nel gioco.
 * Si occupa di validare e eseguire le mosse dei giocatori.
 */
public class MoveManager {
    private Table table;
    private TurnManager turnManager;
    private Game game; // Oggetto Game

    /**
     * Costruttore della classe MoveManager.
     * @param table Il tavolo di gioco.
     * @param turnManager Il gestore dei turni.
     * @param game L'oggetto gioco corrente.
     */
    public MoveManager(Table table, TurnManager turnManager, Game game) {
        this.table = table;
        this.turnManager = turnManager;
        this.game = game;
    }

    /**
     * Valida e esegue la mossa proposta dal giocatore.
     * @param move La mossa da validare ed eseguire.
     * @return true se la mossa è valida e viene eseguita, false altrimenti.
     */
    public boolean makeMove(Move move) {
        if (validateMove(move)) {
            executeMove(move);
            // Costruisci una descrizione della mossa e aggiungila alle mosse globali del gioco
            String moveDescription = move.getStart().toString() + "-" + move.getEnd().toString();
            //game.addMove(moveDescription);
            turnManager.nextTurn();  // Cambia il turno dopo una mossa valida
            System.out.println("Mossa valida eseguita da " + turnManager.getCurrentPlayer().getName());
            return true;
        }
        System.out.println("Mossa non valida: " + move);
        return false;
    }

    /**
     * Valida la mossa in base alla posizione di partenza e di arrivo e alle regole del gioco.
     * Permette solo mosse adiacenti per la duplicazione di pedine.
     * @param move La mossa da validare.
     * @return true se la mossa è adiacente e valida, false altrimenti.
     */
    private boolean validateMove(Move move) {
        int startX = move.getStart().getX();
        int startY = move.getStart().getY();
        int endX = move.getEnd().getX();
        int endY = move.getEnd().getY();
    
        Pawn originPawn = table.getPawnAt(startX, startY);
        Pawn targetPawn = table.getPawnAt(endX, endY);
    
        // Assicurati che la pedina di origine esista e sia valida
        if (originPawn == null || originPawn.getUnicodeCharacter() == ' ' || !originPawn.getOwner().equals(turnManager.getCurrentPlayer().getColor())) {
            System.out.println("Mossa non valida: La posizione di partenza non contiene una pedina valida del giocatore corrente.");
            return false;
        }
    
        // Verifica che la posizione di destinazione sia vuota
        if (targetPawn != null && targetPawn.getUnicodeCharacter() != ' ') {
            System.out.println("Mossa non valida: La posizione di destinazione non è vuota.");
            return false;
        }
    
        // Controlla se la mossa è adiacente
        int dx = Math.abs(startX - endX);
        int dy = Math.abs(startY - endY);
        if (dx <= 1 && dy <= 1 && !(dx == 0 && dy == 0)) {
            return true;
        }
    
        System.out.println("Mossa non valida: La mossa deve essere adiacente.");
        return false;
    }

    /**
     * Esegue la mossa duplicando la pedina nella posizione di destinazione.
     * @param move La mossa da eseguire.
     */
    private void executeMove(Move move) {
        int startX = move.getStart().getX();
        int startY = move.getStart().getY();
        int endX = move.getEnd().getX();
        int endY = move.getEnd().getY();
    
        Pawn movingPawn = table.getPawnAt(startX, startY);
    
        // Duplica la pedina nella posizione di destinazione
        table.setPawnAt(endX, endY, new Pawn(movingPawn.getOwner(), movingPawn.getUnicodeCharacter(), movingPawn.getColor(), endX, endY));
    }
}
