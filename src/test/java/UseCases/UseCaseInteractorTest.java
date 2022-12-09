package UseCases;

import Entities.State;
import Persistence.LoadAccess;
import Persistence.LoadFile;
import Persistence.SaveAccess;
import Persistence.SaveFile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class UseCaseInteractorTest {

    @Test
    void handleInput() {
        LoadAccess game = new LoadFile(new File("src/gameData/input_test.txt"));
        SaveAccess save = new SaveFile(new File("src/gameData/input_test.txt"));
        UseCaseInteractor useCaseTest = new UseCaseInteractor(game, save);
        State currentState = useCaseTest.handleInput(0);
        Assertions.assertEquals(currentState.getId(), "New Game");
    }

    @Test
    void createTrees() {
        LoadAccess game = new LoadFile(new File("src/gameData/input_test.txt"));
        SaveAccess save = new SaveFile(new File("src/gameData/input_test.txt"));
        UseCaseInteractor useCaseTest = new UseCaseInteractor(game, save);
        Assertions.assertNotNull(useCaseTest.getCurrentTree());
    }

    @Test
    void handleInitialTree() {
    }

    @Test
    void handleGameTree() {
    }

    @Test
    void updateOutput() {
    }

    @Test
    void loadFiles() {
    }
}