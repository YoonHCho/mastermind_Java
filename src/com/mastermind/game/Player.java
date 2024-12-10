package com.mastermind.game;

public class Player {
    private String name;
    private int score;
    private String history;
    private boolean isPlaying;
    private int hintsAllowed;

    public Player(String name, int level) {
        this.name = name;
        this.isPlaying = true;
        this.hintsAllowed = level;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return getName();
    }
}
