package TreeHandlers;

import Entities.Cell;
import Entities.Property;
import Entities.State;

public class Roll extends TreeHandler implements NodeLogic {
    @Override
    public State create_state(int input) {
        State currentState = new State();
        if (mainStates[1] == 0) {
            //roll the dice and update the position
            diceRoll = currentPlayer.rollDice(0);
            board.updatePlayerPosition(currentPlayer);

            //get the space landed on
            Cell landedOnCell = board.getCell(currentPlayer.getPosition());

                    /*
                    if the space is a property and has no owner, transverse to a branch, otherwise,
                    transverse to another
                     */
            if (landedOnCell instanceof Property &&
                    ((Property) landedOnCell).getOwner() == null) {
                gameLogicInteractor.transverseCurrentTree(0);
            } else {
                //perform the action on the space as well
                answer = landedOnCell.performAction(currentPlayer,board);
                gameLogicInteractor.transverseCurrentTree(1);
            }
            //perform the logic in the new node.
            currentState = gameLogicInteractor.handleTree(0);
            //player can no longer roll
            mainStates[1] = 1;
        }
        else{
            //go to a node where it tells the user that they cannot roll
            gameLogicInteractor.transverseCurrentTree(2);
            currentState = gameLogicInteractor.handleTree(0);
        }
        return currentState;
    }
}