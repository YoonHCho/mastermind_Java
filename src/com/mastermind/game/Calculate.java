package com.mastermind.game;

import com.mastermind.ui.OutputHandler;
import static com.mastermind.Main.game;

public class Calculate {

    static public void calcTime(long elapsedTime) {
        int minutes = (int) (elapsedTime / 60000);
        int seconds = (int) (elapsedTime / 1000) % 60;
        String time = (minutes > 0 ? minutes + " minutes " + seconds + " seconds.": seconds + " seconds");
        OutputHandler.printResult("Total game time: " + time);
    }

    static public void calcTime(Player player, long elapsedTime) {
        int minutes = (int) (elapsedTime / 60000);
        int seconds = (int) (elapsedTime / 1000) % 60;
        String time = (minutes > 0 ? minutes + " minutes " + seconds + " seconds.": seconds + " seconds");
        OutputHandler.printResult(time);

        if (!player.isPlaying()) {
            calcScore(player);
        }
    }

    static public void calcScore(Player player) {
        int score = (player.getAttempts() * 12) + (game.getGameLevel() - player.getHintIndex()) * 15 + calcMilliScore(player.getTimeElapsed());
        OutputHandler.printResult("Your Score: " + score);
        player.setScore(score);
    }

    static public int calcMilliScore(long milliseconds) {
        if (milliseconds < 210000) {
            return 35;
        } else if (milliseconds < 450000) {
            return 20;
        } else {
            return 10;
        }
    }
}
