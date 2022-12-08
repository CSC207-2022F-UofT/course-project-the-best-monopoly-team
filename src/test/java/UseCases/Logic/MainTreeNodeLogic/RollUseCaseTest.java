package UseCases.Logic.MainTreeNodeLogic;

import Entities.State;
import Persistence.LoadAccess;
import Persistence.LoadFile;
import Persistence.SaveAccess;
import Persistence.SaveFile;
import UseCases.UseCaseInteractor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;


class RollUseCaseTest {
    @Test
    public void testSaveGameUseCaseCreateState(){
        LoadAccess game = new LoadFile(new File("src/gameData/"));
        SaveAccess save = new SaveFile(new File("src/gameData/"));
        UseCaseInteractor useCaseTest = new UseCaseInteractor(game, save);
        int[] validStates = {4,-1,0,0,0,0};
        useCaseTest.createNewGame(validStates);

        // getting into correct branch of current tree
        useCaseTest.getLogicInteractor().transverseCurrentTree(2);
        RollUseCase rollUseCase = new RollUseCase();
        State actual = rollUseCase.create_state(0);
        Assertions.assertNotNull(actual.getRoll());
    }
}