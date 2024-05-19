package it.uniba.app.elements;

import it.uniba.app.features.ColorShell;
import it.uniba.app.elements.Table;

public class Game {

    private boolean stateGame = false;
    private boolean quitGame = false;   

    private Table table = new Table(7);

    public Game() {
        stateGame = false;
        quitGame = false;   

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