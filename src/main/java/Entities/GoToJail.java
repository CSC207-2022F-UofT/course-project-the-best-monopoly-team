package Entities;

/**
 * This class represents the "Go To Jail" cell on the Monopoly board.
 */
public class GoToJail extends CornerTiles {
    /**
     * This method returns a message when the Player lands on the "Go To Jail" tile.
     * @param player the player that lands on the tile.
     * @return a String of the message when the Player lands on the "Go To Jail" tile.
     */
    @Override
    public String returnMessage(Player player) {
        player.changeJailStatus();
        player.setPosition(10);
        return " Go to Jail!";
    }
}
