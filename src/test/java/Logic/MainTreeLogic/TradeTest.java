package Logic.MainTreeLogic;

import Entities.*;
import Logic.GameLogic;
import Logic.MainTreeNodeLogic.Trade;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TradeTest {

    @Test
    public void testTradeCreateState(){
        Player playerOne = new Player("Player One");
        Player playerTwo = new Player("Player Two");
        List<Player> players = new ArrayList<>();
        players.add(playerOne);
        players.add(playerTwo);
        List<Cell> cells = new ArrayList<>();
        Board board = new Board(players, cells);
        GameLogic gameLogic = new GameLogic(playerOne, board);
        Trade trade = new Trade();
        State actual = trade.create_state(0);
        ArrayList<String> options = new ArrayList<String>();
        options.add("Player Two");
        Assertions.assertEquals(actual.getId(), "Trade");
        Assertions.assertEquals(actual.isBackEnable(), true);
        Assert.assertEquals(actual.getOptions(), options);
    }

}
