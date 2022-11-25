package UseCases;

import java.io.File;
import java.io.IOException;

import Entities.ActionSpace2;

public interface    ActionSpaceCreationUseCase {

    ActionSpace2 loadJailCards(File file) throws IOException;
    ActionSpace2 loadChanceCards(File file) throws IOException;
    ActionSpace2 loadComChestCards(File file) throws IOException;

}
