package com.mastermind;

import com.mastermind.api.Api;
import com.mastermind.game.*;
import com.mastermind.ui.OutputHandler;

import java.util.Scanner;

public class Main {
    public static Game game;
    public static Scanner sc;

    public static void main(String[] args) {
        // while online
//        sc = new Scanner(System.in);
//        OutputHandler.welcomeMessage();
//        int gameLevel = UserInput.levelInput();
//        String gameCode = Api.getCode(gameLevel);
//        int maxPlayers = 4;
//        int numOfPlayers = UserInput.playerNumInput(maxPlayers);
//        game = new Game(gameLevel, gameCode, numOfPlayers);
//        game.start();


        // while offline
        sc = new Scanner(System.in);
        OutputHandler.welcomeMessage();
        int gameLevel = UserInput.levelInput();
        String gameCode = "4321";
        int maxPlayers = 4;
        int numOfPlayers = UserInput.playerNumInput(maxPlayers);
        game = new Game(gameLevel, gameCode, numOfPlayers);
        game.start();
        PlayGame.playGame();




        System.out.println("you selected game level: " + game.getGameLevel());
        System.out.println("the code to solve is: " + game.getCode());
        System.out.println("num of players: " + game.getNumOfPlayers());
        for (Player player : game.getPlayers()) {
            System.out.println(player);
        }
        game.end();
    }
}
