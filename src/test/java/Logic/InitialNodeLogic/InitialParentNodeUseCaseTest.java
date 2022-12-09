package Logic.InitialNodeLogic;

import Entities.State;
import Persistence.LoadFile;
import Persistence.SaveFile;
import UseCases.UseCaseInteractor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

public class InitialParentNodeUseCaseTest {

    @Test
    public void testInitialParentUseCaseCreateState(){
        new InitialLogic("Initial Menu Parent Node");
        new UseCaseInteractor(new LoadFile
                (new File("src/gameData/test.txt")), new SaveFile(new File("src/gameData/test.txt")));
        InitialParentNodeUseCase initialParentNodeUseCase = new InitialParentNodeUseCase();
        State actual = initialParentNodeUseCase.create_state(0);
        ArrayList<String> options = new ArrayList<>();
        options.add("New Game");
        options.add("Load Game");
        Assertions.assertEquals(actual.getId(), "Initial Menu Parent Node");
        Assertions.assertEquals(actual.getOptions(), options);
    }

}
