package Logic.InitialNodeLogic;

import Entities.State;
import Persistence.LoadFile;
import Persistence.SaveFile;
import UseCases.UseCaseInteractor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

public class InitialLogicTest {

    @Test
    public void testBeforeLogic(){
        new UseCaseInteractor(new LoadFile
                (new File("src/gameData/test.txt")), new SaveFile(new File("src/gameData/test.txt")));
        InitialLogic initialLogic = new InitialLogic("");
        State actual = initialLogic.beforeLogic();
        Assertions.assertEquals(actual.getId(), "Initial Menu Parent Node");
    }

    @Test
    public void testAfterLogic(){
        new UseCaseInteractor(new LoadFile
                (new File("src/gameData/test.txt")), new SaveFile(new File("src/gameData/test.txt")));
        InitialLogic initialLogic = new InitialLogic("");
        State before = initialLogic.beforeLogic();
        State actual = initialLogic.afterLogic(before);
        Assertions.assertEquals(actual.getId(), "Initial Menu Parent Node");
    }

}
