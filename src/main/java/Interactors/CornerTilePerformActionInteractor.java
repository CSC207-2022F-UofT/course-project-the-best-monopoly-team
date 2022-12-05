package Interactors;

import Entities.Board;
import Entities.CornerTiles;
import Entities.Player;
import UseCases.CornerTilePerformActionUseCase;

public class CornerTilePerformActionInteractor implements CornerTilePerformActionUseCase {
    /**
     * Take the player and the board and decide which action
     * should be performed on the cornerTiles according to its type.
     *
     * @param currentPlayer the player instance of the current player
     * @param board the board instance of the current game
     * @return The message associated with the action.
     */
    @Override
    public String performAction(Player currentPlayer, Board board, CornerTiles corner) {
        String returnMessage = "";
        switch (corner.getType()) {
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
