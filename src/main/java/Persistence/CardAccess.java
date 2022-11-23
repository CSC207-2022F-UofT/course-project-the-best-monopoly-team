package Persistence;

import java.util.HashMap;
import java.util.List;
import Entities.Card;

/**
 * CardAccess interface
 */
public interface CardAccess {
    
    public HashMap<String,List<Card>> CardMapperJailCards(List<String> lst);
    public HashMap<String, List<Card>> CardMapperChanceCards(List<String> lst);
    public HashMap<String, List<Card>> CardMapperComChest(List<String> lst);

}
