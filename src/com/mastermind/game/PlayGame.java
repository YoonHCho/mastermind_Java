package com.mastermind.game;

import static com.mastermind.Main.game;
import static com.mastermind.Main.sc;

public class PlayGame {

    int numOfTries = 10;
    String userGuess;
    static int getTurn = 0;

    static public void playGame() {
        while (!game.getGameOver()) {
            Player currentPlayer = game.getPlayers().get(getTurn);
            System.out.println(currentPlayer.getName());
            String userInput = sc.nextLine();
            if (userInput.equalsIgnoreCase("end")) {
                break;
            }
        }
    }
}
