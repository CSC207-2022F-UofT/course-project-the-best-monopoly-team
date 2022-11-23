package Interactors;

import java.util.List;
import Entities.Card;
import UseCases.CardMapperUseCase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class CardMapperInteractor implements CardMapperUseCase {
    
    /**
     * 
     * @param lst the list of cards before they are mapped
     * @return the list of jail cards map to the Card Entity
     */
    public HashMap<String,List<Card>> CardMapperJailCards(List<String> lst) {
        List<Card> cards = new ArrayList<>();
        HashMap<String,List<Card>> jailCards = new HashMap<>();
        for (String s : lst) {
            String[] arr = s.split(":");
            String type = arr[0];
            if (Objects.equals(type, "jail")) {
                String action = arr[1];
                String actiontype = arr[2];
                Integer amount = Integer.parseInt(arr[3]);
                Card card = new Card(type, action, actiontype, amount);
                cards.add(card);
            }
        }
        jailCards.put("jail", cards);
        return jailCards;
    }

    /**
     * 
     * @param lst the list of cards before they are mapped
     * @return the list of chance cards map to the Card Entity
     */
    public HashMap<String, List<Card>> CardMapperChanceCards(List<String> lst) {
        List<Card> cards = new ArrayList<>();
        HashMap<String,List<Card>> chanceCards = new HashMap<>();
        for (String s : lst) {
            String[] arr = s.split(":");
            String type = arr[0];
            if (Objects.equals(type, "chance")) {
                String action = arr[1];
                String actiontype = arr[2];
                Integer amount = Integer.parseInt(arr[3]);
                Card card = new Card(type, action, actiontype, amount);
                cards.add(card);
            }
        }
        chanceCards.put("chance", cards);
        return chanceCards;
    }

    /**
     * 
     * @param lst the list of cards before they are mapped
     * @return the list of community chest cards map to the Card Entity
     */
    public HashMap<String, List<Card>> CardMapperComChest(List<String> lst) {
        List<Card> cards = new ArrayList<>();
        HashMap<String,List<Card>> comChestCards = new HashMap<>();
        for (String s : lst) {
            String[] arr = s.split(":");
            String type = arr[0];
            if (Objects.equals(type, "comchest")) {
                String action = arr[1];
                String actiontype = arr[2];
                Integer amount = Integer.parseInt(arr[3]);
                Card card = new Card(type, action, actiontype, amount);
                cards.add(card);
            }
        }
        comChestCards.put("comchest", cards);
        return comChestCards;
    }

}
