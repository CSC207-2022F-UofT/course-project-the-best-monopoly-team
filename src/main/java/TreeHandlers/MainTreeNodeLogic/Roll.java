package TreeHandlers.MainTreeNodeLogic;

import Entities.*;
import Interactors.GameLogic;
import Interface.NodeLogic;

/**
 * This class represents the use case where the current player rolls the dice.
 */
public class Roll extends MainTreeNodeLogic implements NodeLogic {

    /**
     * This method creates a State object and conducts the appropriate actions related to the current state of the game,
     * and sets up the tree objects that are required due to the movement of the game.
     * @param input An integer that represents the input of the user. However, this will not be used in this method.
     * @return A State object representing the state of the game after the current player rolls the dice.
     */
    @Override
    public State create_state(int input) {
        Board board = getBoard();
        GameLogic gameLogicInteractor = getGameLogicInteractor();
        Player currentPlayer = getCurrentPlayer();

        State currentState = new State();
        currentState.setId(gameLogicInteractor.getCurrentTree().getName());
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
                setAnswer(landedOnCell.performAction(currentPlayer,board));
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