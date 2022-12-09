package Entities;

import Logic.InitialNodeLogic.*;
import org.junit.jupiter.api.Test;

class GameLogicTreeTest {
    private final GameLogicTree tree = createTree();


    @Test
    void getParent() {
        assert tree.getParent() == null;
    }


    @Test
    void getName() {

    }


    private GameLogicTree createTree(){
        GameLogicTree gameLength = new GameLogicTree(new GameLengthUseCase());
        GameLogicTree gameType = new GameLogicTree(new ChooseGameModeUseCase());
        gameType.addChild(gameLength);

        GameLogicTree numPlayers = new GameLogicTree(new NumberOfPlayersUseCase());
        GameLogicTree newGame = new GameLogicTree(new NewGameUseCase());
        newGame.addChild(numPlayers);
        newGame.addChild(gameType);

        GameLogicTree loadGame = new GameLogicTree(new LoadGameUseCase());
        GameLogicTree startMenu = new GameLogicTree(new InitialParentNodeUseCase());
        startMenu.addChild(loadGame);
        startMenu.addChild(newGame);

        return startMenu;
    }
}