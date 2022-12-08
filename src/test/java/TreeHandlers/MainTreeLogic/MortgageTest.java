package TreeHandlers.MainTreeLogic;

import Entities.*;
import Interactors.GameLogic;
import TreeHandlers.GeneralGameLogic;
import TreeHandlers.MainTreeNodeLogic.MainTreeNodeLogic;
import TreeHandlers.MainTreeNodeLogic.Mortgage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MortgageTest {

    @Test
    public void testMortgageCreateState(){
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
        generalGameLogic.getSelectedOptions().put("PropertySelected", 0);
        int[] main_states = new int[4];
        main_states[0] = 1;
        MainTreeNodeLogic.initializeStates(main_states);
        Mortgage mortgage = new Mortgage();
        State actual = mortgage.create_state(0);
        Assertions.assertEquals(actual.getId(), "Main Tree Parent Node");
    }

    @Test
    public void testMortgageCreateStateConfirm(){
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
        generalGameLogic.getSelectedOptions().put("PropertySelected", 0);
        int[] main_states = new int[4];
        main_states[0] = 0;
        MainTreeNodeLogic.initializeStates(main_states);
        Mortgage mortgage = new Mortgage();
        State actual = mortgage.create_state(0);
        ArrayList<String> options = new ArrayList<String>();
        options.add("Yes");
        options.add("No");
        Assertions.assertEquals(actual.getId(), "Mortgage Property");
        Assertions.assertEquals(actual.getOptions(), options);
    }

}
