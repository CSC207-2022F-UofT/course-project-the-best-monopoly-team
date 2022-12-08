package Logic.AuctionNodeLogic;

import Entities.*;
import UseCases.Logic.AuctionNodeLogic.HighOptionUseCase;
import UseCases.Logic.GameLogic;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class HighOptionUseCaseTest {

    @Test
    public void testHighOptionCreateState(){
        Player playerOne = new Player("Player One");
        Player playerTwo = new Player("Player Two");
        List<Player> players = new ArrayList<>();
        players.add(playerOne);
        Property test_property1 = new Property("Property One", "Blue", 100, 100, new int[6],
                playerOne, 50, 0, false);
        Property test_property2 = new Property("Property One", "Blue", 100, 100, new int[6],
                null, 50, 0, false);
        playerOne.addProperty(test_property1);
        List<Cell> cells = new ArrayList<>();
        cells.add(test_property1);
        cells.add(test_property2);
        Board board = new Board(players, cells);
        GameLogic gameLogic = new GameLogic(playerOne, board);
        HighOptionUseCase highOptionUseCase = new HighOptionUseCase();
        State actual = highOptionUseCase.create_state(0);
        Assertions.assertEquals(actual.getId(), "Main Tree Parent Node");
    }

}
