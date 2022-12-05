package Entities;

public class JailSpace extends CornerTiles {
    @Override
    public String returnMessage(Player player) {
        if (player.isInJail())
            return "You are still in Jail";
        else
            return "You're visiting Jail";
    }
}
