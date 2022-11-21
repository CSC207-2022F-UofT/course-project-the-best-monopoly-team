
package Entities;

import java.util.ArrayList;

public class State {

    private ArrayList<String> playerInfo = new ArrayList<String>();

    private boolean backEnable;
    private String description;
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
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private ArrayList<String> options = new ArrayList<String>();


    public void addOptions(String option) {
        options.add(option);
    }


    public void addPlayerInfo(String info) {
        this.playerInfo.add(info);
    }
    public boolean isBackEnable() {
        return backEnable;
    }
    public void setBackEnable(boolean backEnable) {
        this.backEnable = backEnable;
    }

    public ArrayList<String> getOptions() {
        return options;
    }
}
