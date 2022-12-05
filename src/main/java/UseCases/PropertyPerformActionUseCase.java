package UseCases;

import Entities.Board;
import Entities.Player;
import Entities.Property;

public interface PropertyPerformActionUseCase {
    String performAction(Property property, Player player);
}
