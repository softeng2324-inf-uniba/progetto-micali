package it.uniba.app.elements;

/**
 * Classe responsabile della gestione delle mosse nel gioco Ataxx.
 */
public class MoveManager {
    private Table table;
    private TurnManager turnManager;
    private Game game; // Oggetto Game

    /**
     * Costruttore della classe MoveManager.
     *
     * @param table       la tabella di gioco
     * @param turnManager il gestore dei turni
     * @param game        l'oggetto Game
     */
    public MoveManager(Table table, TurnManager turnManager, Game game) {
        this.table = table;
        this.turnManager = turnManager;
        this.game = game; // Inizializza il membro `game` con l'oggetto passato
    }

    /**
     * Esegue una mossa se valida.
     *
     * @param move la mossa da eseguire
     * @return true se la mossa è valida e viene eseguita, false altrimenti
     */
    public boolean makeMove(Move move) {
        if (validateMove(move)) {
            executeMove(move);
    
            // Costruisci una descrizione della mossa e aggiungila alle mosse globali del gioco
            String moveDescription = move.getStart().toString() + "-" + move.getEnd().toString();
            game.addMove(moveDescription);  // Assume che 'game' abbia un metodo addMove per registrare le mosse
    
            convertAdjacentEnemies(move.getEnd());
            turnManager.nextTurn();  // Cambia il turno dopo una mossa valida
            System.out.println("Mossa valida eseguita da " + turnManager.getCurrentPlayer().getName());
            return true;
        }
        System.out.println("Mossa non valida: " + move);
        return false;
    }
    
    /**
     * Valida una mossa.
     *
     * @param move la mossa da validare
     * @return true se la mossa è valida, false altrimenti
     */
    private boolean validateMove(Move move) {
        int startX = move.getStart().getX();
        int startY = move.getStart().getY();
        int endX = move.getEnd().getX();
        int endY = move.getEnd().getY();
    
        Pawn originPawn = table.getPawnAt(startX, startY);
        Pawn targetPawn = table.getPawnAt(endX, endY);
    
        System.out.println("Coordinate di partenza: (" + startX + ", " + startY + ")");
        System.out.println("Coordinate di arrivo: (" + endX + ", " + endY + ")");
        System.out.println("Pedina di origine: " + (originPawn != null ? originPawn.toString() : "nulla"));
        System.out.println("Pedina di destinazione: " + (targetPawn != null ? targetPawn.toString() : "nulla"));
    
        // Assicurati che la pedina di origine esista e sia valida
        if (originPawn == null || originPawn.getUnicodeCharacter() == ' ' || originPawn.getOwner() == null || originPawn.getOwner().isEmpty()) {
            System.out.println("La posizione di partenza non contiene una tua pedina valida.");
            return false;
        }
    
        // Verifica che la pedina di origine appartenga al giocatore corrente
        if (!originPawn.getOwner().equals(turnManager.getCurrentPlayer().getColor())) {
            System.out.println("La pedina di partenza non appartiene al giocatore corrente.");
            return false;
        }
    
        // Verifica che la posizione di destinazione sia vuota
        if (targetPawn != null && targetPawn.getUnicodeCharacter() != ' ') {
            System.out.println("La posizione di destinazione non è vuota.");
            return false;
        }
    
        // Controlla se la mossa è adiacente o un salto
        int dx = Math.abs(startX - endX);
        int dy = Math.abs(startY - endY);
        if ((dx == 1 && dy <= 1) || (dy == 1 && dx <= 1)) {
            // Mossa adiacente
            return true;
        } else if ((dx == 2 && dy <= 2) || (dy == 2 && dx <= 2)) {
            // Mossa di salto
            return true;
        }
    
        System.out.println("Mossa non valida, non adiacente né di salto.");
        return false;
    }
    
    /**
     * Esegue una mossa.
     *
     * @param move la mossa da eseguire
     */
    private void executeMove(Move move) {
        int startX = move.getStart().getX();
        int startY = move.getStart().getY();
        int endX = move.getEnd().getX();
        int endY = move.getEnd().getY();
    
        Pawn movingPawn = table.getPawnAt(startX, startY);
    
        // Verifica se la mossa è un salto
        if (Math.abs(startX - endX) > 1 || Math.abs(startY - endY) > 1) {
            // Mossa di salto: sposta la pedina e pulisci la posizione originale
            table.setPawnAt(endX, endY, new Pawn(movingPawn.getOwner(), movingPawn.getUnicodeCharacter(), movingPawn.getColor(), endX, endY));
            table.clearPawnAt(startX, startY);  // Rimuove la pedina dalla posizione di partenza
        } else {
            // Mossa normale: duplica la pedina nella posizione di destinazione
            table.setPawnAt(endX, endY, new Pawn(movingPawn.getOwner(), movingPawn.getUnicodeCharacter(), movingPawn.getColor(), endX, endY));
        }
    }
}
