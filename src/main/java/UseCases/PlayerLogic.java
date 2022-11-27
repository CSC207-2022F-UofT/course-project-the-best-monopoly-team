package UseCases;

import Entities.Player;
import Entities.Property;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static UseCases.UseCasesGlobalVariables.*;

public class PlayerLogic {
    private Player player;

    public PlayerLogic(Player player) {
        this.player = player;
    }



    public String rollDice(int consecutive){
        int max = 6;
        int min = 1;
        int a =  (int) Math.floor(Math.random() * (max - min + 1) + min);
        int b = (int) Math.floor(Math.random() * (max - min + 1) + min);
        if (this.player.isInJail() && a == b){
                this.player.setInJail(false);
                this.player.move(a + b);
            }
        else if (! this.player.isInJail() && a != b ){
                this.player.move(a + b);
                return (a + " " + b + "\n");
            }
        else if (! this.player.isInJail() && a == b && (consecutive + 1) < 3){
                return this.rollDice((consecutive + 1));
            }
        else if(! this.player.isInJail() && a == b && (consecutive + 1) == 3){
                // the player goes to jail
                this.player.setInJail(true);
                return (a + " " + b + "\n" + "player goes to jail");
            }
        return (a + " " + b + "\n");
    }

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
     * Counts the number of properties owned by player in each colour set
     * @return a HashMap mapping each colour to an int value correlating to the number of properties of that colour
     * owned by this player
     */
    public HashMap<String, Integer> countPropertySets(Player player) {
        HashMap<String, Integer> sets = createSetMap();
        for (Property property : player.getProperties()) {
            sets.put(property.getColour(), sets.get(property.getColour()) + 1);
        }
        return sets;
    }

    /**
     * Gets all the complete sets of properties owned by this player (sets are grouped by colours)
     *
     * @return an ArrayList of colours referring to the complete sets of properties owned by this player
     */
    public ArrayList<String> ownedPropertySets(Player player) {
        ArrayList<String> ownedSets = new ArrayList<>();
        HashMap<String, Integer> sets = countPropertySets(player);
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

    /**
     * Method allowing this player to trade assets with another player
     * @param tradee the player this player wants to trade with
     * @param money any sufficient amount of money this player wants to offer the tradee
     * @param properties properties owned by this player that they would like to offer the tradee
     * @param jailcards any sufficient amount of jail cards this player wants to offer to the tradee
     * @return a String indicating whether the player has an insufficient amount of money or if the trade was successful
     */
    public String trade(Player trader, Player tradee, int money, ArrayList<Property> properties, int jailcards) {
        trader.pay(tradee, money);
        tradee.getProperties().addAll(properties);
        trader.getProperties().removeAll(properties);
        tradee.addJailCards(jailcards);
        trader.removeJailCards(jailcards);
        return "Trade successful";
    }

    /**
     * Method that allows this player to steal 100 units of money from a player. The success of stealing relies on
     * a chance basis. Unsuccessfully stealing from the victim gives this player a chance of being put in jail.
     * @param victim the player whom this player is stealing from
     * @return returns a String statement indicating whether stealing was successful. If not, the statement indicates
     * whether this player is put in jail
     */
    public String steal(Player thief, Player victim) {
        double success = Math.random();
        if (success <= STEAL_CHANCE) {
            victim.pay(thief, STEAL_MONEY);
            return thief.getName() + " stole money from " + victim.getName();
        } else {
            System.out.println("The police are looking for " + thief.getName());
            double jail = Math.random();
            if (jail <= STEAL_JAIL_CHANCE) {
                thief.setInJail(true);
                return thief.getName() + " is put in jail";
            } else {
                return thief.getName() + " escaped from the police";
            }
        }
    }

    /**
     * This method allows this player to build houses on a currently owned property
     * @param property the property to build the house on
     * @param houses the number of houses to build
     */
    public String buildHouse(Player player, Property property, int houses) {
        switch (property.addHouse(player, houses)) {
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

}

