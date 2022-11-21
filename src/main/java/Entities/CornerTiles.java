package Entities;

public class CornerTiles extends Cell{
    private String type;

    public CornerTiles(String type) {
        this.type = type;
    }
    @Override
    public String performAction(Player currentPlayer, Board board) {
        //TODO: check this.type and perform corresponding action
        String returnMessage = new String();
        switch (this.type) {
            case "Go":
                returnMessage = "Passed Go! Collected 200";
                break;
            case "jail":
                if (currentPlayer.isInJail()) {
                    returnMessage = "You are still in Jail";
                } else {
                    returnMessage = "You're visiting Jail";
                }
                break;
            case "freeParking":
                returnMessage = "You landed on Free Parking!";
                break;
            case "goToJail":
                currentPlayer.setPosition(11);
                currentPlayer.changeJailStatus();
                returnMessage = "Go to Jail!";
                break;
        }
        return returnMessage;
    }
}
