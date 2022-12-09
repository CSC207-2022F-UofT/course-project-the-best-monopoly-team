package Logic.MainTreeLogic;

import Entities.*;
import Logic.GeneralGameLogic;
import Logic.MainTreeNodeLogic.CallActionUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class CallActionUseCaseTest {

    @Test
    public void testCallActionCreateState(){
        GeneralGameLogic generalGameLogic = new GeneralGameLogic();
        CallActionUseCase callActionUseCase = new CallActionUseCase();
        State actual = callActionUseCase.create_state(0);
        ArrayList<String> options = new ArrayList<>();
        options.add("Ok");
        Assertions.assertEquals(actual.getOptions(), options);
        Assertions.assertEquals(actual.getDescription(), generalGameLogic.getAnswer());
    }

}
