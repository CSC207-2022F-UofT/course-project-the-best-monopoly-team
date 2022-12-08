package Logic.MainTreeLogic;

import Entities.*;
import Interactors.GameLogic;
import Logic.MainTreeNodeLogic.Buy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class BuyTest {

    @Test
    public void testBuyCreateState(){
        Player playerOne = new Player("Player One");
        List<Player> players = new ArrayList<>();
        players.add(playerOne);
        Property test_property = new Property("Name", "Blue", 100, 100, new int[6],
                null, 50, 0, false);
        List<Cell> cells = new ArrayList<>();
        cells.add(test_property);
        Board board = new Board(players, cells);
        GameLogic gameLogic = new GameLogic(playerOne, board);
        Buy buy = new Buy();
        State actual = buy.create_state(0);
        Assertions.assertEquals(actual.getId(), "Main Tree Parent Node");
    }
}
