package UseCases;

import java.io.File;
import java.io.IOException;

import Entities.ActionSpace;

public interface    ActionSpaceCreationUseCase {

    ActionSpace loadJailCards(File file) throws IOException;
    ActionSpace loadChanceCards(File file) throws IOException;
    ActionSpace loadComChestCards(File file) throws IOException;

}
