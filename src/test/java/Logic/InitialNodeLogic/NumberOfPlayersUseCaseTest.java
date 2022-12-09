package Logic.InitialNodeLogic;

import Entities.State;
import Persistence.LoadFile;
import Persistence.SaveFile;
import UseCases.UseCaseInteractor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

public class NumberOfPlayersUseCaseTest {

    @Test
    public void testNumberOfPlayerUseCaseCreateState(){
        new InitialLogic("Number of Players");
        new UseCaseInteractor(new LoadFile
                (new File("src/gameData/test.txt")), new SaveFile(new File("src/gameData/test.txt")));
        NumberOfPlayersUseCase numberOfPlayersUseCase = new NumberOfPlayersUseCase();
        State actual = numberOfPlayersUseCase.create_state(0);
        ArrayList<String> options = new ArrayList<>();
        options.add("30 rounds");
        options.add("60 rounds");
        options.add("90 rounds");
        options.add("no limit");
        Assertions.assertEquals(actual.getId(), "Initial Menu Parent Node");
        Assertions.assertTrue(actual.isBackEnable());
        Assertions.assertEquals(actual.getOptions(), options);
    }

}
