package Logic.MainTreeLogic;

import Entities.State;
import Logic.MainTreeNodeLogic.NoPropertiesUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class NoPropertiesUseCaseTest {

    @Test
    public void testNoPropertiesCreateCase(){
        NoPropertiesUseCase noPropertiesUseCase = new NoPropertiesUseCase();
        State actual = noPropertiesUseCase.create_state(0);
        ArrayList<String> options = new ArrayList<String>();
        options.add("Ok");
        Assertions.assertEquals(actual.getId(), "User Has No Properties (Manage Properties)");
        Assertions.assertEquals(actual.getOptions(), options);
    }

}
