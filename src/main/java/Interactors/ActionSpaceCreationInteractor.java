package Interactors;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import Entities.ActionSpace2;
import Entities.Card;
import Persistence.CardAccess;
import Persistence.LoadCards;
import UseCases.ActionSpaceCreationUseCase;

/**
 * LoadCardsUseCase
 */
public class ActionSpaceCreationInteractor implements ActionSpaceCreationUseCase {

    private final LoadCards loadCards;
    private final CardAccess cardAccess;

    /**
     * 
     * @param loadCards the LoadCards object
     * @param cardAccess the CardAccess object
     */
    public ActionSpaceCreationInteractor(LoadCards loadCards, CardAccess cardAccess) {
        this.loadCards = loadCards;
        this.cardAccess = cardAccess;

    }

    /**
     * 
     * @return the action space
     */
    public ActionSpace2 loadCards() throws IOException {
        // TODO Auto-generated method stub
        HashMap<String, List<Card>> cards = cardAccess.CardMapperJailCards(loadCards.loadCards());
        return new ActionSpace2(cards);
    }

}