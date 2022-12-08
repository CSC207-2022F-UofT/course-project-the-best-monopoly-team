package Interactors;

import Entities.Player;
import Entities.Property;
import UseCases.PropertyPerformActionUseCase;


public class PropertyPerformActionInteractor implements PropertyPerformActionUseCase {
    /**
     * This method overrides performAction from the PropertyPerformActionUseCase interface and allows actions to be performed on a player
     * when they land on a property cell. The action (e.g. pay rent) is determined by whether the player owns this property.
     * @param currentPlayer the player currently landing on this property
     * @param property the property instance that was landed on
     * @return A String notifying whether they've landed on their own property and what actions are being performed
     */
    @Override
    public String performAction(Property property, Player currentPlayer) {
        // if player balance is negative after paying, then give them option to mortgage properties, or declare bankruptcy
        if (property.getOwner().equals(currentPlayer)){
            return " You landed on a property you own";
        } else {
            currentPlayer.pay(property.getOwner(), property.getRent());
            return " Paid $" + property.getRent() + " to " + property.getOwner().getName() +
                    " for landing on " + property.getName();
        }
    }
}
