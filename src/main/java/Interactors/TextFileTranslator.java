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

    private String[] boardData;
    private File file;

    public TextFileTranslator(String[][] playerData, String[] boardData, File file) {
        this.playerData = playerData;
        this.boardData = boardData;
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
    public ArrayList<String[]> loadGame(String filePath) throws FileNotFoundException {
        // loadGame reads the given filePath and returns an ArrayList of String arrays
        // each new line on in the txt file given by filePath contains the instance attributes of a Player instance
        // the Board instance attributes are separated from the Player instance attributes by a header "Board"
        // the Board instance attributes are stored in the last element of the ArrayList

        ArrayList<String[]> gameInfo = new ArrayList<>();
        boolean board = false;

        File gameData = new File(filePath);
        Scanner scan = new Scanner(gameData);

        while (scan.hasNextLine()) {
            String data = scan.nextLine();
            if (data.trim().equals("Board")) {
                board = true;
                continue;
            }

            if (!board){
                String[] playerAttributes = data.trim().split(",");
                gameInfo.add(playerAttributes);
            } else {
                String[] boardData = data.trim().split(",");
                gameInfo.add(boardData);
            }
        }

        return gameInfo;
    }

    @Override
    public boolean saveGame(String fileName) throws IOException {
        // fileName should include path (under save folder)
        File saveFile = new File(fileName);

        if (saveFile.createNewFile()){
            FileWriter writer = new FileWriter(fileName);

            // loop through playerData and save each Player instance as a line
            for (String[] playerDatum : this.playerData) {
                StringBuilder newLine = new StringBuilder();
                for (String s : playerDatum) {
                    newLine.append(s).append(",");
                }
                newLine.deleteCharAt(newLine.length() - 1);
                writer.write(String.valueOf(newLine) + "\n");
            }
            // write a line for the header "Board"
            writer.write("Board\n");

            StringBuilder newLine = new StringBuilder();
            // loop through Board instance and save attributes as a line
            for (String boardDatum : this.boardData) {
                newLine.append(boardDatum).append(",");
            }
            newLine.deleteCharAt(newLine.length() - 1);
            writer.write(String.valueOf(newLine));

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
