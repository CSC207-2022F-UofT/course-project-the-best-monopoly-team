package Entities;

public class Card {

    private final String type;
    private final String action;
    private final String actiontype;
    private final Integer amount;

    public Card(String type, String action, String actiontype, Integer amount) {
        this.type = type;
        this.action = action;
        this.actiontype = actiontype;
        this.amount = amount;
    }

    public String getType() {
        return this.type;
    }

    public String getAction() {
        return this.action;
    }

    public String getActionType() {
        return this.actiontype;
    }

    public Integer getAmount() {
        return this.amount;
    }

}
