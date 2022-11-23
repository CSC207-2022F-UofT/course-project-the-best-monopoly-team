package Persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TextFileTranslator implements DataAccess, LoadCards {
    // each subarray holds the instance attributes of a Player instance

    private File file;

    public TextFileTranslator(File file) {
        this.file = file;
    }

    public File[] checkSaves(String folderPath){
        // checkSaves searches the given folder and returns an array of file names
        // the user selects the save file from the list of file names
        // loadGame takes in the filePath of the selected file name
        // and returns a String array of Player and Board data
        // the GameCreationInteractor will handle creating the class instances
        File folder = new File(folderPath);
        return folder.listFiles();
    }

    @Override
    public ArrayList<ArrayList<String[]>> loadGame() throws FileNotFoundException {
        // loadGame reads the given filePath and returns an ArrayList of String arrays
        // each new line on in the txt file given by filePath contains the instance attributes of a Player instance
        // the Board instance attributes are separated from the Player instance attributes by a header "Board"
        // the Board instance attributes are stored in the last element of the ArrayList

        ArrayList<ArrayList<String[]>> gameInfo = new ArrayList<>();
        ArrayList<String[]> players = new ArrayList<>();
        ArrayList<String[]> playerPositions = new ArrayList<>();
        ArrayList<String[]> savedTree = new ArrayList<>();
        ArrayList<String[]> treeOptions = new ArrayList<>();
        boolean player = false;
        boolean board = false;
        boolean tree = false;
        boolean options = false;

        File gameData = new File(file.getPath());
        Scanner scan = new Scanner(gameData);

        while (scan.hasNextLine()) {
            String data = scan.nextLine();

            switch (data.trim()) {
                case "playerStart":
                    player = true;
                case "playerEnd":
                    player = false;
                    break;
                case "positionStart":
                    board = true;
                    break;
                case "positionEnd":
                    board = false;
                    break;
                case "treeStart":
                    tree = true;
                    break;
                case "treeEnd":
                    tree = false;
                    break;
                case "optionStart":
                    options = true;
                    break;
                case "optionEnd":
                    options = false;
                    break;
            }

            if (player){
                String[] playerAttributes = data.trim().split(","); // denotes either a Player instance or a Property instance owned by a Player
                players.add(playerAttributes);

            } else if (board) {
                String[] position = data.trim().split(","); // denotes a key-value pair representing a Player name and their position
                playerPositions.add(position);

            } else if (tree) {
                String[] treeData = data.trim().split(",");
                savedTree.add(treeData);
            } else if (options) {
                String[] selectedOptions = data.trim().split(",");
                treeOptions.add(selectedOptions);
            }
        }
        gameInfo.add(players);
        gameInfo.add(playerPositions);
        gameInfo.add(savedTree);
        gameInfo.add(treeOptions);

        return gameInfo;
    }

    @Override
    public boolean saveGame(String[][] playerData, String[][] boardData,
                            String[][] treeData, String[][] optionData) throws IOException {
        File saveFile = file;

        // each subarray of playerData represents a Player instance or Property instance
        // have playerData ordered such that it follows the format:
        // [[Player 1], [Property 1 (owned by Player 1], [Property 2 (owned by Player 1)], ...
        //  [Player 2], [Property 1 (owned by Player 2], [Property 2 (owned by Player 2)], ....
        //  and so on. ]

        // for Player instances:
        // index [0] is String name, [1] is int money, [2] is String booleanInJail ("true" or "false)
        // [3] is int jailCards, [4] is int position

        // for owned Property instances:
        // index [0] is String name, [1] is String colour, [2] is int cost, [3] is int houseCost,
        // [4] is int rent, [5] is int rent1H, [6] is int rent2H, [7] is int rent3H, [8] is rent4H,
        // [9] is rentHotel, [10] is String playerOwnerName, [11] is int mortgageValue,
        // [12] is int houses, [13] is String booleanMortaged ("true" or "false")

        // for unowned Property instances:
        // index [10] is int mortgageValue, [11] is int houses,
        // String playerOwnerName and booleanMortaged are not stored by default

        // each subarray of boardData represents a key-value pair between a Player and their position
        // index[0] of the subarray is String playerName, [1] is int position

        if (saveFile.createNewFile()){
            FileWriter writer = new FileWriter(file.getPath());

            writer.write("playerStart\n");
            // loop through playerData and save each Player instance as a line
            for (String[] playerDatum : playerData) {
                StringBuilder newLine = new StringBuilder();
                // each line is either a Player Instance attributes, or a Property owned by Player
                for (String s : playerDatum) {
                    newLine.append(s).append(",");
                }
                newLine.deleteCharAt(newLine.length() - 1);
                writer.write(String.valueOf(newLine) + "\n");
            }
            writer.write("playerEnd\n");
            writer.write("positionStart\n");

            // loop through Board instance and save player-position key value pair as a line
            for (String[] boardDatum : boardData) {
                StringBuilder newLine = new StringBuilder();
                for (String s : boardDatum) {
                    newLine.append(s).append(",");
                }
                newLine.deleteCharAt(newLine.length() - 1);
                writer.write(String.valueOf(newLine) + "\n");
            }
            writer.write("positionEnd\n");
            writer.write("treeStart\n");

            for (String[] treeDatum : treeData) {
                StringBuilder newLine = new StringBuilder();
                for (String s : treeDatum) {
                    newLine.append(s).append(",");
                }
                newLine.deleteCharAt(newLine.length() - 1);
                writer.write(String.valueOf(newLine) + "\n");
            }
            writer.write("treeEnd\n");
            writer.write("optionStart\n");
            for (String[] optionDatum : optionData) {
                StringBuilder newLine = new StringBuilder();
                for (String s : optionDatum) {
                    newLine.append(s).append(",");
                }
                newLine.deleteCharAt(newLine.length() - 1);
                writer.write(String.valueOf(newLine) + "\n");
            }
            writer.write("optionEnd");

            writer.close();
            return true;
        } else {
            return false;
        }

    }

    @Override
    public ArrayList<String[]> loadProperties() throws FileNotFoundException {
        // return array of all properties in txt file as Strings
        // GameCreationInteractor will parse Strings to create Property instances
        ArrayList<String[]> allProperties = new ArrayList<>();
        String path = new File("src/save/properties.txt")
                .getAbsolutePath();
        File properties = new File(path);
        Scanner scan = new Scanner(properties);

        while (scan.hasNextLine()) {
            String property = scan.nextLine();
            String[] propertyAttributes = property.split(",");
            allProperties.add(propertyAttributes);
        }

        return allProperties;
    }

    public File getFile() {
        return this.file;
    }
    /**
     * 
     * @return a list of strings that represents all of the cards in the game
     * @throws IOException
     *
     */
    public List<String> loadCards() throws IOException {

        return Files.readAllLines(Paths.get("src/save/cards.txt"));

    }
}
