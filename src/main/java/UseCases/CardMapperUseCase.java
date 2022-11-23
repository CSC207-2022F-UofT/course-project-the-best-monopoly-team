package UseCases;

import java.util.HashMap;
import java.util.List;
import Entities.Card;

public interface CardMapperUseCase {
    
    HashMap<String,List<Card>> CardMapperJailCards(List<String> lst);
    HashMap<String, List<Card>> CardMapperChanceCards(List<String> lst);
    HashMap<String, List<Card>> CardMapperComChest(List<String> lst);

}
