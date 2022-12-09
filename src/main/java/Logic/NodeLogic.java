package Logic;

import Entities.State;

/**
 * An interface for the different use cases to create a State object containing relevant information to a specific use
 * case.
 */
public interface NodeLogic {

    State create_state(int input);

}
