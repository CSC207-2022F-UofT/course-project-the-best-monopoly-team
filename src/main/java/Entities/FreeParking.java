package Entities;

/**
 * This class represents the free parking corner tile.
 */
public class FreeParking extends CornerTiles {

    /**
     * This method returns a String of a message when the player is on the Free Parking corner tile.
     * @param player
     * @return
     */
    @Override
    public String returnMessage(Player player) {
        return " You landed on Free Parking!";
    }
}
