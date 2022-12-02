package Entities;

import java.util.HashMap;
import java.util.List;

public class ActionSpace2 extends Cell{
    
    private List<Card> cards;
    private String type;

    public ActionSpace2(HashMap<String, List<Card>> cards) {
        String key = cards.keySet().iterator().next();
        this.cards = cards.get(key);
        this.type = key;
    }

    @Override
    public String getType() {
        return "actionspace";
    }

    public String getActionType() {
        return this.type;
    }


    public List<Card> getCards() {
        return cards;
    }

}
