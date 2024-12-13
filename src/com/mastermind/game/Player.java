package com.mastermind.game;

import com.mastermind.ui.OutputHandler;

import java.util.ArrayList;

public class Player {
    private String name;
    private int score;
    private final ArrayList<String> history;
    private boolean isPlaying;
    private int hintsAllowed;
    private int attempts;
    private long startTime;
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
        this.history = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public int getScore() {
        return this.score;
    }

    public long getTimeElapsed() {
        return this.timeElapsed;
    }

    public void printHistory() {
        if (!history.isEmpty()) {
            OutputHandler.printHistory(this.history);
            return;
        }
        OutputHandler.printResult("You do not have any feedback.");
    }

    public int getAttempts() {
        return this.attempts;
    }

    public int getHintsAllowed() {
        return this.hintsAllowed;
    }

    public int getHintIndex() {
        return this.hintIndex;
    }

    public void incrementHintIndex() {
        this.hintIndex++;
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

    public void setScore(int score) {
        this.score = score;
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

    public void addHistory(String feedback) {
        this.history.add(feedback);
    }

    public void startTime() {
        this.startTime = System.currentTimeMillis();
    }

    public void endTime() {
        this.timeElapsed += System.currentTimeMillis() - this.startTime;
    }
}
