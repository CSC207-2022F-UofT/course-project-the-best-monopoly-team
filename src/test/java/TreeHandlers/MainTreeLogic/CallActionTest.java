package TreeHandlers.MainTreeLogic;

import Entities.*;
import Interactors.GameLogic;
import TreeHandlers.GeneralGameLogic;
import TreeHandlers.MainTreeNodeLogic.CallAction;
import TreeHandlers.MainTreeNodeLogic.MainTreeNodeLogic;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CallActionTest {

    @Test
    public void testCallActionCreateState(){
        Player playerOne = new Player("Player One");
        List<Player> players = new ArrayList<>();
        players.add(playerOne);
        Property test_property = new Property("Name", "Blue", 100, 100, new int[5],
                null, 50, 0, false);
        List<Cell> cells = new ArrayList<>();
        cells.add(test_property);
        Board board = new Board(players, cells);
        GeneralGameLogic generalGameLogic = new GeneralGameLogic();
        CallAction callAction = new CallAction();
        State actual = callAction.create_state(0);
        ArrayList<String> options = new ArrayList<String>();
        options.add("Ok");
        Assertions.assertEquals(actual.getOptions(), options);
        Assertions.assertEquals(actual.getDescription(), generalGameLogic.getAnswer());
    }

}
