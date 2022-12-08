package Logic.MainTreeLogic;

import Entities.*;
import UseCases.Logic.GameLogic;
import UseCases.Logic.MainTreeNodeLogic.ChoosePlayerUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ChoosePlayerUseCaseTest {

    @Test
    public void testChoosePlayerUseCaseCreateState(){
        Player playerOne = new Player("Player One");
        Player playerTwo = new Player("Player Two");
        List<Player> players = new ArrayList<>();
        players.add(playerOne);
        players.add(playerTwo);
        List<Cell> cells = new ArrayList<>();
        Board board = new Board(players, cells);
        GameLogic gameLogic = new GameLogic(playerOne, board);
        ChoosePlayerUseCase choosePlayerUseCase = new ChoosePlayerUseCase();
        State actual = choosePlayerUseCase.create_state(0);
        ArrayList<String> options = new ArrayList<String>();
        options.add("Ok");
        Assertions.assertEquals(actual.getOptions(), options);
        Assertions.assertEquals(actual.getId(), "Choose Player (Steal)");
    }

}
