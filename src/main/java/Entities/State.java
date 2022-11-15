
package Entities;

import java.util.ArrayList;

public class State {

    private ArrayList<String> playerInfo = new ArrayList<String>();
    private boolean onProperty;
    private boolean endNode;
    private String Name;
    private boolean saveGame;
    private boolean exitToMenu;
    public boolean isExitToMenu() {
        return exitToMenu;
    }

    public void setExitToMenu(boolean exitToMenu) {
        this.exitToMenu = exitToMenu;
    }
    public boolean isSaveGame() {
        return saveGame;
    }

    public void setSaveGame(boolean saveGame) {
        this.saveGame = saveGame;
    }
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    private ArrayList<String> options = new ArrayList<String>();

    public void setOnProperty(boolean onProperty) {
        this.onProperty = onProperty;
    }

    public boolean isOnProperty() {
        return onProperty;
    }

    public boolean isEndNode() {
        return endNode;
    }

    public void setEndNode(boolean endNode) {
        this.endNode = endNode;
    }

    public ArrayList<String> getOptions() {
        return options;
    }

    public ArrayList<String> getPlayerInfo() {
        return playerInfo;
    }

    public void addOptions(String option){
        options.add(option);
    }

    public void addPlayerInfo(String info){
        this.playerInfo.add(info);
    }

}
