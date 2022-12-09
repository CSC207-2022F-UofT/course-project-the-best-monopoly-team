package Logic.MainTreeLogic;

import Entities.State;
import Logic.MainTreeNodeLogic.AlreadyRolledUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class AlreadyRolledUseCaseTest {

    @Test
    public void testAlreadyRolledCreateState(){
        AlreadyRolledUseCase alreadyRolledUseCase = new AlreadyRolledUseCase();
        State actual = alreadyRolledUseCase.create_state(0);
        ArrayList<String> options = new ArrayList<>();
        options.add("Ok");
        Assertions.assertEquals(actual.getOptions(), options);
        Assertions.assertEquals(actual.getId(), "Already Rolled");
    }

}
