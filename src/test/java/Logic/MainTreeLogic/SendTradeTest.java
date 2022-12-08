package Logic.MainTreeLogic;


import Entities.*;
import Interactors.GameLogic;
import Logic.GeneralGameLogic;
import Logic.MainTreeNodeLogic.SendTrade;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class SendTradeTest {

    @Test
    public void testSendTradeCreateStateSent(){
        Player playerOne = new Player("Player One");
        Player playerTwo = new Player("Player Two");
        List<Player> players = new ArrayList<>();
        players.add(playerOne);
        players.add(playerTwo);
        Property propertyOne = new Property("Property One", "Blue", 100, 100, new int[6],
                playerOne, 50, 0, false);
        Property propertyTwo = new Property("Property Two", "Blue", 100, 100, new int[6],
                playerTwo, 50, 0, false);
        playerOne.addProperty(propertyOne);
        playerTwo.addProperty(propertyTwo);
        List<Cell> cells = new ArrayList<>();
        cells.add(propertyOne);
        cells.add(propertyTwo);
        Board board = new Board(players, cells);
        GameLogic gameLogic = new GameLogic(playerOne, board);
        GeneralGameLogic generalGameLogic = new GeneralGameLogic();
        generalGameLogic.setGameLogicInteractor(gameLogic);
        generalGameLogic.getSelectedOptions().put("PickPlayer", 1);
        generalGameLogic.setCurrentPlayer(playerOne);
        generalGameLogic.getSelectedOptions().put("PickItemSelf", 0);
        generalGameLogic.getSelectedOptions().put("PickItemOp", 0);
        SendTrade sendTrade = new SendTrade();
        State actual = sendTrade.create_state(0);
        ArrayList<String> options = new ArrayList<String>();
        options.add("Accept The Trade");
        options.add("Decline The Trade");
        Assertions.assertEquals(actual.getOptions(), options);
        Assertions.assertEquals(actual.getCurrentPlayerProperty(), propertyOne);
        Assertions.assertEquals(actual.getTradingPlayerProperty(), propertyTwo);
        Assertions.assertEquals(actual.getPlayer(), playerOne);
        Assertions.assertEquals(actual.getTradingOpponent(), playerTwo);

    }

    @Test
    public void testSendTradeCreateStateNotSent(){
        Player playerOne = new Player("Player One");
        Player playerTwo = new Player("Player Two");
        List<Player> players = new ArrayList<>();
        players.add(playerOne);
        players.add(playerTwo);
        Property propertyOne = new Property("Property One", "Blue", 100, 100, new int[6],
                playerOne, 50, 0, false);
        Property propertyTwo = new Property("Property Two", "Blue", 100, 100, new int[6],
                playerTwo, 50, 0, false);
        playerOne.addProperty(propertyOne);
        playerTwo.addProperty(propertyTwo);
        List<Cell> cells = new ArrayList<>();
        cells.add(propertyOne);
        cells.add(propertyTwo);
        Board board = new Board(players, cells);
        GameLogic gameLogic = new GameLogic(playerOne, board);
        SendTrade sendTrade = new SendTrade();
        State actual = sendTrade.create_state(1);
        Assertions.assertEquals(actual.getId(), "Main Tree Parent Node");
    }

}
