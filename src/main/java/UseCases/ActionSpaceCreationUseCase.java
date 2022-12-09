package UseCases;

import java.io.IOException;

import Entities.ActionSpace;

/**
 * Interface for the action space creation interactor
 */
public interface    ActionSpaceCreationUseCase {

    ActionSpace loadJailCards() throws IOException;
    ActionSpace loadChanceCards() throws IOException;
    ActionSpace loadComChestCards() throws IOException;

}
