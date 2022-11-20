package Interactors;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TextFileTranslator implements DataAccess{
    // each subarray holds the instance attributes of a Player instance
    private String[][] playerData;

    private String[][] boardData;
    private String[][] treeData;
    private String[][] optionData;
    private File file;

    public TextFileTranslator(String[][] playerData, String[][] boardData,
                              String[][] treeData, String[][] optionData, File file) {
        this.playerData = playerData;
        this.boardData = boardData;
        this.treeData = treeData;
        this.optionData = optionData;
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
    public ArrayList<ArrayList<String[]>> loadGame(String filePath) throws FileNotFoundException {
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

        File gameData = new File(filePath);
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
    public boolean saveGame(String fileName) throws IOException {
        // fileName should include path (under save folder)
        File saveFile = new File(fileName);

        if (saveFile.createNewFile()){
            FileWriter writer = new FileWriter(fileName);

            writer.write("playerStart\n");
            // loop through playerData and save each Player instance as a line
            for (String[] playerDatum : this.playerData) {
                StringBuilder newLine = new StringBuilder();
                for (String s : playerDatum) {
                    newLine.append(s).append(",");
                }
                newLine.deleteCharAt(newLine.length() - 1);
                writer.write(String.valueOf(newLine) + "\n");
            }
            writer.write("playerEnd\n");
            writer.write("positionStart\n");

            // loop through Board instance and save player-position key value pair as a line
            for (String[] boardDatum : this.boardData) {
                StringBuilder newLine = new StringBuilder();
                for (String s : boardDatum) {
                    newLine.append(s).append(",");
                }
                newLine.deleteCharAt(newLine.length() - 1);
                writer.write(String.valueOf(newLine) + "\n");
            }
            writer.write("positionEnd\n");
            writer.write("treeStart\n");

            for (String[] treeDatum : this.treeData) {
                StringBuilder newLine = new StringBuilder();
                for (String s : treeDatum) {
                    newLine.append(s).append(",");
                }
                newLine.deleteCharAt(newLine.length() - 1);
                writer.write(String.valueOf(newLine) + "\n");
            }
            writer.write("treeEnd\n");
            writer.write("optionStart\n");
            for (String[] optionDatum : this.optionData) {
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

    public File getFile() {
        return this.file;
    }
}
