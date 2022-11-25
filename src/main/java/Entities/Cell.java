package Entities;

public abstract class Cell {
    public abstract String performAction(Player player, Board board);
    public abstract String getType();
}

