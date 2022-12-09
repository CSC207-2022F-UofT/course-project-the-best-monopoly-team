package Entities;

/**
 * This class represents the "Pass Go" tile on the Monopoly board.
 */
public class PassGo extends CornerTiles {

    /**
     * This method returns a message when a Player instance passes the Pass Go tile.
     * @param player the Player that the message is shown to.
     * @return a String of the message.
     */
    @Override
    public String returnMessage(Player player) {
        return " Passed Go!, Collected 200";
    }
}
