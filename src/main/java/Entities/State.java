
package Entities;

import java.util.ArrayList;

/**
 * This class represents an object that stores information relating to the state of the game in different scenarios.
 */
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
    private final ArrayList<String> options = new ArrayList<>();

    /**
     * This method returns the roll value stored in the State object which corresponds to value of the dice rolled by
     * the player.
     * @return A String representation of an integer representing the roll value of the dice rolled by the player.
     */
    public String getRoll() {
        return this.roll;
    }

    /**
     * This method sets the roll value stored in the State object.
     * @param roll A string representation of an integer representing the roll value of the dice.
     */
    public void setRoll(String roll) {
        this.roll = roll;
    }

    /**
     * This method returns a Property instance representing a property that the current player owns that is being
     * focused on.
     * @return a Property instance representing a property that the current player owns that is being focused on.
     */
    public Property getCurrentPlayerProperty() {
        return this.currentPlayerProperty;
    }

    /**
     * This method sets the currentPlayerProperty instance attribute to a Property that the current player owns and is
     * to be focused on.
     * @param currentPlayerProperty a Property instance that the current player owns and is to be focused on.
     */
    public void setCurrentPlayerProperty(Property currentPlayerProperty) {
        this.currentPlayerProperty = currentPlayerProperty;
    }
    /**
     * This method returns a Property instance representing a property that the player wants to trade.
     * @return a Property instance representing a property that the player wants to trade.
     */
    public Property getTradingPlayerProperty() {
        return this.tradingPlayerProperty;
    }

    /**
     * This method sets the tradingPlayerProperty instance attribute to a Property instance representing a property
     * that the player wants to trade.
     * @param tradingPlayerProperty a Property instance representing a property that the player wants to trade.
     */
    public void setTradingPlayerProperty(Property tradingPlayerProperty) {
        this.tradingPlayerProperty = tradingPlayerProperty;
    }

    /**
     * This method returns a boolean value showing if the game should exit to the menu.
     * @return a boolean value showing if the game should exit to the menu.
     */
    public boolean isExitToMenu() {
        return this.exitToMenu;
    }

    /**
     * This method sets the exitToMenu instance attribute showing if the game should exit to the menu.
     * @param exitToMenu a boolean value showing if the game should exit to the menu.
     */
    public void setExitToMenu(boolean exitToMenu) {
        this.exitToMenu = exitToMenu;
    }

    /**
     * This method returns a boolean value showing if the game should be saved.
     * @return a boolean value showing if the game should be saved.
     */
    public boolean isSaveGame() {
        return this.saveGame;
    }

    /**
     * This method sets the saveGame instance attribute showing if the game should be saved.
     * @param saveGame a boolean value showing if the game should be saved.
     */
    public void setSaveGame(boolean saveGame) {
        this.saveGame = saveGame;
    }

    /**
     * This method returns a String which represents a description of the game's current state.
     * @return a String which represents a description of the game's current state.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * This method sets the description instance attribute representing a description of the game's current state.
     * @param description a String which represents a description of the game's current state.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This method adds an option to the options instance attribute which will be presented to the user(s).
     * @param option A string representing an option to be presented to the user(s).
     */
    public void addOptions(String option) {
        this.options.add(option);
    }

    /**
     * This method returns an ArrayList containing the options that are to be shown to the user(s).
     * @return an ArrayList containing the options that are to be shown to the user(s).
     */
    public ArrayList<String> getOptions() {
        return this.options;
    }

    /**
     * This method returns a Player instance stored in the player instance attribute.
     * @return a Player instance stored in the player instance attribute.
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * This method sets the player instance attribute.
     * @param player a Player instance that is to be stored in the player instance attribute.
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * This method returns a boolean value showing if the game could go back to its previous state.
     * @return a boolean value showing if the game could go back to its previous state.
     */
    public boolean isBackEnable() {
        return backEnable;
    }

    /**
     * This method sets the backEnable instance attribute which shows if the game could go back to its previous state.
     * @param backEnable a boolean value showing if the game could go back to its previous state.
     */
    public void setBackEnable(boolean backEnable) {
        this.backEnable = backEnable;
    }

    /**
     * This method returns a String representing the "Id" of the tree node that the game is currently in.
     * @return a String representing the "Id" of the tree node that the game is currently in.
     */
    public String getId() {
        return this.id;
    }

    /**
     * This method sets the id instance attribute to a String representing the "Id" of the tree node that the game is
     * currently in.
     * @param id a String representing the "Id" of the tree node that the game is currently in.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * This method returns an int representing the value inside the bidding pot during a property auction.
     * @return an int representing the value inside the bidding pot during a property auction.
     */
    public int getBiddingPot() {
        return this.biddingPot;
    }

    /**
     * This method sets the biddingPot instance attribute which represents the value inside the bidding pot during a
     * property auction.
     * @param biddingPot an int representing the value inside the bidding pot during a property auction.
     */
    public void setBiddingPot(int biddingPot) {
        this.biddingPot = biddingPot;
    }

    /**
     * This method returns a Property instance representing the property that is being auctioned.
     * @return a Property instance representing the property that is being auctioned.
     */
    public Property getBiddingProperty() {
        return biddingProperty;
    }

    /**
     * This method sets the biddingProperty instance attribute which represents the property is being auctioned.
     * @param biddingProperty a Property instance representing the property that is to be auctioned.
     */
    public void setBiddingProperty(Property biddingProperty) {
        this.biddingProperty = biddingProperty;
    }

    /**
     * This method returns a Player instance that the current player is trading with.
     * @return a Player instance that the current player is trading with.
     */
    public Player getTradingOpponent() {
        return this.tradingPlayer;
    }

    /**
     * This method sets the tradingPlayer instance attribute which represents the player that the current player is
     * trading with.
     * @param tradingPlayer a Player instance that the current player will trade with.
     */
    public void setTradingOpponent(Player tradingPlayer) {
        this.tradingPlayer = tradingPlayer;
    }
}