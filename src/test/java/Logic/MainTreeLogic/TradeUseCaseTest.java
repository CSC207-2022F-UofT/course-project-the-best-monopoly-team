package Logic.MainTreeLogic;

import Entities.*;
import Logic.GameLogic;
import Logic.MainTreeNodeLogic.TradeUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TradeUseCaseTest {

    @Test
    public void testTradeCreateState(){
        Player playerOne = new Player("Player One");
        Player playerTwo = new Player("Player Two");
        List<Player> players = new ArrayList<>();
        players.add(playerOne);
        players.add(playerTwo);
        List<Cell> cells = new ArrayList<>();
        Board board = new Board(players, cells);
        new GameLogic(playerOne, board);
        TradeUseCase tradeUseCase = new TradeUseCase();
        State actual = tradeUseCase.create_state(0);
        ArrayList<String> options = new ArrayList<>();
        options.add("Player Two");
        Assertions.assertEquals(actual.getId(), "Trade");
        Assertions.assertTrue(actual.isBackEnable());
        Assertions.assertEquals(actual.getOptions(), options);
    }

}
