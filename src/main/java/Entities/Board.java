package Entities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board{

    List<Player> players;
    List<Cell> cells;
    Map <Player, Integer> playerPositions = new HashMap<Player, Integer>();
    Property[] properties;

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
            int rent = (int) data[3];
            int mortgage = (int) data[4];
            int houseCost = (int) data[5];
            properties[i] = new Property(name, colour, cost, rent, mortgage, houseCost);
        }
        this.properties = properties;
    }

    public List<Player> getPlayers(){
        return players;
    }

    public List<Cell> getCells(){return cells;}

    public Map<Player, Integer> getPlayerPositions(){return playerPositions;}

    public Property[] getProperties(){return properties;}

    public Cell getPlayerCell(Player player){
        return this.cells.get(this.playerPositions.get(player));
    }

    public void updatePlayerPosition(Player player){
        playerPositions.put(player, player.position);
    }

}