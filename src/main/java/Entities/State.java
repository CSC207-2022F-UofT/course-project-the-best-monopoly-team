
package Entities;

import java.util.ArrayList;

public class State {
    public boolean isOnProperty() {
        return onProperty;
    }

    public void setOnProperty(boolean onProperty) {
        this.onProperty = onProperty;
    }

    private boolean onProperty;

    public ArrayList<String> getOptions() {
        return options;
    }

    private ArrayList<String> options = new ArrayList<String>();

    public ArrayList<String> getPlayerInfo() {
        return playerInfo;
    }

    private ArrayList<String> playerInfo = new ArrayList<String>();
    public void addOptions(String option){
        options.add(option);
    }
    public void addPlayerInfo(String info){
        this.playerInfo.add(info);
    }

}
