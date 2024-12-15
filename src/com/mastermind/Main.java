package com.mastermind;

import com.mastermind.api.Api;
import com.mastermind.game.*;
import com.mastermind.ui.OutputHandler;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static Game game;
    public static Scanner sc;

    public static void main(String[] args) {
        sc = new Scanner(System.in);
        OutputHandler.welcomeMessage();
        int gameLevel = UserInput.levelInput();
        String gameCode = Api.getCode(gameLevel);
        int maxPlayers = 4;
        int numOfPlayers = UserInput.playerNumInput(maxPlayers);
        game = new Game(gameLevel, gameCode, numOfPlayers);
        game.start();
        ArrayList<String> result = PlayGame.playGame();
        game.end();
        Calculate.calcGameResult(result);

        // Read and Write to txt file
        TopPlayersHandler.readWriteTopPlayers(result);
        OutputHandler.endMsg();
    }
}
