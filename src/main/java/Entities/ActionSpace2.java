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

    public String getType() {
        return type;
    }

    public List<Card> getCards() {
        return cards;
    }

    @Override
    public String performAction(Player player, Board board) {
        // TODO Auto-generated method stub
        return null;
    }

}
