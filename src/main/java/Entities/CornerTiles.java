package Entities;

/**
 * This is an abstract class representing the corner tiles on the monopoly board.
 */
public abstract class CornerTiles extends Cell {
    public String getType(){
        return "Corner Tile";
    }
    public abstract String returnMessage(Player player);
}
