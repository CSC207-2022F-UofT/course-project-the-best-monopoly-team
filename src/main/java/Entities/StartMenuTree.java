package Entities;
import java.util.ArrayList;
import java.util.List;

public class StartMenuTree<T> {
    private List<StartMenuTree<T>> children = new ArrayList<>();
    private StartMenuTree<T> parent = null;
    private String name;

    public StartMenuTree(String name){
        this.name = name;
    }

    public StartMenuTree(){
        StartMenuTree<T> gameLength = new StartMenuTree<>("Game Length");
        StartMenuTree<T> gameType = new StartMenuTree<>("Game Type");
        gameType.children.add(gameLength);
        gameLength.parent = gameType;

        StartMenuTree<T> numPlayers = new StartMenuTree<>("number of players");
        StartMenuTree<T> newGame = new StartMenuTree<>("start menu");
        newGame.children.add(numPlayers);
        newGame.children.add(gameType);
        numPlayers.parent = newGame;
        gameType.parent = newGame;

        StartMenuTree<T> loadGame = new StartMenuTree<>("Load Game");
        StartMenuTree<T> startMenu = new StartMenuTree<>("Start Menu");
        startMenu.children.add(loadGame);
        startMenu.children.add(newGame);
        loadGame.parent = startMenu;
        newGame.parent = startMenu;
    }
}
