package Logic.MainTreeLogic;

import Entities.*;
import Logic.GameLogic;
import Logic.MainTreeNodeLogic.EndTurnUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class EndTurnUseCaseTest {

    @Test
    public void testEndTurnUseCaseCreateStateDebt(){
        Player playerOne = new Player("Player One");
        playerOne.setMoney(-1);
        List<Player> players = new ArrayList<>();
        players.add(playerOne);
        List<Cell> cells = new ArrayList<>();
        Board board = new Board(players, cells);
        new GameLogic(playerOne, board);
        EndTurnUseCase endTurnUseCase = new EndTurnUseCase();
        State actual = endTurnUseCase.create_state(0);
        ArrayList<String> options = new ArrayList<String>();
        options.add("Ok");
        Assertions.assertEquals(actual.getId(), "End Turn");
        Assertions.assertEquals(actual.getOptions(), options);
    }

}
