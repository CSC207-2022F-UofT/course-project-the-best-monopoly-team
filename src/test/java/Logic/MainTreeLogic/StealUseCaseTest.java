package Logic.MainTreeLogic;

import Entities.*;
import Logic.GameLogic;
import Logic.MainTreeNodeLogic.StealUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class StealUseCaseTest {

    @Test
    public void testStealCreateState(){
        Player playerOne = new Player("Player One");
        Player playerTwo = new Player("Player Two");
        List<Player> players = new ArrayList<>();
        players.add(playerOne);
        players.add(playerTwo);
        List<Cell> cells = new ArrayList<>();
        Board board = new Board(players, cells);
        new GameLogic(playerOne, board);
        StealUseCase stealUseCase = new StealUseCase();
        State actual = stealUseCase.create_state(0);
        ArrayList<String> options = new ArrayList<>();
        options.add("Player Two");
        Assertions.assertTrue(actual.isBackEnable());
        Assertions.assertEquals(actual.getId(), "Steal");
        Assertions.assertEquals(actual.getOptions(), options);
    }

}
