
package Entities;

import java.util.ArrayList;

public class State {

    private Player player;
    private Player tradingPlayer;
    private boolean backEnable;
    private String description;
    private boolean saveGame;
    private boolean exitToMenu;

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String roll;

    public Property getCurrentPlayerProperty() {
        return currentPlayerProperty;
    }

    public void setCurrentPlayerProperty(Property currentPlayerProperty) {
        this.currentPlayerProperty = currentPlayerProperty;
    }

    private Property currentPlayerProperty;

    public Property getTradingPlayerProperty() {
        return tradingPlayerProperty;
    }

    public void setTradingPlayerProperty(Property tradingPlayerProperty) {
        this.tradingPlayerProperty = tradingPlayerProperty;
    }

    private Property tradingPlayerProperty;
    private Property biddingProperty;

    private int biddingPot;

    private String id;
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

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public int getBiddingPot() {
        return biddingPot;
    }

    public void setBiddingPot(int biddingPot) {
        this.biddingPot = biddingPot;
    }
    public Property getBiddingProperty() {
        return biddingProperty;
    }

    public void setBiddingProperty(Property biddingProperty) {
        this.biddingProperty = biddingProperty;
    }
    public Player getTradingOpponent() {
        return tradingPlayer;
    }

    public void setTradingOpponent(Player tradingPlayer) {
        this.tradingPlayer = tradingPlayer;
    }
}
