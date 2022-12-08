package NodeLogic.MainTreeLogic;

import Entities.*;
import Interactors.GameLogic;
import NodeLogic.MainTreeNodeLogic.Auction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class AuctionTest {

    @Test
    public void testAuctionTestCreateState(){
        Player playerOne = new Player("Player One");
        List<Player> players = new ArrayList<>();
        players.add(playerOne);
        Property test_property = new Property("Name", "Blue", 100, 100, new int[6],
                null, 50, 0, false);
        List<Cell> cells = new ArrayList<>();
        cells.add(test_property);
        Board board = new Board(players, cells);
        GameLogic gameLogic = new GameLogic(playerOne, board);
        Auction auction = new Auction();
        State actual = auction.create_state(0);
        Assertions.assertEquals(actual.getId(), "Auction Tree Parent Node");
    }

}
