package Interactors;

import java.io.File;
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
     * @return the action space
     */
    public ActionSpace loadJailCards(File file) throws IOException {
        CardMapperInteractor cardMapperInteractor = new CardMapperInteractor();
        HashMap<String, List<Card>> cards = cardMapperInteractor.cardMapperJailCards(dataAccess.loadCards(file));
        return new ActionSpace(cards);

    }

    public ActionSpace loadChanceCards(File file) throws IOException {
        CardMapperInteractor cardMapperInteractor = new CardMapperInteractor();
        HashMap<String, List<Card>> cards = cardMapperInteractor.cardMapperChanceCards(dataAccess.loadCards(file));
        return new ActionSpace(cards);
    }

    public ActionSpace loadComChestCards(File file) throws IOException {
        CardMapperInteractor cardMapperInteractor = new CardMapperInteractor();
        HashMap<String, List<Card>> cards = cardMapperInteractor.cardMapperComChest(dataAccess.loadCards(file));
        return new ActionSpace(cards);
    }
}