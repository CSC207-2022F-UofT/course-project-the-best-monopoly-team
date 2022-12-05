package TreeHandlers.MainTreeLogic;

import Entities.*;
import Interactors.GameLogic;
import TreeHandlers.GeneralGameLogic;
import TreeHandlers.MainTreeNodeLogic.MainParentNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class MainParentNodeTest {

    @Test
    public void testMainTreeParentNodeCreateState(){
        Player playerOne = new Player("Player One");
        Player playerTwo = new Player("Player Two");
        List<Player> players = new ArrayList<>();
        players.add(playerOne);
        players.add(playerTwo);
        Property test_property = new Property("Name", "Blue", 100, 100, new int[5],
                null, 50, 0, false);
        List<Cell> cells = new ArrayList<>();
        cells.add(test_property);
        Board board = new Board(players, cells);
        GameLogic gameLogic = new GameLogic(playerOne, board);
        MainParentNode mainParentNode = new MainParentNode();
        State actual = mainParentNode.create_state(0);
        ArrayList<String> options = new ArrayList<String>();
        options.add("Trade");
        options.add("Manage Property");
        options.add("Roll The Dice");
        options.add("Steal");
        options.add("End Turn");
        options.add("Settings Menu");
        options.add("Bankruptcy");
        Assertions.assertEquals(actual.getId(), "Main Tree Parent Node");
        Assertions.assertEquals(actual.getPlayer(), playerOne);
        Assertions.assertEquals(actual.getDescription(), "Player One It's your turn! What do you want to do?" +
                " You currently have 1500 dollars");
        Assertions.assertEquals(actual.getOptions(), options);
    }

}