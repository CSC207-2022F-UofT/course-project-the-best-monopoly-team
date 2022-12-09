package UseCases;

import Entities.Player;
import Entities.Property;

/**
 * Interface for the performAction method for property logic interactors
 */
public interface PropertyPerformActionUseCase {
    String performAction(Property property, Player player);
}
