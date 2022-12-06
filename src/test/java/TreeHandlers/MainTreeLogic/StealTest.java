package TreeHandlers.MainTreeLogic;

import Entities.*;
import Interactors.GameLogic;
import TreeHandlers.GeneralGameLogic;
import TreeHandlers.MainTreeNodeLogic.Steal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class StealTest {

    @Test
    public void testStealCreateState(){
        Player playerOne = new Player("Player One");
        Player playerTwo = new Player("Player Two");
        List<Player> players = new ArrayList<>();
        players.add(playerOne);
        players.add(playerTwo);
        List<Cell> cells = new ArrayList<>();
        Board board = new Board(players, cells);
        GameLogic gameLogic = new GameLogic(playerOne, board);
        Steal steal = new Steal();
        State actual = steal.create_state(0);
        ArrayList<String> options = new ArrayList<String>();
        options.add("Player Two");
        Assertions.assertEquals(actual.isBackEnable(), true);
        Assertions.assertEquals(actual.getId(), "Steal");
        Assertions.assertEquals(actual.getOptions(), options);
    }

}
