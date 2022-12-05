package Entities;

public abstract class CornerTiles extends Cell {
    public String getType(){
        return "Corner Tile";
    }
    public abstract String returnMessage(Player player);
}
