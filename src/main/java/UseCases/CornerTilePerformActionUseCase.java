package UseCases;

import Entities.Player;
import Entities.CornerTiles;

public interface CornerTilePerformActionUseCase {
    String performAction(Player currentPlayer, CornerTiles corner);
}
