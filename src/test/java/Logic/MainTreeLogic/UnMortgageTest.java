package Logic.MainTreeLogic;

import Entities.*;
import Logic.GameLogic;
import Logic.GeneralGameLogic;
import Logic.MainTreeNodeLogic.UnMortgageUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class UnMortgageTest {

    @Test
    public void testUnMortgageCreateState(){
        Player playerOne = new Player("Player One");
        List<Player> players = new ArrayList<>();
        players.add(playerOne);
        Property propertyOne = new Property("Property One", "Blue", 100, 100, new int[6],
                playerOne, 50, 0, false);
        playerOne.addProperty(propertyOne);
        List<Cell> cells = new ArrayList<>();
        cells.add(propertyOne);
        Board board = new Board(players, cells);
        GameLogic gameLogic = new GameLogic(playerOne, board);
        GeneralGameLogic generalGameLogic = new GeneralGameLogic();
        GeneralGameLogic.setGameLogicInteractor(gameLogic);
        GeneralGameLogic.setCurrentPlayer(playerOne);
        generalGameLogic.getSelectedOptions().put("PropertySelected", 0);
        UnMortgageUseCase unMortgage = new UnMortgageUseCase();
        State actual = unMortgage.create_state(0);
        Assertions.assertEquals(actual.getId(), "Main Tree Parent Node");
    }

}
