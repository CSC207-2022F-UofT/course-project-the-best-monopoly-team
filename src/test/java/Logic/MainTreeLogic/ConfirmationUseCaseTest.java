package Logic.MainTreeLogic;

import Entities.Board;
import Entities.Cell;
import Entities.Player;
import Entities.State;
import Interactors.GameLogic;
import Logic.GeneralGameLogic;
import Logic.MainTreeNodeLogic.ConfirmationUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ConfirmationUseCaseTest {

    @Test
    public void testConfirmationUseCaseCreateState(){
        Player playerOne = new Player("Player One");
        Player playerTwo = new Player("Player Two");
        List<Player> players = new ArrayList<>();
        players.add(playerOne);
        players.add(playerTwo);
        List<Cell> cells = new ArrayList<>();
        Board board = new Board(players, cells);
        GameLogic gameLogic = new GameLogic(playerOne, board);
        GeneralGameLogic.initialize(playerOne, board, gameLogic);
        ConfirmationUseCase confirmationUseCase = new ConfirmationUseCase();
        State actual = confirmationUseCase.create_state(0);
        Assertions.assertEquals(actual.getId(), "Main Tree Parent Node");
    }

}
