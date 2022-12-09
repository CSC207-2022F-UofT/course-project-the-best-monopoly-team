package Logic.InitialNodeLogic;

import Entities.State;
import Persistence.LoadFile;
import Persistence.SaveFile;
import UseCases.UseCaseInteractor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

public class CreateNewGameUseCaseTest {

    @Test
    public void testCreateNewGameCreateStateConfirmed(){
        new UseCaseInteractor(new LoadFile
                (new File("src/gameData/test.txt")), new SaveFile(new File("src/gameData/test.txt")));
        InitialLogic initialLogic = new InitialLogic("Initial Menu Parent Node");
        initialLogic.getSelectedOptions().put("NumOfPlayers", 2);
        initialLogic.getSelectedOptions().put("GameLength", 2);
        initialLogic.getSelectedOptions().put("GameMode", 0);
        CreateNewGameUseCase createNewGameUseCase = new CreateNewGameUseCase();
        State actual = createNewGameUseCase.create_state(0);
        Assertions.assertEquals(actual.getId(), "Main Tree Parent Node");
    }

    @Test
    public void testCreateNewGameCreateStateNotConfirmed() {
        new UseCaseInteractor(new LoadFile(new File("src/gameData/test.txt")),
                new SaveFile(new File("src/gameData/test.txt")));
        new InitialLogic("Initial Menu Parent Node");
        CreateNewGameUseCase createNewGameUseCase = new CreateNewGameUseCase();
        State actual = createNewGameUseCase.create_state(1);
        Assertions.assertEquals(actual.getId(), "Initial Menu Parent Node");
    }
}
