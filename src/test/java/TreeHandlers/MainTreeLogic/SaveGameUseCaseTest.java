package TreeHandlers.MainTreeLogic;

import Entities.*;
import Interactors.GameLogic;
import TreeHandlers.MainTreeNodeLogic.SaveGameUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class SaveGameUseCaseTest {

    @Test
    public void testSaveGameUseCaseCreateState(){
        SaveGameUseCase saveGameUseCase = new SaveGameUseCase();
        State actual = saveGameUseCase.create_state(0);
        ArrayList<String> options = new ArrayList<>();
        options.add("Ok");
        Assertions.assertEquals(actual.getId(), "Save The Game");
        Assertions.assertEquals(actual.isSaveGame(), true);
        Assertions.assertEquals(actual.getOptions(), options);
    }

}
