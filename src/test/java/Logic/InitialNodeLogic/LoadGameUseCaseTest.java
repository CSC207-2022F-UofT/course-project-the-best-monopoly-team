package Logic.InitialNodeLogic;

import Entities.State;
import Persistence.LoadFile;
import Persistence.SaveFile;
import UseCases.UseCaseInteractor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

public class LoadGameUseCaseTest {

    @Test
    public void testLoadGameUseCaseCreateState(){
        InitialLogic initialLogic = new InitialLogic("Load Game");
        LoadFile loadFile = new LoadFile(new File("src/gameData/"));
        SaveFile saveFile = new SaveFile(new File("src/gameData/"));
        UseCaseInteractor useCaseInteractor = new UseCaseInteractor(loadFile, saveFile);
        LoadGameUseCase loadGameUseCase = new LoadGameUseCase();
        State actual = loadGameUseCase.create_state(0);
        ArrayList<String> options = new ArrayList<>();
        options.add("input_test.txt");
        options.add("test.txt");
        options.add("test01.txt");
        Assertions.assertEquals(actual.getOptions(), options);
        Assertions.assertEquals(actual.getId(), "Initial Menu Parent Node");
    }

}
