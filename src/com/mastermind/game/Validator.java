package com.mastermind.game;

import com.mastermind.ui.OutputHandler;

import java.util.HashMap;

import static com.mastermind.Main.game;

public class Validator {
    public Validator() {}

    static public String validateLevel(int level) {
        if (level < 4 || level > 8) {
            return "Your input " + level + " is not in range.";
        }
        return null;
    }

    static public String validateNumOfPlayers(int numOfPlayers, int maxPlayers) {
        if (numOfPlayers > maxPlayers) {
            return "Maximum players are " + maxPlayers;
        } else if (numOfPlayers < 1) {
            return "Minimum player is 1";
        }
        return null;
    }

    static public String validatePlayerName(String name) {
        if (name.isBlank()) {
            return "Player name cannot be blank/empty";
        }
        return null;
    }

    static public String validateNameChange(String response) {
        if (response.equalsIgnoreCase("confirm")) {
            return null;
        } else if (response.equalsIgnoreCase("edit")) {
            UserInput.editNameInput();
            return "Edit completed";
        } else {
            return "Please enter confirm or edit.";
        }
    }

    static public String validatePlayerNum(int indexNum) {
        if (indexNum >= 0 && game.getNumOfPlayers() > indexNum) {
            return null;
        }
        return "There is no Player " + (indexNum + 1);
    }
    
    static public boolean validateCommand(String userInput) {
        return userInput.equalsIgnoreCase("history") ||
                userInput.equalsIgnoreCase("hint") ||
                userInput.equalsIgnoreCase("time") ||
                userInput.equals("getCode");
    }

    static public void handleCommand(Player player, String action) {
        if (action.equals("getCode")) {
            OutputHandler.printResult("=== ANSWER: " + game.getCode() + " ===");
            return;
        }
        switch (action.toLowerCase()) {
            case "history" -> player.printHistory();
            case "hint" -> {
                int index = player.getHintIndex();
                if (index < game.getGameLevel()) {
                    char code = game.getCode().charAt(index);
                    OutputHandler.getHint(index, code);
                    player.decreaseHintsAllowed();
                    player.incrementHintIndex();
                } else {
                    OutputHandler.printResult("No more hints available.");
                }
            }
            case "time" -> {
                long elapsedTime = player.getTimeElapsed() + System.currentTimeMillis() - player.getStartTime();
                Calculate.calcTime(player, elapsedTime);
            }
        }
    }

    static public boolean validateGuess(String userInput) {
        if (userInput.isBlank() || Integer.parseInt(userInput) < 0 || userInput.length() != game.getGameLevel()) {
            return false;
        }

        try {
            Integer.parseInt(userInput);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    static public String validateCode(Player player, String userGuess, String code) {
        int correctPlaces = 0;
        int correctNumbers = 0;
        String result;
        HashMap<Character, Integer> records = new HashMap<>();

        for (int i = 0; i < code.length(); i++) {
            char codeChar = code.charAt(i);
            char userChar = userGuess.charAt(i);

            if (codeChar == userChar) {
                correctPlaces++;
            }

            if (records.containsKey(codeChar)) {
                records.put(codeChar, records.get(codeChar) + 1);
            } else {
                records.put(codeChar, 1);
            }
        }

        for (int i = 0; i < userGuess.length(); i++) {
            char userChar = userGuess.charAt(i);

            if (records.containsKey(userChar) && records.get(userChar) > 0) {
                records.put(userChar, records.get(userChar) - 1);
                correctNumbers++;
            }
        }

        result = correctPlaces + (correctPlaces > 1 ? " correct placements AND " : " correct placement AND ") + correctNumbers + (correctNumbers > 1 ? " correct numbers" : " correct number");
        OutputHandler.printResult(result);
        player.addHistory(userGuess + ": " + result);

        if (correctPlaces == game.getGameLevel() && correctNumbers == game.getGameLevel()) {
            return "solved";
        }
        return null;
    }
}
