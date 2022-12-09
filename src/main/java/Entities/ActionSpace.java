package Entities;

import java.util.HashMap;
import java.util.List;

/**
 * This class represents an action space cell.
 */
public class ActionSpace extends Cell{
    
    private final List<Card> cards;
    private final String type;

    public ActionSpace(HashMap<String, List<Card>> cards) {
        String key = cards.keySet().iterator().next();
        this.cards = cards.get(key);
        this.type = key;
    }

    @Override
    public String getType() {
        return "Action Space";
    }

    public String getActionType() {
        return this.type;
    }


    public List<Card> getCards() {
        return this.cards;
    }

}
