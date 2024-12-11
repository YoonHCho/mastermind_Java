package com.mastermind.game;

import com.mastermind.ui.OutputHandler;

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

    static public boolean validateCommand(Player player, String userInput) {
        return switch (userInput.trim().toLowerCase()) {
            case "history" -> {
                player.printHistory();
                yield true;
            }
            case "hint" -> {
                if (player.getHintIndex() < game.getGameLevel()) {
                    int index = player.getHintIndex();
                    char code = game.getCode().charAt(index);
                    System.out.println("inside hint ln 61");
                    OutputHandler.getHint(index, code);
                    player.decreaseHintsAllowed();
                } else {
                    OutputHandler.printResult("No more hints available.");
                }
                yield true;
            }
            case "time" -> {
                long elapsedTime = System.currentTimeMillis() - player.getStartTime();
                PlayTimeCalc.calcTime(elapsedTime);
                yield true;
            }
            default -> {
                yield false;
            }
        };
    }

    static public String validateCode(Player player, String userGuess) {
        if (userGuess.length() != game.getGameLevel() || Integer.parseInt(userGuess) < 0) {
            return "Must enter positive " + game.getGameLevel() + "-digit numbers";
        }

        if (userGuess.equals("1111")) {
            return null;
        }
        return "Need logic for validating code";
    }
}
