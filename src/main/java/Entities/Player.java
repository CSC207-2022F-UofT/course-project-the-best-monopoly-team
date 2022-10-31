package Entities;

import java.util.ArrayList;
import java.util.Map;

public class Player {
    // Represents a player in the game

    public String name;
    public int money;
    public ArrayList<Property> properties;
    public boolean inJail;
    public int jailCards;
    public int position;
    public Map<Integer, Integer> actions;

}
