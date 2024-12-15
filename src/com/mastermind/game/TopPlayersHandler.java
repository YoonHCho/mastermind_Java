package com.mastermind.game;

import com.mastermind.ui.OutputHandler;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TopPlayersHandler {
    static public String filePath = "top5.txt";

    static public StringBuilder readWriteTopPlayers(ArrayList<String> curPlayers) {
        // if no solved players doesn't exist return. don't need to read and/or write
//        if (curPlayers.isEmpty()) return null;

        // need a place to store the playerScore in a listArray
        List<PlayerScore> topPlayers = new ArrayList<>();

        // if there are players that solved add the current game players to list
        if (!curPlayers.isEmpty()) {
            for (String player : curPlayers) {
                String[] playerArr = player.split(": ");
                topPlayers.add(new PlayerScore(playerArr[0], Integer.parseInt(playerArr[1])));
            }
        }

        // get the already existing players from file.
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.startsWith("===") && !line.isEmpty()) {
                    String[] toArray = line.split(": ");
                    String[] sepName = toArray[0].split(". ");
                    topPlayers.add(new PlayerScore(sepName[1], Integer.parseInt(toArray[1])));
                }
            }
            reader.close();
        } catch (IOException e) {
            OutputHandler.printResult("Error reading file: " + e.getMessage());
        }

        // sort by score
        topPlayers.sort((p1, p2) -> Integer.compare(p2.score, p1.score));
        int rank = 1;

        // write with updated top 5 players
        StringBuilder partOfEndMessage = new StringBuilder();
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            String header = "=== TOP 5 PLAYERS ===\n";
            partOfEndMessage.append(header);
            writer.write(header);
            for (PlayerScore player : topPlayers) {
                if (rank > 5) break;
                String playerInfo =
                        rank + ". " + player.name + ": " + player.score + "\n";
                partOfEndMessage.append(playerInfo);
                writer.write(playerInfo);
                rank++;
            }
            writer.close();
        } catch (IOException e) {
            OutputHandler.printResult("Error writing file" + e.getMessage());
        }

        if (partOfEndMessage.isEmpty()) {
            return null;
        }
        return partOfEndMessage;
    }
}
