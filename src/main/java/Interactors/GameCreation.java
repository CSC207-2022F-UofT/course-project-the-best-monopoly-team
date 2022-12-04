package Interactors;

import Entities.*;
import Persistence.LoadAccess;
import Persistence.LoadFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameCreation {
    /** Create a new game by initializing a Board instance with default values.
     *
     * @param playersName an Arraylist of Strings which denote each player's name.
     * @param properties  an Arraylist of String[] arrays, each subarray contains the default instance attributes of a Property.
     * @return a Board instance initialized with default Properties and Players with names given by playersName.
     * @throws IOException in case there was an error with creating an ActionSpace in createCells
     */
    public Board createNewGame(ArrayList<String> playersName, ArrayList<String[]> properties) throws IOException {
        // This method is to create a brand-new game and initialize a new Board.

        ArrayList<Player> players = new ArrayList<>();
        ArrayList<Cell> propertiesSoFar;

        for (String name : playersName){
            Player newPlayer = new Player(name);
            players.add(newPlayer);
        }

        propertiesSoFar = parsePropertyData(properties);
        List<Cell> cells = createCells(propertiesSoFar, propertiesSoFar);

        return new Board(players, cells);
    }

    /** Loads a saved game by creating a Board instance with save data from gameData
     *
     * @param gameData an Arraylist with a sub Arraylists of String[] arrays where each array represents Player or Property instance data
     * @param newProperties an Arraylist where each sub String[] array contains default Property instance data
     * @return a Board instance initialized with gameData represented as their respective Entities
     * @throws IOException in case there was an error with creating an ActionSpace in createCells
     */
    public Board createSavedGame(ArrayList<ArrayList<String[]>> gameData, ArrayList<String[]> newProperties) throws IOException {
        ArrayList<Player> players = new ArrayList<>();
        ArrayList<Cell> properties = new ArrayList<>();
        ArrayList<Cell> standardProperties = parsePropertyData(newProperties);


        // playerData
        for (String[] instance : gameData.get(0)){
            if (instance.length == 5){
                // Player instance
                boolean inJail = false;
                if (instance[2].equals("true")) {
                    inJail = true;
                }

                Player player = new Player(instance[0], Integer.parseInt(instance[1]), inJail,
                        Integer.parseInt(instance[3]), Integer.parseInt(instance[4]));
                players.add(player);
            } else {
                // Property instance
                boolean mortgaged = false;
                if (instance[13].equals("true")) {
                    mortgaged = true;
                }
                Player owner = getOwner(players, instance[10]);

                int[] rentValues = new int[] {Integer.parseInt(instance[4]), Integer.parseInt(instance[5]),
                        Integer.parseInt(instance[6]), Integer.parseInt(instance[7]),
                        Integer.parseInt(instance[8]), Integer.parseInt(instance[9])};

                Property property = new Property(instance[0], instance[1], Integer.parseInt(instance[2]),
                        Integer.parseInt(instance[3]), rentValues, owner, Integer.parseInt(instance[11]),
                        Integer.parseInt(instance[12]), mortgaged);

                owner.addProperty(property);
                properties.add(property);
                }
            }

        List<Cell> cells = createCells(properties, standardProperties);
        Board savedBoard = new Board(players, cells);
        return savedBoard;
    }

    /** Gets the Player instance based on a String of a Player's name.
     *
     * @param players an Arraylist of Player instances.
     * @param playerName a String of a Player's name.
     * @return the Player instance which has the name playerName, or null if such a Player does not exist
     */
    private Player getOwner(ArrayList<Player> players, String playerName) {
        for (Player player : players) {
            if (player.getName().equals(playerName)) {
                return player;
            }
        }
        return null;
    }

    /** Creates all the Cells to be used to create a Board class. Properties which are owned
     *  by Players (which potentially have houses built already) are inserted in place of
     *  default Property instance.
     *
     * @param propertiesSoFar an Arraylist of Property instances which are owned by Players
     * @param standardProperties an Arraylist of all Property instances with default values
     * @return a List of all Cells on the Monopoly board
     * @throws IOException in case there was an error with creating an ActionSpace
     */
    public List<Cell> createCells(ArrayList<Cell> propertiesSoFar, ArrayList<Cell> standardProperties) throws IOException {

        CornerTiles go = new PassGo();
        CornerTiles jail = new JailSpace();
        CornerTiles freeParking = new FreeParking();
        CornerTiles goJail = new GoToJail();
        LoadAccess loadAccess = new LoadFile(new File(""));
        ActionSpaceCreationInteractor actionSpaceCreationInteractor = new ActionSpaceCreationInteractor(loadAccess);
        ActionSpace2 communityChest = actionSpaceCreationInteractor.loadComChestCards(new File("src/save/cards.txt"));
        ActionSpace2 chance = actionSpaceCreationInteractor.loadChanceCards(new File("src/save/cards.txt"));


        ArrayList<Cell> cells = standardProperties;
        cells.add(0, go);
        cells.add(2, communityChest);
        cells.add(4, chance);
        cells.add(7, chance);
        cells.add(10, jail);
        cells.add(18, communityChest);
        cells.add(21, freeParking);
        cells.add(23, chance);
        cells.add(31, goJail);
        cells.add(34, communityChest);
        cells.add(37, chance);
        cells.add(39, communityChest);

        if (!propertiesSoFar.equals(standardProperties)) {
            for (Cell property : propertiesSoFar) {
                Property cProperty = (Property) property;
                for (int i = 0; i < 40; i++) {
                    if (cells.get(i) instanceof Property) {
                        Property currentCell = (Property) cells.get(i);
                        if (currentCell.getName().equals(cProperty.getName())) {
                            cells.remove(i);
                            cells.add(i, cProperty);
                        }
                    }
                }
            }
        }

        return cells;
    }

    /** Creates an Arraylist of Property instances with default values.
     *
     * @param propertyData an Arraylist of String[] arrays where each array contains default values for a Property instance.
     * @return an Arraylist of Property instances initialized with their default values.
     */
    public ArrayList<Cell> parsePropertyData(ArrayList<String[]> propertyData) {
        // for unowned Property instances:
        // index [10] is int mortgageValue, [11] is int houses,
        // String playerOwnerName and booleanMortaged are not stored by default

        ArrayList<Cell> newProperties = new ArrayList<>();

        for (String[] data : propertyData) {
            int[] rentValues = new int[]{Integer.parseInt(data[4]), Integer.parseInt(data[5]),
                    Integer.parseInt(data[6]), Integer.parseInt(data[7]),
                    Integer.parseInt(data[8]), Integer.parseInt(data[9])};

            Property property = new Property(data[0], data[1], Integer.parseInt(data[2]),
                    Integer.parseInt(data[3]), rentValues, null, Integer.parseInt(data[10]),
                    Integer.parseInt(data[11]), false);

            newProperties.add(property);
        }
        return newProperties;
    }
}

