package Logic.InitialNodeLogic;

import Entities.State;
import Persistence.LoadFile;
import Persistence.SaveFile;
import UseCases.UseCaseInteractor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

public class ChooseSaveUseCaseTest {

    @Test
    public void testChooseSaveUseCaseCreateState(){
        new InitialLogic("Choose Save");
        new UseCaseInteractor(new LoadFile
                (new File("src/gameData/test.txt")), new SaveFile(new File("src/gameData/test.txt")));
        ChooseSaveUseCase chooseSaveUseCase = new ChooseSaveUseCase();
        State actual = chooseSaveUseCase.create_state(0);
        ArrayList<String> options = new ArrayList<>();
        options.add("Yes");
        options.add("No");
        Assertions.assertEquals(actual.getId(), "Initial Menu Parent Node");
        Assertions.assertEquals(actual.getOptions(), options);
    }

}
