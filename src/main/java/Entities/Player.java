package Entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * A class representing a player in the game of monpoly
 */
public class Player {
    /**
     * InstanceVar name: the name of the player
     * InstanceVar money: the amount of money the player has
     * InstanceVar properties: the properties the player owns
     * InstanceVar inJail: a variable representing if the player is in jail or not
     * InstanceVar jailCards: the number of get out of jail free cards the user has
     * InstanceVar position: the current position of the player on the board
     */
    private String name;
    private int money;
    private ArrayList<Property> properties;
    private boolean inJail;
    private int jailCards;
    private int position;

    /**
     * Global Variables representing constant values
     */
    private static final int STARTING_MONEY = 1500;
    private static final int STARTING_JAILCARDS = 0;
    private static final int STARTING_POSITION = 0;
    private static final int GO_MONEY = 200;

    private static final int LAST_POSITION_INDEX = 39;
    private static final int BOARD_SIZE = 40;

    /**
     * This is the constructor method for new player instances
     * @param name this parameter is for the name of the player
     */
    public Player(String name) {
        this.name = name;
        this.money = STARTING_MONEY;
        this.properties = new ArrayList<Property>();
        this.inJail = false;
        this.jailCards = STARTING_JAILCARDS;
        this.position = STARTING_POSITION;
    }

    /**
     * This is the constructor method for new player instances that takes in more parameters. Useful when loading a
     * previously saved game
     * @param name the name of the player
     * @param money the amount of money in the player's balance
     * @param inJail the player's jail status
     * @param jailCards the number of get out of jail free cards owned by the player
     * @param position the player's current position on the board
     */
    public Player(String name, int money, boolean inJail, int jailCards, int position) {
        this.name = name;
        this.money = money;
        this.properties = new ArrayList<Property>();
        this.inJail = inJail;
        this.jailCards = jailCards;
        this.position = position;
    }

    /**
     * This basically sets the player attribute of inJail, which in this case is a boolean value
     * @param inJail the parameter for this is a boolean value that sets their inJail property to true or false
     */
    public void setInJail(boolean inJail) {
        this.inJail = inJail;
    }

    /**
     * Set the player's position.
     * This is useful when the player needs to move by a mean beside rolling the dice
     * e.g. action cards, or when we need to load a previous game.
     * @param position the position of the player on the board should be between 0 and 39 inclusive.
     */
    public void setPosition(int position){
        this.position = position;
    }

    /**
     * This sets the number of get out of jail free cards the player has that was drawn from the community chest or
     * chance cards
     * @param num this parameter is the number of cards to increment by for player
     */
    public void setJailCards(int num) {
        this.jailCards = this.jailCards + num;
    }

    /**
     * The getter method for jailCards
     * @return the return value is of type int and returns the number of jail cards the current player has
     */
    public int getJailCards() { return jailCards; }

    /**
     * The getter method for position of the player
     * @return  returns the int that is from 0 to 39 inclusive corresponding to the player position
     */
    public int getPosition() { return position; }

    /**
     * The getter method to see if the player is in jail or not
     * @return returns a boolean value of False and True
     */
    public boolean isInJail() { return inJail; }
    public int getRailroads() {
        int railroads = 0;
        for (Property property : this.properties) {
            if (Objects.equals(property.getColour(), "Railroad")) {
                railroads += 1;
            }
        }
        return railroads;
    }

    /**
     * Gets the number of utilities owned by this player
     * @return the number of utilities owned by this player
     */
    public int getUtilities() {
        int utility = 0;
        for (Property property : this.properties) {
            if (Objects.equals(property.getColour(), "Utility")) {
                utility += 1;
            }
        }
        return utility;
    }

    /**
     * Increment the player's number of get out of jail free cards by a specific number
     * @param num this parameter is the number of cards to increment by for player
     */
    public void addJailCards(int num) { this.jailCards += num; }

    /**
     * Decrement the player's number of get out of jail free cards by a specific number
     * @param num this parameter is the number of cards to decrement by for player
     */
    public void removeJailCards(int num) { this.jailCards -= num; }

    /**
     * Helper function for ownedPropertySets(). This function creates a map with the property colours as the key and
     * an int counter as its value.
     * @return a HashMap with the property colours as the key and an int counter as its value.
     */
    private HashMap<String, Integer> createSetMap() {
        HashMap<String, Integer> sets = new HashMap<>();
        sets.put("Brown", 0);
        sets.put("Light Blue", 0);
        sets.put("Pink", 0);
        sets.put("Orange", 0);
        sets.put("Red", 0);
        sets.put("Yellow", 0);
        sets.put("Green", 0);
        sets.put("Dark Blue", 0);
        return sets;
    }

    /**
     * Changes the jail status of the player
     */
    public void changeJailStatus() {
        this.inJail = !this.inJail;
    }


    /**
     * Getter method for the name of the player
     * @return returns a string, which is the name of the player
     */
    public String getName(){
        return this.name;
    }

    /**
     * Setter method for setting the amount money
     * @param money the money that we want to set the money of the player to
     */
    public void setMoney(int money) {
        this.money = money;
    }

    /**
     * Getter method for getting the current amount of money that the player has.
     * @return returns and integer
     */
    public int getMoney() {
        return this.money;
    }

    /**
     * Adds a certain property to the current player
     * @param property the property to add to the properties list of the player
     */
    public void addProperty(Property property) {
        this.properties.add(property);
    }

    /**
     * Method to move the player by x number of steps
     * @param step the steps to move the player to
     */
    public void move(int step) {
        position += step;
        if (position > LAST_POSITION_INDEX) {
            //Money on passing go
            money += GO_MONEY;
            position -= BOARD_SIZE;
        }
    }

    /**
     * Rigged roll for testing purposes
     * @param rig - number to change the roll to
     * @return
     */
    public String riggedRoll(int rig) {
        this.move(rig);
        return (""+rig);
    }

    /**
     * Increases the amount of money owned by this player
     * @param change an integer indicating how much money will be added to the player's balance
     */
    public void changeMoney(int change) {
        this.money += change;
    }

    /**
     * Decrease the money of the player
     * @param money the amount of money to decrease by
     */
    public void pay(int money) {
        this.money -= money;
    }

    /**
     * Decrease the money of the current player and add it to another players money
     * @param player the player to add the money to
     * @param money the amount of money to decrease by for current player and increase by for the other player
     */
    public void pay(Player player, int money) {
        this.money -= money;
        player.money += money;
    }

    /**
     * Gets all of the properties of the current player
     * @return returns and array list of properties
     */
    public ArrayList<Property> getProperties() {
        return properties;
    }

    /**
     * Sets the current players properties to another ArrayList of properties
     * @param properties the ArrayList of properties to change it to
     */
    public void setProperties(ArrayList<Property> properties) {
        this.properties = properties;
    }

}

