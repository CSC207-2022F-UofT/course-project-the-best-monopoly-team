package Logic.MainTreeLogic;

import Entities.*;
import Logic.GeneralGameLogic;
import Logic.MainTreeNodeLogic.CallActionUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CallActionUseCaseTest {

    @Test
    public void testCallActionCreateState(){
        Player playerOne = new Player("Player One");
        List<Player> players = new ArrayList<>();
        players.add(playerOne);
        Property test_property = new Property("Name", "Blue", 100, 100, new int[6],
                null, 50, 0, false);
        List<Cell> cells = new ArrayList<>();
        cells.add(test_property);
        Board board = new Board(players, cells);
        GeneralGameLogic generalGameLogic = new GeneralGameLogic();
        CallActionUseCase callActionUseCase = new CallActionUseCase();
        State actual = callActionUseCase.create_state(0);
        ArrayList<String> options = new ArrayList<String>();
        options.add("Ok");
        Assertions.assertEquals(actual.getOptions(), options);
        Assertions.assertEquals(actual.getDescription(), generalGameLogic.getAnswer());
    }

}
