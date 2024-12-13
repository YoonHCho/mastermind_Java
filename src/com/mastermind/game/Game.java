package com.mastermind.game;

import com.mastermind.ui.OutputHandler;

import java.util.ArrayList;

public class Game {
    private final int gameLevel;
    private final String code;
    private final int numOfPlayers;
    private final ArrayList<Player> players;
    private long startTime;
    private boolean gameOver;

    public Game(int gameLevel, String code, int numOfPlayers) {
        this.gameLevel = gameLevel;
        this.code = code;
        this.numOfPlayers = numOfPlayers;
        this.players = new ArrayList<>();
        this.gameOver = false;
    }

    public int getGameLevel() {
        return gameLevel;
    }

    public String getCode() {
        return code;
    }

    public int getNumOfPlayers() {
        return this.numOfPlayers;
    }

    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    public boolean getGameOver() {
        return this.gameOver;
    }

    public void setGameOver(boolean b) {
        this.gameOver = b;
    }

    public void start() {
        this.startTime = System.currentTimeMillis();
        OutputHandler.generatingGame(this.numOfPlayers, this.gameLevel);
        UserInput.playerNameInput(this.numOfPlayers, this.gameLevel);
    }

    public void end() {
        long elapsedTime = System.currentTimeMillis() - this.startTime;
        Calculate.calcTime(elapsedTime);
    }

    public void addPlayers(Player player) {
        this.players.add(player);
    }

    public void editPlayerName(int index, String newName) {
        Player playerNameToEdit = players.get(index);
        String oldName = playerNameToEdit.getName();
        playerNameToEdit.setName(newName);
        OutputHandler.confirmNameChange(oldName, newName, index);
    }
}
