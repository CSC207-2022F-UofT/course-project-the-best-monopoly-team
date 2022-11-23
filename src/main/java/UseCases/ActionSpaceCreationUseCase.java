package UseCases;

import java.io.IOException;

import Entities.ActionSpace2;

public interface ActionSpaceCreationUseCase {
    
    ActionSpace2 loadCards() throws IOException;

}
