package Interactors;

import Persistence.LoadAccess;
import Persistence.LoadFile;
import Persistence.SaveAccess;
import Persistence.SaveFile;
import UseCases.UseCaseInteractor;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

public class InputInteractorTest {

    @Test
    public void testSendInputNewGame() {
        LoadAccess game = new LoadFile(new File("src/gameData/input_test.txt"));
        SaveAccess save = new SaveFile(new File("src/gameData/input_test.txt"));
        UseCaseInteractor useCaseTest = new UseCaseInteractor(game, save);
        InputInteractor inputInteractor = new InputInteractor(useCaseTest);

        inputInteractor.getChoice(0);
        inputInteractor.sendInput();

        inputInteractor.getChoice(0);
        inputInteractor.sendInput();

        inputInteractor.getChoice(2);
        inputInteractor.sendInput();

        ArrayList<String> test = inputInteractor.getCurrentState().getOptions();

        ArrayList<String> nextOptions = new ArrayList<>();
        nextOptions.add("30 rounds");
        nextOptions.add("60 rounds");
        nextOptions.add("90 rounds");
        nextOptions.add("no limit");

        System.out.println(test);
        System.out.println(nextOptions);

        assert test.equals(nextOptions);
    }

    @Test
    public void testSendInputLoadGame() {
        LoadAccess game = new LoadFile(new File("src/gameData/"));
        SaveAccess save = new SaveFile(new File("src/gameData/"));
        UseCaseInteractor useCaseTest = new UseCaseInteractor(game, save);
        InputInteractor inputInteractor = new InputInteractor(useCaseTest);

        inputInteractor.getChoice(1);
        inputInteractor.sendInput();

        ArrayList<String> test = inputInteractor.getCurrentState().getOptions();

        assert test.contains("input_test.txt");
        assert test.contains("test.txt");
        assert test.contains("test01.txt");
    }

    @Test
    public void testGetChoice() {
        LoadAccess game = new LoadFile(new File("src/gameData/input_test.txt"));
        SaveAccess save = new SaveFile(new File("src/gameData/input_test.txt"));
        UseCaseInteractor useCaseTest = new UseCaseInteractor(game, save);
        InputInteractor inputInteractor = new InputInteractor(useCaseTest);
        int num = 1;
        inputInteractor.getChoice(num);

        assert inputInteractor.input.getInput() == num;
    }
}
