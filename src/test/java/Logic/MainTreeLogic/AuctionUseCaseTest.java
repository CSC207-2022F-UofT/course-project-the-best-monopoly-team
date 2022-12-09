package Logic.MainTreeLogic;

import Entities.*;
import Logic.GameLogic;
import Logic.MainTreeNodeLogic.AuctionUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class AuctionUseCaseTest {

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
        AuctionUseCase auctionUseCase = new AuctionUseCase();
        State actual = auctionUseCase.create_state(0);
        Assertions.assertEquals(actual.getId(), "Auction Tree Parent Node");
    }

}
