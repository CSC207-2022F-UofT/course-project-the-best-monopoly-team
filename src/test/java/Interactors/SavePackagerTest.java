package Interactors;

import Logic.MainTreeNodeLogic.MainTreeNodeLogic;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SavePackagerTest {

    @Test
    void getStates() {
        // we do not implement a test for getStates since it calls
        // MainTreeNodeLogic.getStates() which is a static method
    }

    @Test
    void getPlayerPropertyData() {
        // we do not implement a test for getPlayerPropertyData since it calls
        // GeneralGameLogic.getBoard().getPlayers() where Board and Players
        // are static variables, and getBoard() is a static method
    }
}