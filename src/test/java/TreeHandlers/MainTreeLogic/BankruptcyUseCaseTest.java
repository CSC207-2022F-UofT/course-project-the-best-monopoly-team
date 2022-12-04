package TreeHandlers.MainTreeLogic;

import Entities.*;
import Interactors.GameLogic;
import TreeHandlers.MainTreeNodeLogic.BankruptcyUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class BankruptcyUseCaseTest {

    @Test
    public void testBankruptcyUseCaseCreateStateRemove(){
        Player playerOne = new Player("Player One");
        List<Player> players = new ArrayList<>();
        players.add(playerOne);
        Property test_property = new Property("Name", "Blue", 100, 100, new int[5],
                null, 50, 0, false);
        List<Cell> cells = new ArrayList<>();
        cells.add(test_property);
        Board board = new Board(players, cells);
        BankruptcyUseCase bankruptcyUseCase = new BankruptcyUseCase();
        State actual = bankruptcyUseCase.create_state(0);
        Assertions.assertEquals(actual.getId(), "Bankruptcy");
    }

    @Test
    public void testBankruptcyUseCaseCreateStateConfirmation(){
        Player playerOne = new Player("Player One");
        List<Player> players = new ArrayList<>();
        players.add(playerOne);
        Property test_property = new Property("Name", "Blue", 100, 100, new int[5],
                null, 50, 0, false);
        List<Cell> cells = new ArrayList<>();
        cells.add(test_property);
        Board board = new Board(players, cells);
        GameLogic gameLogic = new GameLogic(playerOne, board);
        BankruptcyUseCase bankruptcyUseCase = new BankruptcyUseCase();
        State actual = bankruptcyUseCase.create_state(0);
        ArrayList<String> options = new ArrayList<String>();
        options.add("Yes");
        options.add("No");
        Assertions.assertEquals(actual.getId(), "Bankruptcy");
        Assertions.assertEquals(actual.getOptions(), options);
    }

}
