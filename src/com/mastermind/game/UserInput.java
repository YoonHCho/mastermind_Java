package com.mastermind.game;

import com.mastermind.ui.OutputHandler;

import static com.mastermind.Main.game;
import static com.mastermind.Main.sc;

public class UserInput {

    static public int levelInput() {
        while(true) {
            OutputHandler.selectLevel();
            try {
                int level = Integer.parseInt(sc.nextLine());
                String result = Validator.validateLevel(level);
                if (result == null) {
                    return level;
                }
                OutputHandler.printResult(result);
            } catch (NumberFormatException e) {
                OutputHandler.printResult("Need a whole number");
            }
        }
    }

    static public int playerNumInput(int maxPlayers) {
        while (true) {
            OutputHandler.numOfPlayers(maxPlayers);
            try {
                int userInput = Integer.parseInt(sc.nextLine());
                String result = Validator.validateNumOfPlayers(userInput, maxPlayers);
                if (result == null) {
                    return userInput;
                }
                OutputHandler.printResult(result);
            } catch (NumberFormatException e) {
                OutputHandler.printResult("Please input a whole number");
            }
        }
    }

    static public void playerNameInput(int numOfPlayers, int level) {
        int playersAdded = 0;

        while (playersAdded != numOfPlayers) {
            OutputHandler.mainPlayerNameMsg(playersAdded + 1);
            String name = sc.nextLine();
            String result = Validator.validatePlayerName(name);
            if (result == null) {
                Player newPlayer = new Player(name, level);
                game.addPlayers(newPlayer);
                playersAdded++;
            }
        }

        while (true) {
            OutputHandler.confirmNames();
            String userInput = sc.nextLine();
            String result = Validator.validateNameChange(userInput);
            if (result == null) {
                break;
            }
            OutputHandler.printResult(result);
        }
    }

    static public void editNameInput() {
        int playerIndex;
        if (game.getNumOfPlayers() == 1) {
            playerIndex = 0;
        } else {
            while (true) {
                OutputHandler.playerNumMsg();
                try {
                    playerIndex = Integer.parseInt(sc.nextLine()) - 1;
                    String result = Validator.validatePlayerNum(playerIndex);
                    if (result == null) {
                        break;
                    }
                    OutputHandler.printResult(result);
                } catch (NumberFormatException e) {
                    OutputHandler.printResult("Enter a whole number.");
                }

            }
        }

        String newName;
        while (true) {
            OutputHandler.newNameMsg();
            newName = sc.nextLine();
            String result = Validator.validatePlayerName(newName);
            if (result == null) {
                break;
            }
            OutputHandler.printResult(result);
        }
        game.editPlayerName(playerIndex, newName);
    }

    static public String guessInput(Player player) {
        while (true) {
            OutputHandler.playGameBeginningMsg(player.getName(), player.getAttempts(), player.getHintsAllowed());
            try {
                String userInput = sc.nextLine().trim();

                if (Validator.validateCommand(userInput)) {
                    Validator.handleCommand(player, userInput);
                    continue;
                }

                if (Validator.validateGuess(userInput)) {
                    return userInput;
                }
                throw new NumberFormatException();
            } catch (NumberFormatException e) {
                OutputHandler.printResult("Need a " + game.getGameLevel() + "-digit numbers");
            }
        }
    }
}
