package UseCases;

import Entities.Player;
import Entities.ActionSpace;
import Entities.Board;

/**
 * Interface for the performAction method for action space logic interactors
 */
public interface PerformActionSpaceUseCase {
    
    String performAction(ActionSpace actionSpace, Player player, Board board);

}
