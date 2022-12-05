package UseCases;

import java.io.File;
import java.io.IOException;

import Entities.ActionSpace;

public interface    ActionSpaceCreationUseCase {

    ActionSpace loadJailCards() throws IOException;
    ActionSpace loadChanceCards() throws IOException;
    ActionSpace loadComChestCards() throws IOException;

}
