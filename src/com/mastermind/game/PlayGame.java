package com.mastermind.game;

import com.mastermind.ui.OutputHandler;

import static com.mastermind.Main.game;

public class PlayGame {

    static String userGuess;
//    static int getTurn = 0;
    static int numOfFinishedPlayers = 0;

    static public void playGame() {
        int getTurn = 0;
        while (!game.getGameOver()) {
            Player currentPlayer = game.getPlayers().get(getTurn);
            if (!currentPlayer.isPlaying()) {
                getTurn = (getTurn + 1) % game.getNumOfPlayers();
                continue;
            }
//            else if (currentPlayer.getAttempts() < 1) {
//                currentPlayer.setIsPlaying(false);
//                getTurn = (getTurn + 1) % game.getNumOfPlayers();
//                continue;
//            }
            currentPlayer.startTime();
            userGuess = UserInput.guessInput(currentPlayer);
            String result = Validator.validateCode(currentPlayer, userGuess, game.getCode());

            getTurn = (getTurn + 1) % game.getNumOfPlayers();
            currentPlayer.decreaseAttempts();
            currentPlayer.endTime();

            if (result != null && result.equalsIgnoreCase("solved")) {
                currentPlayer.setIsPlaying(false);
                OutputHandler.printResult("Congratulations, " + currentPlayer.getName() + ". You solved the Mastermind Game.\nThe code to solve: " + game.getCode());
                Calculate.calcTime(currentPlayer, currentPlayer.getTimeElapsed());
                numOfFinishedPlayers++;
            }

            if (currentPlayer.getAttempts() < 1) {
                currentPlayer.setIsPlaying(false);
                currentPlayer.setScore(0);
                OutputHandler.printResult("Sorry " + currentPlayer.getName() + ", you've exhausted all your attempts. The game is over for you.");
                numOfFinishedPlayers++;
            }

            if (game.getNumOfPlayers() == numOfFinishedPlayers) {
                game.setGameOver(true);
            }
        }
    }
}
