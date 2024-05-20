package it.uniba.app.elements;


public class Game {

    private boolean stateGame = false;

    private Table table = new Table(7);

    public Game() {
        stateGame = false;
    }

    public Table getTable() {
        return table;
    }   

    public boolean getStateGame() {
        return stateGame;
    }

    public void setStateGame(boolean stateGame) {
        this.stateGame = stateGame;
    }   
}