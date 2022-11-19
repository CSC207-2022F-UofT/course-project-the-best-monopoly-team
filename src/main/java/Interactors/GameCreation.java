package Interactors;

import Entities.Board;
import Entities.Cell;
import Entities.Player;
import Entities.Property;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class GameCreation {

    public Board createNewGame(ArrayList<String> playersName) throws FileNotFoundException {
        // This method is to create a brand-new game and initialize a new Board.

        ArrayList<Player> players = new ArrayList<>();
        for (String name : playersName){
            Player newPlayer = new Player(name);
            players.add(newPlayer);
        }

        ArrayList<Cell> properties = new ArrayList<>();
        ArrayList<String[]> propertyList = Property.loadProperties();
        for (String[] item : propertyList) {
            Property property = new Property(item[0], item[1], Integer.parseInt(item[2]), Integer.parseInt(item[3]),
                    Integer.parseInt(item[4]), Integer.parseInt(item[5]), Integer.parseInt(item[6]),
                    Integer.parseInt(item[7]), Integer.parseInt(item[8]), Integer.parseInt(item[9]), null, Integer.parseInt(item[11]),
                    Integer.parseInt(item[12]), Boolean.parseBoolean(item[13]));
            properties.add(property);
        }
        // Object[][] object = new Object[properties.size()][6];

        return new Board(players, properties);
    }

    public Board createSavedGame(ArrayList<String[]> playerData) throws FileNotFoundException {
        // This method is to re-create a game based on saved data - basically when the players decide to resume a
        // saved game.
        ArrayList<Player> players = new ArrayList<>();
        for (String[] player : playerData) {
            Player newPlayer = new Player(player[0]);
            newPlayer.setMoney(Integer.parseInt(player[1]));
            newPlayer.setInJail(Boolean.parseBoolean(player[2]));
            newPlayer.setJailCards(Integer.parseInt(player[3]));
            newPlayer.setPosition(Integer.parseInt(player[4]));
            players.add(newPlayer);
        }
        ArrayList<Cell> properties = new ArrayList<>();
        ArrayList<String[]> propertyList = Property.loadProperties();
        for (String[] item : propertyList){
            Property property = new Property(item[0], item[1], Integer.parseInt(item[2]), Integer.parseInt(item[3]),
                    Integer.parseInt(item[4]), Integer.parseInt(item[5]), Integer.parseInt(item[6]),
                    Integer.parseInt(item[7]), Integer.parseInt(item[8]), Integer.parseInt(item[9]), null, Integer.parseInt(item[11]),
                    Integer.parseInt(item[12]), Boolean.parseBoolean(item[13]));
            properties.add(property);
        }
        // Object[][] object = new Object[properties.size()][6];

        return new Board(players, properties);
    }
}
