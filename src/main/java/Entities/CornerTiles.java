package Entities;

public class CornerTiles extends Cell{
    public void setJail(boolean jail) {
        isJail = jail;
    }

    boolean isJail = false;
    @Override
    public String performAction(Player currentPlayer) {
        if (isJail){
            //TODO: put player in jail and put them in correct location
            currentPlayer.position = 23;
            return "You went to jail";
        }
        return "You're at "+ name + " nothing happens!";
    }
}
