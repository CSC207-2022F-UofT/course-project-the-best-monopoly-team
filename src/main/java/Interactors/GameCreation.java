package Interactors;

import Entities.Board;
import Entities.Cell;
import Entities.Player;
import Entities.Property;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class GameCreation {

    public void createGame() throws FileNotFoundException {
        // This method is to create a brand-new game and initialize a new Board.
        ArrayList<Player> players = new ArrayList<>();
        Player player1 = new Player("red");
        Player player2 = new Player("blue");
        Player player3 = new Player("green");
        Player player4 = new Player("yellow");
        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player4);

        ArrayList<Cell> properties = new ArrayList<>();
        ArrayList<String[]> propertyList = Property.loadProperties();
        for (String[] item : propertyList) {
            Property property = new Property(item[0], item[1], Integer.parseInt(item[2]), Integer.parseInt(item[3]),
                    Integer.parseInt(item[4]), Integer.parseInt(item[5]));
            properties.add(property);
        }
        Object[][] object = new Object[properties.size()][6];
        Board board = new Board(players, properties, object);
    }

    public void createGame(ArrayList<String[]> playerData) throws FileNotFoundException {
        // This method is to re-create a game based on saved data - basically when the players decide to resume a
        // saved game.
        ArrayList<Player> players = new ArrayList<>();
        for (String[] player : playerData) {
            Player newPlayer = new Player(player[0]);
            newPlayer.money = Integer.parseInt(player[1]);
            newPlayer.inJail = Boolean.parseBoolean(player[2]);
            newPlayer.jailCards = Integer.parseInt(player[3]);
            newPlayer.position = Integer.parseInt(player[4]);
        }
        ArrayList<Cell> properties = new ArrayList<>();
        ArrayList<String[]> propertyList = Property.loadProperties();
        for (String[] item : propertyList) {
            Property property = new Property(item[0], item[1], Integer.parseInt(item[2]), Integer.parseInt(item[3]),
                    Integer.parseInt(item[4]), Integer.parseInt(item[5]));
            properties.add(property);
        }
        Object[][] object = new Object[properties.size()][6];
        Board board = new Board(players, properties, object);
    }
}
