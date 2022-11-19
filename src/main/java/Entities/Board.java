package Entities;

import java.util.*;

public class Board{

    List<Player> players;
    List<Cell> cells;
    Map <Player, Integer> playerPositions = new HashMap<Player, Integer>();
    // Property[] properties;

    public Board(List<Player> players, List<Cell> cells){
        this.players = players;
        this.cells = cells;
//        for (Player player : players) {
//            this.playerPositions.put(player, 0);
//        }
//        Property[] properties = new Property[propertyData.length];
//        for (int i = 0; i < propertyData.length; i++){
//            Object[] data = propertyData[i];
//            String name = (String) data[0];
//            String colour = (String) data[1];
//            int cost = (int) data[2];
//            int houseCost = (int) data[3];
//            int rent = (int) data[4];
//            int rent1H = (int) data[5];
//            int rent2H = (int) data[6];
//            int rent3H = (int) data[7];
//            int rent4H = (int) data[8];
//            int rentHotel = (int) data[9];
//            Player owner = null;
//            if ((int) data[10] != 0) {
//                owner = players.get((int) data[10]);
//            }
//            int mortgageValue = (int) data[11];
//            int houses = (int) data[12];
//            boolean mortgaged = false;
//            String mortgageString = (String) data[13];
//            if (mortgageString.equals("true")) {
//                mortgaged = true;
//            }
//            properties[i] = new Property(name, colour, cost, houseCost, rent, rent1H,
//                    rent2H, rent3H, rent4H, rentHotel, owner,
//                    mortgageValue, houses, mortgaged);
//        }
//        this.properties = properties;
    }

    public List<Player> getPlayers(){
        return players;
    }

    public void removePlayer(Player player){
        this.players.remove(player);
    }

    public List<Cell> getCells(){return cells;}

    public Cell getCell(int position){return cells.get(position);}

    public Map<Player, Integer> getPlayerPositions(){return playerPositions;}

    // public Property[] getProperties(){return properties;}
    public Cell getPlayerCell(Player player){
        return this.cells.get(this.playerPositions.get(player));
    }

    public void updatePlayerPosition(Player player){
        playerPositions.put(player, player.getPosition());
    }

}