package Entities;

public class CornerTiles extends Cell{
    public void setJail(boolean jail) {
        isJail = jail;
    }
    public String name;

    boolean isJail = false;
    @Override
    public String performAction(Player currentPlayer, Board board) {
        if (isJail){
            //TODO: put player in jail and put them in correct location
            currentPlayer.setPosition(23);
            return "You went to jail";
        }
        return "You're at "+ name + " nothing happens!";
    }
}
