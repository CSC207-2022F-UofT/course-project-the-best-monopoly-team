package Entities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a Monopoly board.
 */
public class Board{

    List<Player> players;
    List<Cell> cells;
    Map <Player, Integer> playerPositions = new HashMap<Player, Integer>();
    Property[] properties;

    /**
     * This is the constructor which creates a Board instance.
     * @param players A List of Player instances that we wish to be a part of the board.
     * @param cells A List of Cell instances that we wish to be a part of the board.
     * @param propertyData A two-dimensional array which includes the data important to form Property instances.
     */
    public Board(List<Player> players, List<Cell> cells, Object[][] propertyData){
        this.players = players;
        this.cells = cells;
        for (Player player : players) {
            this.playerPositions.put(player, 0);
        }
        Property[] properties = new Property[propertyData.length];
        for (int i = 0; i < propertyData.length; i++){
            Object[] data = propertyData[i];
            String name = (String) data[0];
            String colour = (String) data[1];
            int cost = (int) data[2];
            int houseCost = (int) data[3];
            int rent = (int) data[4];
            int rent1H = (int) data[5];
            int rent2H = (int) data[6];
            int rent3H = (int) data[7];
            int rent4H = (int) data[8];
            int rentHotel = (int) data[9];
            Player owner = null;
            if ((int) data[10] != 0) {
                owner = players.get((int) data[10]);
            }
            int mortgageValue = (int) data[11];
            int houses = (int) data[12];
            boolean mortgaged = false;
            String mortgageString = (String) data[13];
            if (mortgageString.equals("true")) {
                mortgaged = true;
            }
            properties[i] = new Property(name, colour, cost, houseCost, rent, rent1H,
                    rent2H, rent3H, rent4H, rentHotel, owner,
                    mortgageValue, houses, mortgaged);
        }
        this.properties = properties;
    }

    /**
     * This method is used to return a List of all the Player instances that are in Board.
     * @return A List of all the Player instances stored in Board.
     */
    public List<Player> getPlayers(){
        return players;
    }

    /**
     * This method is used to return a List of all the Cell instances on Board.
     * @return This returns this.cells, a List of all the Cell instances on Board.
     */
    public List<Cell> getCells(){return cells;}

    /**
     * This method is used to get a Map of all the positions of Player instances on Board.
     * @return This returns a Map with a Player instance as a key and an Integer representing the Player's position.
     */
    public Map<Player, Integer> getPlayerPositions(){return playerPositions;}

    /**
     * This method is used to return an array of Property objects containing all the Properties on Board.
     * @return This returns an array of Property objects that are stored in this.properties.
     */
    public Property[] getProperties(){return properties;}

    /**
     * This method is used to return the Cell instance that a Player instance is on.
     * @param player This is a Player instance that we want to know which Cell it is on.
     * @return This returns a Cell which the Player instance is on.
     */
    public Cell getPlayerCell(Player player){
        return this.cells.get(this.playerPositions.get(player));
    }

    /**
     * This method updates the position of a player by making changes to the map, this.playerPositions.
     * @param player The player that is in need of a position update.
     */
    public void updatePlayerPosition(Player player){
        playerPositions.put(player, player.position);
    }

}