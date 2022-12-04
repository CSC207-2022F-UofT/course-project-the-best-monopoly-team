package Entities;

public class GoToJail extends CornerTiles {
    @Override
    public String returnMessage(Player player) {
        player.changeJailStatus();
        player.setPosition(11);
        return "Go to Jail!";
    }
}
