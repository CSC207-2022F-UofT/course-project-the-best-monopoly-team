package Entities;

import java.util.HashMap;
import java.util.Map;

public class Board{

    Player[] players;
    Cell[] cells;
    Map <Player, String> playerPosition = new HashMap<Player, String>();


    public Board(Player[] players, Cell[] cells){
        this.players = players;
        this.cells = cells;
        for (Player player : players) {
            this.playerPosition.put(player, "0");
        }
    }

    public Cell getPlayerCell(Player player){
        int cell_number = Integer.parseInt(this.playerPosition.get(player));
        return this.cells[cell_number];
    }

    public void updatePlayerPosition(Player player){
        int position = player.position;
        playerPosition.put(player, Integer.toString(position));
    }

}