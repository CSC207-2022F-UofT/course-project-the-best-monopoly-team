package Entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static UseCases.PlayerLogic.*;

public class Player {
    // Represents a player in the game

    private String name;
    private int money;
    private ArrayList<Property> properties;
    private boolean inJail;
    private int jailCards;
    private int position;

    static final int STARTING_MONEY = 1500;
    static final int STARTING_JAILCARDS = 0;
    static final int STARTING_POSITION = 0;
    static final int GO_MONEY = 200;

    static final int LAST_POSITION_INDEX = 39;
    static final int BOARD_SIZE = 40;

    /**
     * This is the constructor method for new player instances
     * @param name the name of the player
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
     * Set the player's position. This is useful when the player needs to move by a mean beside rolling the dice e.g.
     * action cards, or when we need to load a previous game.
     * @param position the position of the player on the board should be between 0 and 39 inclusive.
     */
    public void setPosition(int position) { this.position = position; }

    /**
     * Sets the player's inJail attribute, which in this case is a boolean value
     * @param inJail the parameter for this is a boolean value that sets their inJail property to true or false
     */
    public void setInJail(boolean inJail) { this.inJail = inJail; }

    /**
     * Sets the player's number of get out of jail free cards
     * @param num the number of jail cards the player has
     */
    public void setJailCards(int num) { this.jailCards = num; }

    /**
     * Sets the amount of money owned by this player. Needed for loading previous games.
     * @param money the amount of money the player is having
     */
    public void setMoney(int money) { this.money = money; }

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
     * The getter method for this player's name
     * @return the name of the player
     */
    public String getName(){ return this.name; }

    /**
     * The getter method for this player's list of properties
     * @return an ArrayList of properties owned by this player
     */
    public ArrayList<Property> getProperties() { return properties; }

    /**
     * The getter method for this player's amount of money
     * @return the amount of money currently owned by this player
     */
    public int getMoney() { return this.money; }

    /**
     * This method determines whether this player is currently in jail
     * @return a boolean value determining whether the player is in jail, with true being the player is in jail
     */
    public boolean isInJail() { return inJail; }

    /**
     * Gets the number of railroads owned by this player
     * @return the number of railroads owned by this player
     */
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
     * Changes the jail status of this player
     */
    public void changeJailStatus() {
        this.inJail = !this.inJail;
    }

    /**
     * This method adds a property to this player's list of owned properties
     * @param property the property to be added to the player's property list
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
     * @param rig
     * @return
     */
    public String riggedRoll(int rig) {
        this.move(rig);
        return (""+rig);
    }

    /**
     * This method rolls the dice for the player. The dice result can indicate this player's movement while
     * they are not in jail. If they are in jail, the dice result can determine if the player can get out of jail
     * @param consecutive the number of consecutive doubles
     * @return a String of two numbers indicating the numbers rolled from the dice
     */
    public String rollDice(int consecutive) {
        int max = 6;
        int min = 1;
        int a =  (int) Math.floor(Math.random() * (max - min + 1) + min);
        int b = (int) Math.floor(Math.random() * (max - min + 1) + min);
        if (this.inJail){
            if(a == b){
                this.inJail = false;
                this.move(a + b);
            }
            return (a + " " + b + "\n");

        }
        else {
            if (a != b){
                this.move(a + b);
                return (a + " " + b + "\n");
            }
            else if (a == b && (consecutive + 1) < 3){
                return this.rollDice((consecutive + 1));
            }
            else if(a == b && (consecutive + 1) == 3){
                // the player goes to jail
                this.setInJail(true);
                return (a + " " + b + "\n" + "player goes to jail");
            }
            return (a + " " + b + "\n");
        }
    }

    /**
     * Increases the amount of money owned by this player
     * @param change an integer indicating how much money will be added to the player's balance
     */
    public void changeMoney(int change) {
        this.money += change;
    }

    /**
     * A payment method where money is deducted from the player's balance e.g. to pay tax
     * @param money the amount of money deducted from this player's balance
     */
    public void pay(int money) {
        this.money -= money;
    }

    /**
     * A payment method that allows this player to pay another player e.g. paying rent
     * @param player the player whom this player is giving money to
     * @param money the amount of money being paid by this player
     */
    public void pay(Player player, int money) {
        this.money -= money;
        player.money += money;
    }

    /**
     * This method allows the player to place one of their owned properties for mortgage
     * @param property the property to mortgaged by this player
     */
    public void mortgage(Property property) {
        this.properties.remove(property);
        this.money += property.getMortgageValue();
    }

    public String buildHouse(Property property, int houses) {
        switch (property.addHouse(this, houses)) {
            case "house":
                return ((houses + " houses have been built on " + property.getName()));
            case "hotel":
                return ("A hotel has been built on " + property.getName());
            case "not owned":
                return ("Player does not own " + property.getName());
            case "not owned set":
                return ("Player does not own the full colour set of " + property.getName());
            case "not enough money":
                return ("Player does not have enough money to build " + houses + " houses on " + property.getName());
            default:
                return null;
        }
    }

    public String steal(Player victim) {
        double success = Math.random();
        if (success <= STEAL_CHANCE) {
            victim.pay(this, STEAL_MONEY);
            return this.getName() + " stole money from " + victim.getName();
        } else {
            System.out.println("The police are looking for " + this.getName());
            double jail = Math.random();
            if (jail <= STEAL_JAIL_CHANCE) {
                this.setInJail(true);
                return this.getName() + " is put in jail";
            } else {
                return this.getName() + " escaped from the police";
            }
        }
    }

    public ArrayList<String> ownedPropertySets() {
        ArrayList<String> ownedSets = new ArrayList<>();
        HashMap<String, Integer> sets = countPropertySets();
        for (Map.Entry<String, Integer> colour : sets.entrySet()) {
            if (colour.getKey().equals("Brown") || colour.getKey().equals("Dark Blue")) {
                if (colour.getValue() == BROWN_DARKBLUE_SETSIZE) {
                    ownedSets.add(colour.getKey());
                }
            } else {
                if (colour.getValue() == PROPERTY_SETSIZE) {
                    ownedSets.add(colour.getKey());
                }
            }
        }
        return ownedSets;
    }

    public HashMap<String, Integer> countPropertySets() {
        HashMap<String, Integer> sets = createSetMap();
        for (Property property : this.getProperties()) {
            sets.put(property.getColour(), sets.get(property.getColour()) + 1);
        }
        return sets;
    }

}

