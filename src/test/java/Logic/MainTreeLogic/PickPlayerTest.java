package NodeLogic.MainTreeLogic;

import Entities.*;
import Interactors.GameLogic;
import NodeLogic.MainTreeNodeLogic.PickPlayer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class PickPlayerTest {

    @Test
    public void testPickPlayerCreateStateNoProperties(){
        Player playerOne = new Player("Player One");
        Player playerTwo = new Player("Player Two");
        List<Player> players = new ArrayList<>();
        players.add(playerOne);
        players.add(playerTwo);
        List<Cell> cells = new ArrayList<>();
        Board board = new Board(players, cells);
        GameLogic gameLogic = new GameLogic(playerOne, board);
        PickPlayer pickPlayer = new PickPlayer();
        State actual = pickPlayer.create_state(0);
        Assertions.assertEquals(actual.getId(), "User Has No Properties (Manage Properties)");
        Assertions.assertEquals(actual.isBackEnable(), false);
    }

    @Test
    public void testPickPlayerCreateState(){
        Player playerOne = new Player("Player One");
        Player playerTwo = new Player("Player Two");
        Property propertyOne = new Property("Property One", "Blue", 100, 100, new int[6],
                playerOne, 50, 0, false);
        Property propertyTwo = new Property("Property Two", "Blue", 100, 100, new int[6],
                playerTwo, 50, 0, false);
        playerOne.addProperty(propertyOne);
        playerTwo.addProperty(propertyTwo);
        List<Player> players = new ArrayList<>();
        players.add(playerOne);
        players.add(playerTwo);
        List<Cell> cells = new ArrayList<>();
        cells.add(propertyOne);
        cells.add(propertyTwo);
        Board board = new Board(players, cells);
        GameLogic gameLogic = new GameLogic(playerOne, board);
        PickPlayer pickPlayer = new PickPlayer();
        State actual = pickPlayer.create_state(0);
        ArrayList<String> options = new ArrayList<String>();
        options.add("Property Two");
        Assertions.assertEquals(actual.getId(), "Pick Player (Trade)");
        Assertions.assertEquals(actual.isBackEnable(), true);
        Assertions.assertEquals(actual.getOptions(), options);
    }

}
