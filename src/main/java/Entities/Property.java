package Entities;

public class Property {
    private Player ownedBy;
    private int houses;
    private int rent;
    private String name;
    private String colour;
    private int mortgageValue;
    private int houseCost;

    public Property (String name, String colour, int rent, int mortgageValue, int houseCost) {
        this.ownedBy = null;
        this.houses = 0;
        this.name = name;
        this.colour = colour;
        this.rent = rent;
        this.mortgageValue = mortgageValue;
        this.houseCost = houseCost;
    }

}
