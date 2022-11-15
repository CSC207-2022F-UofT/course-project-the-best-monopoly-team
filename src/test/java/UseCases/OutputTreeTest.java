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
        assert tree.getName().equals("Initial Menu");
    }


    private OutputTree createTree(){
        OutputTree gameLength = new OutputTree("Game Length");
        OutputTree gameType = new OutputTree("Game Type");
        gameType.addChild(gameLength);

        OutputTree numPlayers = new OutputTree("number of players");
        OutputTree newGame = new OutputTree("start menu");
        newGame.addChild(numPlayers);
        newGame.addChild(gameType);

        OutputTree loadGame = new OutputTree("Load Game");
        Integer[] position = {3,2};
        loadGame.addButtonPosition(position);
        OutputTree startMenu = new OutputTree("Initial Menu");
        startMenu.addChild(loadGame);
        startMenu.addChild(newGame);

        return startMenu;
    }
}