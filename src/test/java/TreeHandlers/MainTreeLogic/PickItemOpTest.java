package TreeHandlers.MainTreeLogic;

import Entities.*;
import Interactors.GameLogic;
import TreeHandlers.MainTreeNodeLogic.PickItemOp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class PickItemOpTest {

    @Test
    public void testPickItemOpCreateState(){
        Player playerOne = new Player("Player One");
        Player playerTwo = new Player("Player Two");
        List<Player> players = new ArrayList<>();
        players.add(playerOne);
        Property test_property1 = new Property("Property One", "Blue", 100, 100, new int[5],
                playerOne, 50, 0, false);
        Property test_property2 = new Property("Property One", "Blue", 100, 100, new int[5],
                playerTwo, 50, 0, false);
        playerOne.addProperty(test_property1);
        playerTwo.addProperty(test_property2);
        List<Cell> cells = new ArrayList<>();
        cells.add(test_property1);
        cells.add(test_property2);
        Board board = new Board(players, cells);
        GameLogic gameLogic = new GameLogic(playerOne, board);
        PickItemOp pickItemOp = new PickItemOp();
        State actual = pickItemOp.create_state(0);
        ArrayList<String> options = new ArrayList<String>();
        options.add("Property One");
        Assertions.assertEquals(actual.isBackEnable(), true);
        Assertions.assertEquals(actual.getId(), "Pick Item Of Opponent");
        Assertions.assertEquals(actual.getOptions(), options);
    }

}
