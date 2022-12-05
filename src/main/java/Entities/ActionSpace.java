package Entities;

import java.util.HashMap;
import java.util.List;

public class ActionSpace extends Cell{
    
    private List<Card> cards;
    private String type;

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
        return cards;
    }

}
