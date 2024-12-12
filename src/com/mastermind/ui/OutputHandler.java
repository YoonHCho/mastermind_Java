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

    static public void playGameBeginningMsg(String name, int tries, int hints) {
        System.out.println("\n**** In-Game Commands ****\nhistory - view the history of guesses and their feedback\nhint - " + (hints > 0 ? "show hint, you only have " + hints + " available" : "no hints available") + "\ntime - view the current player's play time\ngetCode - case sensitive, show answer\n**************************\n");
        System.out.println(name + ", you have " + tries + " attempts left");
        System.out.println("Please enter " + game.getGameLevel() + "-digit numbers:");
    }

    static public void getHint(int index, char code) {
        System.out.println("===== HINT =====");
        String result = "";

        switch (index + 1) {
            case 1 -> result += "First-digit hint:";
            case 2 -> result += "Second-digit hint:";
            case 3 -> result += "Third-digit hint:";
            case 4 -> result += "Fourth-digit hint:";
            case 5 -> result += "Fifth-digit hint:";
            case 6 -> result += "Sixth-digit hint:";
            case 7 -> result += "Seventh-digit hint:";
            case 8 -> result += "Eighth-digit hint:";
            default -> result += "Cannot find the " + (index + 1) + "-placed Number";
        }

        switch (code) {
            case '0' -> result += "\tI hold no identity. I am the empty vessel, ready to be filled with any data. What am I?";
            case '1' -> result += "\tI am the smallest whole number, yet mighty in my simplicity. What am I?";
            case '2' -> result += "\tI am the number of times you blink and I symbolize teamwork. What am I?";
            case '3' -> result += "\tI am the number of primary colors for your CSS color property. What am I?";
            case '4' -> result += "\tIn some Eastern cultures, I am feared because I resemble 'death'. What am I?";
            case '5' -> result += "\tI am the one and only prime number that has a sum of two consecutive prime numbers. What am I?";
            case '6' -> result += "\tI am the only number that is both the sum and product of three consecutive positive numbers. What am I?";
            case '7' -> result += "\tI am the number of continents in your world. What am I?";
            default -> result += "\tThere is no more hints";

        }
        System.out.println(result);
        System.out.println("================\n");
    }

    static public void printHistory(ArrayList<String> history) {
        OutputHandler.printResult("\n===== HISTORY =====");
        for (String feedback : history) {
            System.out.println(feedback);
        }
        OutputHandler.printResult("===================\n");
    }


}
