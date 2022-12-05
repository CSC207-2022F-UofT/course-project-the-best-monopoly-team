package UseCases;

import java.util.HashMap;
import java.util.List;
import Entities.Card;

public interface CardMapperUseCase {
    
    HashMap<String,List<Card>> cardMapperJailCards(List<String> lst);
    HashMap<String, List<Card>> cardMapperChanceCards(List<String> lst);
    HashMap<String, List<Card>> cardMapperComChest(List<String> lst);

}
