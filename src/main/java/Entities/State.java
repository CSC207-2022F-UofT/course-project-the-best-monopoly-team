
package Entities;

import java.util.ArrayList;

public class State {

    private Player player;
    private Player tradingPlayer;
    private boolean backEnable;
    private String description;
    private boolean saveGame;
    private boolean exitToMenu;
    private String roll;
    private Property currentPlayerProperty;
    private Property tradingPlayerProperty;
    private Property biddingProperty;
    private int biddingPot;
    private String id;
    private ArrayList<String> options = new ArrayList<>();

    public String getRoll() {
        return this.roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public Property getCurrentPlayerProperty() {
        return this.currentPlayerProperty;
    }

    public void setCurrentPlayerProperty(Property currentPlayerProperty) {
        this.currentPlayerProperty = currentPlayerProperty;
    }

    public Property getTradingPlayerProperty() {
        return this.tradingPlayerProperty;
    }

    public void setTradingPlayerProperty(Property tradingPlayerProperty) {
        this.tradingPlayerProperty = tradingPlayerProperty;
    }
    public boolean isExitToMenu() {
        return this.exitToMenu;
    }

    public void setExitToMenu(boolean exitToMenu) {
        this.exitToMenu = exitToMenu;
    }
    public boolean isSaveGame() {
        return this.saveGame;
    }

    public void setSaveGame(boolean saveGame) {
        this.saveGame = saveGame;
    }
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public void addOptions(String option) {
        this.options.add(option);
    }

    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
    public boolean isBackEnable() {
        return this.backEnable;
    }
    public void setBackEnable(boolean backEnable) {
        this.backEnable = backEnable;
    }

    public ArrayList<String> getOptions() {
        return this.options;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public int getBiddingPot() {
        return this.biddingPot;
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
        return this.tradingPlayer;
    }

    public void setTradingOpponent(Player tradingPlayer) {
        this.tradingPlayer = tradingPlayer;
    }
}
