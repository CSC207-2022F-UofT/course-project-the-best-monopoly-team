package Interactors;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import Entities.ActionSpace;
import Entities.Card;
import Persistence.LoadAccess;
import UseCases.ActionSpaceCreationUseCase;

/**
 * LoadCardsUseCase
 */
public class ActionSpaceCreationInteractor implements ActionSpaceCreationUseCase {

    private final LoadAccess loadAccess;

    /**
     *
     * @param loadAccess the CardAccess object
     */
    public ActionSpaceCreationInteractor(LoadAccess loadAccess) {
        this.loadAccess = loadAccess;

    }

    /**
     * 
     * @return the type of action space (Chance, Jail, Community Chest)
     */
    public ActionSpace loadJailCards() throws IOException {
        CardMapperInteractor cardMapperInteractor = new CardMapperInteractor();
        HashMap<String, List<Card>> cards = cardMapperInteractor.cardMapperJailCards(loadAccess.loadCards());
        return new ActionSpace(cards);

    }

    public ActionSpace loadChanceCards() throws IOException {
        CardMapperInteractor cardMapperInteractor = new CardMapperInteractor();
        HashMap<String, List<Card>> cards = cardMapperInteractor.cardMapperChanceCards(loadAccess.loadCards());
        return new ActionSpace(cards);
    }

    public ActionSpace loadComChestCards() throws IOException {
        CardMapperInteractor cardMapperInteractor = new CardMapperInteractor();
        HashMap<String, List<Card>> cards = cardMapperInteractor.cardMapperComChest(loadAccess.loadCards());
        return new ActionSpace(cards);
    }
}