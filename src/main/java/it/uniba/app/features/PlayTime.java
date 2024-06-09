package it.uniba.app.features;

public class PlayTime {
    private long startTime;
    private long endTime;
    private boolean running;

    // Costruttore
    public PlayTime() {
        this.running = false;
    }

    // Metodo per avviare il timer
    public void start() {
        this.startTime = System.currentTimeMillis();
        this.running = true;
    }

    // Metodo per fermare il timer
    public void stop() {
        this.endTime = System.currentTimeMillis();
        this.running = false;
    }

     // Metodo per resettare il timer
    public void reset() {
        this.startTime = 0;
        this.endTime = 0;
        this.running = false;
    }

    // Metodo per ottenere il tempo trascorso in millisecondi
    public long getElapsedTime() {
        if (running) {
            return System.currentTimeMillis() - startTime;
        } else {
            return endTime - startTime;
        }
    }

    // Metodo per ottenere il tempo trascorso in secondi
    public long getElapsedTimeSeconds() {
        return getElapsedTime() / 1000;
    }

    // Metodo per ottenere il tempo trascorso in ore, minuti e secondi
    public String getElapsedTimeFormatted() {
        long elapsedTime = getElapsedTime();
        long hours = elapsedTime / 3600000;
        long minutes = (elapsedTime % 3600000) / 60000;
        long seconds = (elapsedTime % 60000) / 1000;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
