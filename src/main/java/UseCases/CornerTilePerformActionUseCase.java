package UseCases;

import Entities.Player;
import Entities.CornerTiles;
/**
 * Interface for the performAction method for corner tiles logic interactors
 */
public interface CornerTilePerformActionUseCase {
    String performAction(Player currentPlayer, CornerTiles corner);
}
