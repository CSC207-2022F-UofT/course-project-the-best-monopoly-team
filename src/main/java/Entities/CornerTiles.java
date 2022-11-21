package Entities;

public class CornerTiles extends Cell{
    private String type;
    public void setJail(boolean jail) {
        isJail = jail;
    }

    public String name;
    boolean isJail = false;
    public CornerTiles(String type) {
        this.type = type;
    }
    @Override
    public String performAction(Player currentPlayer, Board board) {
        //TODO: check this.type and perform corresponding action

        switch (this.type){
            case "Go":
            case "justVisiting":
            case "inJail":
            case "freeParking":
            case "goToJail":
        }

        if (isJail){
            //TODO: put player in jail and put them in correct location
            currentPlayer.setPosition(11);
            return "You went to jail";
        }
        return "You're at "+ name + " nothing happens!";
    }
}
