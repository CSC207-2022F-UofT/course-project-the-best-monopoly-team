package UseCases;

import Entities.Board;
import Entities.Player;
import Entities.CornerTiles;

public interface CornerTilePerformActionUseCase {
    String performAction(Player currentPlayer, Board board, CornerTiles corner);
}
