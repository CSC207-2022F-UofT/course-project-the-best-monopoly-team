package Entities;

import java.util.*;
/**
 * Represents a Monopoly board.
 */
public class Board{

    List<Player> players;
    List<Cell> cells;

    /**
     * This is the constructor which creates a Board instance.
     * @param players A List of Player instances that we wish to be a part of the board.
     * @param cells A List of Cell instances that we wish to be a part of the board.
     */
    public Board(List<Player> players, List<Cell> cells){
        this.players = players;
        this.cells = cells;
    }

    /**
     * This method is used to return a List of all the Player instances that are in Board.
     * @return A List of all the Player instances stored in Board.
     */
    public List<Player> getPlayers(){
        return players;
    }

    /**
     * This method is used to remove a player from Board.
     * @param player The player that must be removed.
     */
    public void removePlayer(Player player){
        this.players.remove(player);
    }
    /**
     * This method is used to return a List of all the Cell instances on Board.
     * @return This returns this.cells, a List of all the Cell instances on Board.
     */
    public List<Cell> getCells(){return cells;}
    /**
     * This method is used to return a Cell at a specific position represented by an int value.
     * @param position The position of the cell.
     * @return This returns the Cell at the int position.
     */
    public Cell getCell(int position){return cells.get(position);}

    /**
     * This method is used to return the Cell instance that a Player instance is on.
     * @param player This is a Player instance that we want to know which Cell it is on.
     * @return This returns a Cell which the Player instance is on.
     */
    public Cell getPlayerCell(Player player){
        int position = player.getPosition();
        return this.getCell(position);
    }

}