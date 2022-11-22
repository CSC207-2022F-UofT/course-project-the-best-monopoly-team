package Interactors;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TextFileTranslator implements DataAccess{
    private File file;

    /**
     * Constructor for TextFileTranslator which implements load and save game functionality.
     * The UseCaseInteractor handles implementation with respect to DataAccessInterface.
     *
     * @param file  is the file used to load or save game data to.
     */
    public TextFileTranslator(File file) {
        this.file = file;
    }

    /**
     * Searches the given folder denoted by folderPath and returns an array
     * of Files inside the given folder. Used by UseCaseInteractor to display
     * a selection of save files to the user when selecting which file to load a game from.
     *
     * @param folderPath  is the folder path to the folder which stores the user's save files.
     * @return  an array of the user's save files.
     */

    public File[] checkSaves(String folderPath){
        // TODO test in the future, list.Files() may not return as expected for src/save
        // checkSaves searches the given folder and returns an array of file names
        // the user selects the save file from the list of file names
        // loadGame takes in the filePath of the selected file name
        // and returns a String array of Player and Board data
        // the GameCreationInteractor will handle creating the class instances

        File folder = new File(folderPath);
        return folder.listFiles();
    }

    /**
     * Loads the game data from this.file.
     *
     * @return an Arraylist of 3 Arraylists of String[] arrays which represent saved data.
     *         The first Arraylist of String[] arrays represents either Player instance
     *         attributes or Property instance attributes of a Property owned by a Player.
     *         The second Arraylist of String[] arrays represents key-value pairs for a
     *         Player to their position on the Board. The third Arraylist of String[]
     *         arrays represents the main states for Tree data.
     *
     * @throws FileNotFoundException in the case that this.file does not exist
     */

    public ArrayList<ArrayList<String[]>> loadGame() throws FileNotFoundException {
        // loadGame reads the given filePath and returns an ArrayList of String arrays
        // each new line on in the txt file given by filePath contains the instance attributes of a Player instance
        // the Board instance attributes are separated from the Player instance attributes by a header "Board"
        // the Board instance attributes are stored in the last element of the ArrayList

        ArrayList<ArrayList<String[]>> gameInfo = new ArrayList<>();
        ArrayList<String[]> players = new ArrayList<>();
        ArrayList<String[]> playerPositions = new ArrayList<>();
        ArrayList<String[]> savedTree = new ArrayList<>();
        boolean player = false;
        boolean board = false;
        boolean tree = false;

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
            }

            if (player){
                String[] playerAttributes = data.trim().split(","); // denotes either a Player instance or a Property instance owned by a Player
                players.add(playerAttributes);

            } else if (board) {
                String[] position = data.trim().split(","); // denotes a key-value pair representing a Player name and their position
                playerPositions.add(position);

            } else if (tree) {
                String[] treeData = data.trim().split(","); // denotes an array of Integers representing mainStates
                savedTree.add(treeData);
            }
        }
        gameInfo.add(players);
        gameInfo.add(playerPositions);
        gameInfo.add(savedTree);

        return gameInfo;
    }

    /**
     * Given game data to save, save the information to txt file this.file.
     *
     * @param playerData each subarray of playerData represents a Player instance or Property instance
     *                   playerData should be ordered with a Player followed by the Properties they own
     *                   for each Player instance all values should be Strings:
     *                   index [0] name, [1] money, [2] booleanInJail ("true" or "false), [3] jailCards, [4] position
     *                   for each Property instance all values should be Strings:
     *                   index [0] name, [1] colour, [2] cost, [3] houseCost, [4] rent, [5] rent1H,
     *                   [6] rent2H, [7] rent3H, [8] rent4H, [9] rentHotel, [10] playerOwnerName,
     *                   [11] mortgageValue, [12] numHouses, [13] booleanMortaged ("true" or "false")
     *
     * @param boardData  each subarray of boardData represents a key-value pair between a Player and their position
     *                   index[0] of the subarray is String playerName, [1] is int position
     *
     * @param mainStates an Integer[] array of ints representing main states for Trees
     * @return a boolean representing whether the save to this.file was successful
     * @throws IOException in the case that there was an error writing the data in the subarrays to the file
     */
    public boolean saveGame(String[][] playerData, String[][] boardData, Integer[] mainStates) throws IOException {
        File saveFile = file;

        if (saveFile.createNewFile()){
            FileWriter writer = new FileWriter(file.getPath());

            writer.write("playerStart\n");
            // loop through playerData and save each Player instance as a line
            saveArray(playerData, writer);
            writer.write("playerEnd\n");
            writer.write("positionStart\n");

            // loop through Board instance and save player-position key value pair as a line
            saveArray(boardData, writer);
            writer.write("positionEnd\n");
            writer.write("treeStart\n");

            // save the Integer[] mainStates as a line
            StringBuilder newLine = new StringBuilder();
            for (int stateDatum : mainStates) {
                newLine.append(stateDatum).append(",");
            }
            newLine.deleteCharAt(newLine.length() - 1);
            writer.write(String.valueOf(newLine) + "\n");
            writer.write("treeEnd\n");

            writer.close();
            return true;
        } else {
            return false;
        }

    }

    /**
     * Loops through each subarray of data and stores it as a line in a txt file.
     *
     * @param data      a 2D array where each subarray stores information of an instance of an Entity to be saved
     * @param writer    a FileWriter instance with the save file
     * @throws IOException  in case the writer was unable to write to the file
     */
    private void saveArray(String[][] data, FileWriter writer) throws IOException {
        for (String[] datum : data) {
            StringBuilder newLine = new StringBuilder();
            for (String s : datum) {
                newLine.append(s).append(",");
            }
            newLine.deleteCharAt(newLine.length() - 1);
            writer.write(String.valueOf(newLine) + "\n");
        }
    }

    /**
     * Loads all the instance attributes of properties in properties.txt as an array of String values.
     *
     * @return an Arraylist of String[] subarrays where each String[] array contains instance attributes of a Property
     * @throws FileNotFoundException in case there is an error with finding the properties.txt file
     */
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
}
