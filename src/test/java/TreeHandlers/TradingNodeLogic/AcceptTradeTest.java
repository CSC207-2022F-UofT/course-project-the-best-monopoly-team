package TreeHandlers.TradingNodeLogic;

import Entities.*;
import Interactors.GameLogic;
import TreeHandlers.GeneralGameLogic;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class AcceptTradeTest {

    @Test
    public void testAcceptTradeCreateState(){

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
        generalGameLogic.setReturnPlayerIndex(0);
        generalGameLogic.setCurrentPlayer(playerTwo);
        generalGameLogic.getSelectedOptions().put("PickItemOp", 0);
        generalGameLogic.getSelectedOptions().put("PickItemSelf", 0);
        AcceptTrade acceptTrade = new AcceptTrade();
        State actual = acceptTrade.create_state(0);
        ArrayList<String> options = new ArrayList<String>();
        options.add("Ok");
        Assertions.assertEquals(actual.getId(), "Accept The Trade");
        Assertions.assertEquals(actual.getOptions(), options);
    }

}
