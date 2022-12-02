package Entities;

import org.junit.jupiter.api.Test;

class GameLogicTreeTest {
    private GameLogicTree tree = createTree();


    @Test
    public void getParent() {
        assert tree.getParent() == null;
    }


    @Test
    public void getName() {
        assert tree.getName().equals("InitialMenu");
    }


    private GameLogicTree createTree(){
        GameLogicTree gameLength = new GameLogicTree("GameLength");
        GameLogicTree gameType = new GameLogicTree("GameType");
        gameType.addChild(gameLength);

        GameLogicTree numPlayers = new GameLogicTree("NumberOfPlayers");
        GameLogicTree newGame = new GameLogicTree("StartMenu");
        newGame.addChild(numPlayers);
        newGame.addChild(gameType);

        GameLogicTree loadGame = new GameLogicTree("LoadGame");
        GameLogicTree startMenu = new GameLogicTree("InitialMenu");
        startMenu.addChild(loadGame);
        startMenu.addChild(newGame);

        return startMenu;
    }
}