package TreeHandlers.MainTreeNodeLogic;

import Entities.State;
import Interface.NodeLogic;

/**
 * This class represents a use case where a tree path is finished and the game has to tell the user something.
 */
public class InformationUseCase extends MainTreeNodeLogic implements NodeLogic {

    /**
     * This method creates and returns a State object which represents a State object when a path through a tree has
     * been finished.
     * @param input An integer representing the user's input. However, this parameter will not be used for this method.
     * @return A State object indicating that the path in the tree is finished.
     */
    @Override
    public State create_state(int input) {
        return afterBottomNode();
    }
}