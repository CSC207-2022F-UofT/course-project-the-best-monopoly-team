package Entities;

import java.util.*;

public class ActionSpace {
    private final HashMap<String, String> jailCards;
    private final HashMap<String, String> comChestCards;
    private final HashMap<String, String> chanceCards;
    private final String type;

    public ActionSpace(String type, HashMap<String, String> jailCards, HashMap<String, String> comChestCards, HashMap<String, String> chanceCards) {
        // calling load cards method
        this.jailCards = jailCards;
        this.comChestCards = comChestCards;
        this.chanceCards = chanceCards;
        this.type = type;
    }

    public String performAction() {
        // get current player
        // Map the key to a function call?
        // Hard code the logic??
        return "";
    }

    public String getCard() {
        // how do we know which space we are on right now?

//        String card;
//        String current_space = getCurrentSpace();
//        if (Objects.equals(current_space, "jail")) {
//            List<String> keyList = new ArrayList<>(jailCards.keySet());
//            int random_index = new Random().nextInt(keyList.size());
//            card = jailCards.get(keyList.get(random_index));
//        } else if (Objects.equals(current_space, "chance")) {
//            List<String> keyList = new ArrayList<>(chanceCards.keySet());
//            int random_index = new Random().nextInt(keyList.size());
//            card = chanceCards.get(keyList.get(random_index));
//        } else {
//            List<String> keyList = new ArrayList<>(comChestCards.keySet());
//            int random_index = new Random().nextInt(keyList.size());
//            card = comChestCards.get(keyList.get(random_index));
//        }
//        return card;


        //placeholder return
        return "";

    }

}
