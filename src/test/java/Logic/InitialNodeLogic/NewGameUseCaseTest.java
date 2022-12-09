package Logic.InitialNodeLogic;

import Entities.State;
import Persistence.LoadFile;
import Persistence.SaveFile;
import UseCases.UseCaseInteractor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

public class NewGameUseCaseTest {

    @Test
    public void testNewGameUseCaseCreateState(){
        new InitialLogic("Initial Menu Parent Node");
        new UseCaseInteractor(new LoadFile
                (new File("src/gameData/test.txt")), new SaveFile(new File("src/gameData/test.txt")));
        NewGameUseCase newGameUseCase = new NewGameUseCase();
        State actual = newGameUseCase.create_state(0);
        ArrayList<String> options = new ArrayList<>();
        options.add("Normal mode");
        options.add("Rich mode");
        Assertions.assertEquals(actual.getId(), "Initial Menu Parent Node");
        Assertions.assertTrue(actual.isBackEnable());
        Assertions.assertEquals(actual.getOptions(), options);
    }

}
