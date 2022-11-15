package UseCases;

import org.junit.jupiter.api.Test;

class OutputTreeTest {
    private OutputTree tree = createTree();

    @Test
    void getButtonPosition() {
        Integer[] position = {3,2};
        tree.addButtonPosition(position);
        assert tree.getButtonPosition().get(0) == position;
    }

    @Test
    void getParent() {
        assert tree.getParent() == null;
    }

    @Test
    void getChildren() {
        OutputTree temp = (OutputTree) tree.getChildren().get(0);
        Integer[] temp2 = temp.getButtonPosition().get(0);
        assert temp2[0] == 3;

    }

    @Test
    void getName() {
        assert tree.getName().equals("InitialMenu");
    }


    private OutputTree createTree(){
        OutputTree gameLength = new OutputTree("GameLength");
        OutputTree gameType = new OutputTree("GameType");
        gameType.addChild(gameLength);

        OutputTree numPlayers = new OutputTree("NumberOfPlayers");
        OutputTree newGame = new OutputTree("StartMenu");
        newGame.addChild(numPlayers);
        newGame.addChild(gameType);

        OutputTree loadGame = new OutputTree("LoadGame");
        Integer[] position = {3,2};
        loadGame.addButtonPosition(position);
        OutputTree startMenu = new OutputTree("InitialMenu");
        startMenu.addChild(loadGame);
        startMenu.addChild(newGame);

        return startMenu;
    }
}