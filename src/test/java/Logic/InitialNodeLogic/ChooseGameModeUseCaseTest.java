package Logic.InitialNodeLogic;

import Entities.State;
import Persistence.LoadFile;
import Persistence.SaveFile;
import UseCases.UseCaseInteractor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

public class ChooseGameModeUseCaseTest {

    @Test
    public void testChooseGameModeUseCaseTest(){

        new InitialLogic("Choose Game Mode");
        new UseCaseInteractor(new LoadFile (new File("src/gameData/test.txt")),
                new SaveFile(new File("src/gameData/test.txt")));
        ChooseGameModeUseCase chooseGameModeUseCase = new ChooseGameModeUseCase();
        State actual = chooseGameModeUseCase.create_state(0);
        ArrayList<String> options = new ArrayList<>();
        options.add("2 players");
        options.add("3 players");
        options.add("4 players");
        options.add("5 players");
        options.add("6 players");
        options.add("7 players");
        options.add("8 players");
        Assertions.assertTrue(actual.isBackEnable());
        Assertions.assertEquals(actual.getId(), "Initial Menu Parent Node");
        Assertions.assertEquals(actual.getOptions(), options);
    }

}
