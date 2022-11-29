package Interactors;

import Entities.*;
import Persistence.DataAccess;
import Persistence.TextFileTranslator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameCreation {

    public Board createNewGame(ArrayList<String> playersName, ArrayList<String[]> properties) throws IOException {
        // This method is to create a brand-new game and initialize a new Board.

        ArrayList<Player> players = new ArrayList<>();
        ArrayList<Cell> propertiesSoFar = new ArrayList<>();

        for (String name : playersName){
            Player newPlayer = new Player(name);
            players.add(newPlayer);
        }

        propertiesSoFar = parsePropertyData(properties);
        List<Cell> cells = createCells(propertiesSoFar, propertiesSoFar);

        return new Board(players, cells);
    }

    public Board createSavedGame(ArrayList<ArrayList<String[]>> gameData, ArrayList<String[]> newProperties) throws IOException {
        // This method is to re-create a game based on saved data - basically when the players decide to resume a
        // saved game.

        ArrayList<Player> players = new ArrayList<>();
        ArrayList<Cell> properties = new ArrayList<>();
        ArrayList<Cell> standardProperties = parsePropertyData(newProperties);
        HashMap<Player, Integer> playerPositions = new HashMap<>();

        for (int i=0; i<= gameData.size(); i++){
            switch (i) {
                case 0:
                    // playerData
                    for (String[] instance : gameData.get(i)){
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
                case 1:
                    // playerPositions
                    for (String[] position : gameData.get(i)) {
                        playerPositions.put(getOwner(players, position[0]),Integer.parseInt(position[1]));
                    }
                    //TODO create saved Tree with savedTree and treeOptions from TextFileTranslator
                case 2:
                    // savedTree

                case 3:
                    // treeOptions
            }
        }
        List<Cell> cells = createCells(properties, standardProperties);

        return new Board(players, cells, playerPositions);
    }
    private Player getOwner(ArrayList<Player> players, String playerName) {
        for (Player player : players) {
            if (player.getName().equals(playerName)) {
                return player;
            }
        }
        return null;
    }
    private Property getProperty(ArrayList<Cell> standardProperties, String propertyName) {
        for (Cell property: standardProperties) {
            Property cProperty = (Property) property;
            if (cProperty.getName().equals(propertyName)){
                return cProperty;
            }
        }
        return null;
    }
    private List<Cell> createCells(ArrayList<Cell> propertiesSoFar, ArrayList<Cell> standardProperties) throws IOException {

        CornerTiles go = new CornerTiles("Go");
        CornerTiles jail = new CornerTiles("jail");
        CornerTiles freeParking = new CornerTiles("freeParking");
        CornerTiles goJail = new CornerTiles("goToJail");

        DataAccess textFileTranslator = new TextFileTranslator(new File(""));
        ActionSpaceCreationInteractor actionSpaceCreationInteractor = new ActionSpaceCreationInteractor(textFileTranslator);
        ActionSpace communityChest = actionSpaceCreationInteractor.loadComChestCards(new File("src/save/cards.txt"));
        ActionSpace chance = actionSpaceCreationInteractor.loadChanceCards(new File("src/save/cards.txt"));
        // TODO: determine when to call jail ActionSpaces
        // ActionSpace jail = new ActionSpace("jail");

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
                for (int i = 0; i <= 40; i++) {
                    if (cells.get(i) instanceof Property) {
                        Property currentCell = (Property) cells.get(i);
                        if (currentCell.getName().equals(cProperty.getName())) ;
                        cells.remove(i);
                        cells.add(i, cProperty);
                    }
                }
            }
        }

        return cells;
    }
    private ArrayList<Cell> parsePropertyData(ArrayList<String[]> propertyData) throws IOException {
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

