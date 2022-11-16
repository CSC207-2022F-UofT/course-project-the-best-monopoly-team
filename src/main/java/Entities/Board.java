package Entities;

import java.util.*;

public class Board{

    Player[] players;
    Cell[] cells;
    Map <Player, Integer> playerPositions = new HashMap<Player, Integer>();
    Property[] properties;

    public Board(Player[] players, Cell[] cells, Object[][] propertyData){
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

    public Player[] getPlayers(){
        return players;
    }

    public void removePlayer(Player player){
        List<Player> newPlayers = Arrays.asList(players);
        newPlayers.remove(player);
        Player[] newPlayersArray = new Player[players.length - 1];
        for(int i = 0; i < newPlayers.size(); i++){
            newPlayersArray[i] = newPlayers.get(i);
        }
        this.players = newPlayersArray;
    }

    public Cell[] getCells(){return cells;}

    public Cell getCell(int position){return cells[position];}

    public Map<Player, Integer> getPlayerPositions(){return playerPositions;}

    public Property[] getProperties(){return properties;}

    public Cell getPlayerCell(Player player){
        return this.cells[this.playerPositions.get(player)];
    }

    public void updatePlayerPosition(Player player){
        playerPositions.put(player, player.position);
    }

}