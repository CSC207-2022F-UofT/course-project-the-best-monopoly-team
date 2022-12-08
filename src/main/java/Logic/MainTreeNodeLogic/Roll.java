package NodeLogic.MainTreeNodeLogic;

import Entities.*;
import Interactors.CornerTilePerformActionInteractor;
import Interactors.GameLogic;
import Interactors.PerformActionSpaceCardInteractor;
import Interactors.PropertyPerformActionInteractor;
import UseCases.NodeLogic;
import UseCases.CornerTilePerformActionUseCase;
import UseCases.PerformActionSpaceUseCase;
import UseCases.PlayerLogic;
import UseCases.PropertyPerformActionUseCase;

/**
 * This class represents the use case where the current player rolls the dice.
 */
public class Roll extends MainTreeNodeLogic implements NodeLogic {

    public Roll() {
        super("Roll The Dice");
    }

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
        currentState.setId(getName());
        if (mainStates[1] == 0) {
            //roll the dice and update the position
            PlayerLogic playerLogic = new PlayerLogic(currentPlayer);
            diceRoll = playerLogic.rollDice(0);

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
                switch (landedOnCell.getType()) {
                    case "Property":
                        PropertyPerformActionUseCase propertyInteractor = new PropertyPerformActionInteractor();
                        Property property = (Property) landedOnCell;
                        if (property.getMortgageStatus()) {
                            setAnswer("This property is mortgaged, don't need to pay rent.");
                        } else {
                            setAnswer(propertyInteractor.performAction(property, currentPlayer));
                        }
                        break;
                    case "Corner Tile":
                        CornerTilePerformActionUseCase cornerTileInteractor = new CornerTilePerformActionInteractor();
                        assert landedOnCell instanceof CornerTiles;
                        CornerTiles cornerTile = (CornerTiles) landedOnCell;

                        setAnswer(cornerTileInteractor.performAction(currentPlayer, cornerTile));
                        break;
                    case "Action Space":
                        PerformActionSpaceUseCase actionSpaceInteractor = new PerformActionSpaceCardInteractor();
                        ActionSpace actionSpace = (ActionSpace) landedOnCell;
                        setAnswer(actionSpaceInteractor.performAction(actionSpace, currentPlayer, board));

                        if (currentPlayer.isInJail()) {
                            ActionSpace jail = (ActionSpace) landedOnCell;
                            setAnswer(actionSpaceInteractor.performAction(jail, currentPlayer, board));
                        }

                }

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