package Logic.MainTreeLogic;

import Entities.*;
import Logic.GameLogic;
import Logic.MainTreeNodeLogic.NothingToTradeUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class NothingToTradeUseCaseTest {

    @Test
    public void testNothingToTestCreateState(){
        Player playerOne = new Player("Player One");
        List<Player> players = new ArrayList<>();
        players.add(playerOne);
        Property test_property = new Property("Name", "Blue", 100, 100, new int[6],
                null, 50, 0, false);
        List<Cell> cells = new ArrayList<>();
        cells.add(test_property);
        Board board = new Board(players, cells);
        new GameLogic(playerOne, board);
        NothingToTradeUseCase nothingToTradeUseCase = new NothingToTradeUseCase();
        State actual = nothingToTradeUseCase.create_state(0);
        ArrayList<String> options = new ArrayList<>();
        options.add("Ok");
        Assertions.assertEquals(actual.getId(), "Trader/Tradee Has No Properties");
        Assertions.assertEquals(actual.getOptions(), options);
    }

}
