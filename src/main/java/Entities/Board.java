package Entities;

import java.util.*;

public class Board{

    List<Player> players;
    List<Cell> cells;
    Map <Player, Integer> playerPositions = new HashMap<Player, Integer>();

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
            int[] rentValues = new int[] {(int) data[4], (int) data[5], (int) data[6],
                                          (int) data[7], (int) data[8], (int) data[9]};
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
            properties[i] = new Property(name, colour, cost, houseCost, rentValues,
                                         owner, mortgageValue, houses, mortgaged);
        }

    }

    public List<Player> getPlayers(){
        return players;
    }

    public void removePlayer(Player player){
        List<Player> newPlayers = players;
        newPlayers.remove(player);
    }

    public List<Cell> getCells(){return cells;}

    public Cell getCell(int position){return cells.get(position);}

    public Map<Player, Integer> getPlayerPositions(){return playerPositions;}


    public Cell getPlayerCell(Player player){
        return this.cells.get(this.playerPositions.get(player));
    }

    public void updatePlayerPosition(Player player){
        playerPositions.put(player, player.getPosition());
    }

}