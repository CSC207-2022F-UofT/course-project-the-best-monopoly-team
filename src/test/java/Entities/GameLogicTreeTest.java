package Entities;

import UseCases.InitialNodeLogic.*;
import org.junit.jupiter.api.Test;

class GameLogicTreeTest {
    private GameLogicTree tree = createTree();


    @Test
    void getParent() {
        assert tree.getParent() == null;
    }


    @Test
    void getName() {

    }


    private GameLogicTree createTree(){
        GameLogicTree gameLength = new GameLogicTree(new GameLength());
        GameLogicTree gameType = new GameLogicTree(new ChooseGameMode());
        gameType.addChild(gameLength);

        GameLogicTree numPlayers = new GameLogicTree(new NumberOfPlayers());
        GameLogicTree newGame = new GameLogicTree(new NewGame());
        newGame.addChild(numPlayers);
        newGame.addChild(gameType);

        GameLogicTree loadGame = new GameLogicTree(new LoadGame());
        GameLogicTree startMenu = new GameLogicTree(new InitialParentNode());
        startMenu.addChild(loadGame);
        startMenu.addChild(newGame);

        return startMenu;
    }
}