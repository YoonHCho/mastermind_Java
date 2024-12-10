package com.mastermind.ui;

import com.mastermind.game.Player;
import com.mastermind.game.UserInput;

import java.util.ArrayList;

import static com.mastermind.Main.game;

public class OutputHandler {

    public OutputHandler() {}

    static public void welcomeMessage() {
        System.out.println("""
                **************************
                Welcome to Mastermind Game
                     by Yoon Hwan Cho
                **************************
                """);
    }

    static public void selectLevel() {
        System.out.print("Please select a difficulty level (4 for Normal to 8 for Hardest). Enter a whole number: ");
    }

    static public void printResult(String result) {
        System.out.println(result);
    }

    static public void numOfPlayers(int num) {
        System.out.print("How many players (min: 1, max: " + num + "): ");
    }

    static public void generatingGame(int numOfPlayers, int level) {
        System.out.println("Generating game for " + numOfPlayers + (numOfPlayers > 1 ? " players" : " player"));
        System.out.println("Game Level: " + level + "\n");
    }

    static public void mainPlayerNameMsg(int num) {
        System.out.print("Please input name for Player " + num + ": ");
    }

    static public void confirmNames() {
        ArrayList<Player> players = game.getPlayers();
        int playerNumber = 1;
        System.out.println("\n ** Player(s) **");
        for (Player player : players) {
            System.out.println("Player " + playerNumber + ": " + player.getName());
            playerNumber++;
        }
        System.out.print("""
                Type 'confirm' to start playing or
                Type 'edit' to edit name(s):
                """);
    }

    static public void playerNumMsg() {
        System.out.print("Please input the player number of the name you want to edit: ");
    }

    static public void newNameMsg() {
        System.out.print("Please enter a new name: ");
    }

    static public void confirmNameChange(String oldName, String newName, int index) {
        System.out.println("Player " + (index + 1) + " name changed from " + oldName + " to " + newName);
    }




}
