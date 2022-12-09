package Logic.MainTreeLogic;

import Entities.*;
import Logic.GameLogic;
import Logic.MainTreeNodeLogic.MainParentNodeUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class MainParentNodeUseCaseTest {

    @Test
    public void testMainTreeParentNodeCreateState(){
        Player playerOne = new Player("Player One");
        Player playerTwo = new Player("Player Two");
        List<Player> players = new ArrayList<>();
        players.add(playerOne);
        players.add(playerTwo);
        Property test_property = new Property("Name", "Blue", 100, 100, new int[6],
                null, 50, 0, false);
        List<Cell> cells = new ArrayList<>();
        cells.add(test_property);
        Board board = new Board(players, cells);
        new GameLogic(playerOne, board);
        MainParentNodeUseCase mainParentNodeUseCase = new MainParentNodeUseCase();
        State actual = mainParentNodeUseCase.create_state(0);
        ArrayList<String> options = new ArrayList<>();
        options.add("Trade");
        options.add("Manage Property");
        options.add("Roll The Dice");
        options.add("Steal");
        options.add("End Turn");
        options.add("Settings Menu");
        options.add("Bankruptcy");
        Assertions.assertEquals(actual.getId(), "Main Tree Parent Node");
        Assertions.assertEquals(actual.getPlayer(), playerOne);
        Assertions.assertEquals(actual.getOptions(), options);
    }

}
