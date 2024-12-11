package com.mastermind.game;

import com.mastermind.ui.OutputHandler;

import static com.mastermind.Main.game;

public class PlayGame {

    int numOfTries = 10;
    static String userGuess;
    static int getTurn = 0;

    static public void playGame() {
        while (!game.getGameOver()) {
            Player currentPlayer = game.getPlayers().get(getTurn);
            if (!currentPlayer.isPlaying()) {
                ++getTurn;
                continue;
            } else if (currentPlayer.getAttempts() < 1) {
                currentPlayer.setIsPlaying(false);
                continue;
            }
            currentPlayer.startTime();
            userGuess = UserInput.guessInput(currentPlayer);
            System.out.println(currentPlayer);
            if (userGuess == null) {
                break;
            }

            getTurn++;
            currentPlayer.decreaseAttempts();
            currentPlayer.endTime();
        }
    }
}
