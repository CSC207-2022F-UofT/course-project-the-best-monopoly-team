package UseCases;

import Entities.Player;
import Entities.ActionSpace;
import Entities.Board;

public interface PerformActionSpaceUseCase {
    
    String performAction(ActionSpace actionSpace, Player player, Board board);

}
