package TreeHandlers.MainTreeLogic;

import Entities.Board;
import Entities.Cell;
import Entities.Player;
import Entities.State;
import Interactors.GameLogic;
import TreeHandlers.GeneralGameLogic;
import TreeHandlers.MainTreeNodeLogic.AlreadyRolled;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class AlreadyRolledTest {

    @Test
    public void testAlreadyRolledCreateState(){
        AlreadyRolled alreadyRolled = new AlreadyRolled();
        State actual = alreadyRolled.create_state(0);
        ArrayList<String> options = new ArrayList<String>();
        options.add("Ok");
        Assertions.assertEquals(actual.getOptions(), options);
        Assertions.assertEquals(actual.getId(), "Already Rolled");
    }

}
