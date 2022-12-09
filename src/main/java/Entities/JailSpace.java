package Entities;

/**
 * This class represents the Jail corner tile on the Monopoly board.
 */
public class JailSpace extends CornerTiles {

    /**
     * This method returns a String containing a message to be shown to the player who lands on the Jail space.
     * @param player the player that the message is shown to.
     * @return a String containing a message to be shown to a player who lands on the Jail space.
     */
    @Override
    public String returnMessage(Player player) {
        if (player.isInJail())
            return " You are still in Jail";
        else
            return " You're visiting Jail";
    }
}
