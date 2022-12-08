package Logic.TradingNodeLogic;

import Entities.*;
import Interactors.GameLogic;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TradingParentNodeTest {

    @Test
    public void testTradingParentNodeCreateState(){
        Player playerOne = new Player("Player One");
        List<Player> players = new ArrayList<>();
        players.add(playerOne);
        List<Cell> cells = new ArrayList<>();
        Board board = new Board(players, cells);
        GameLogic gameLogic = new GameLogic(playerOne, board);
        TradingParentNode tradingParentNode = new TradingParentNode();
        State actual = tradingParentNode.create_state(0);
        ArrayList<String> options = new ArrayList<String>();
        options.add("Trade");
        options.add("Manage Property");
        options.add("Roll The Dice");
        options.add("Steal");
        options.add("End Turn");
        options.add("Settings Menu");
        options.add("Bankruptcy");
        Assertions.assertEquals(actual.getId(), "Trading Parent Node");
        Assertions.assertEquals(actual.getOptions(), options);
    }

}
