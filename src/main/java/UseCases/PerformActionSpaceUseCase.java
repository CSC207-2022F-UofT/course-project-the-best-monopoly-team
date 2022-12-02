package UseCases;

import Entities.Player;
import Entities.ActionSpace2;
import Entities.Board;

public interface PerformActionSpaceUseCase {
    
    String performAction(ActionSpace2 actionSpace2, Player player, Board board);

}
