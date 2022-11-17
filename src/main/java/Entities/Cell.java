package Entities;

abstract class Cell {
    public String performAction(){
        return "";
    }
    public abstract String performAction(Player player, Board board);

}
