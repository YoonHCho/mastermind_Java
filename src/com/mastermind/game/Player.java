package com.mastermind.game;

import com.mastermind.ui.OutputHandler;

public class Player {
    private String name;
    private int score;
    private String history;
    private boolean isPlaying;
    private int hintsAllowed;
    private int attempts;
    private long startTime;
    private long endTime;
    private long timeElapsed;
    private int hintIndex;

    public Player(String name, int level) {
        this.name = name;
        this.isPlaying = true;
        this.hintsAllowed = level;
        this.attempts = 10;
        this.timeElapsed = 0;
        this.score = 0;
        this.hintIndex = 0;
    }

    public String getName() {
        return this.name;
    }

    public int getScore() {
        return this.score;
    }

    public void printHistory() {
        OutputHandler.printResult(this.history);
    }

    public int getAttempts() {
        return this.attempts;
    }

    public int getHintsAllowed() {
        return this.hintsAllowed;
    }

    public int getHintIndex() {
        return this.hintIndex++;
    }

    public long getStartTime() {
        return this.startTime;
    }

    public boolean isPlaying() {
        return this.isPlaying;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void decreaseHintsAllowed() {
        this.hintsAllowed--;
    }

    public void decreaseAttempts() {
        this.attempts--;
    }

    public void setIsPlaying(boolean b) {
        this.isPlaying = b;
    }

    public void startTime() {
        this.startTime = System.currentTimeMillis();
    }

    public void endTime() {
        this.timeElapsed += System.currentTimeMillis() - this.startTime;
    }

    public String toString() {
        return getName() + "(name), " + getHintsAllowed() + "(hintsAllowed), " + getHintIndex() + "(hintIndex)";
    }
}
