package Logic.InitialNodeLogic;

import Entities.State;
import Persistence.LoadFile;
import Persistence.SaveFile;
import UseCases.UseCaseInteractor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

public class GameLengthUseCaseTest {

    @Test
    public void testGameLengthUseCaseCreateState(){
        InitialLogic initialLogic = new InitialLogic("Game Length");
        initialLogic.getSelectedOptions().put("NumOfPlayers", 2);
        new UseCaseInteractor(new LoadFile
                (new File("src/gameData/test.txt")), new SaveFile(new File("src/gameData/test.txt")));
        GameLengthUseCase gameLengthUseCase = new GameLengthUseCase();
        State actual = gameLengthUseCase.create_state(0);
        ArrayList<String> options = new ArrayList<>();
        options.add("Yes");
        options.add("No");
        Assertions.assertEquals(actual.getOptions(), options);
        Assertions.assertTrue(actual.isBackEnable());
        Assertions.assertEquals(actual.getId(), "Initial Menu Parent Node");
    }

}
