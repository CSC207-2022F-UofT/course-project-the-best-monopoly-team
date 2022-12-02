package Interactors;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import Entities.ActionSpace2;
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
    public ActionSpace2 loadJailCards(File file) throws IOException {
        CardMapperInteractor cardMapperInteractor = new CardMapperInteractor();
        HashMap<String, List<Card>> cards = cardMapperInteractor.cardMapperJailCards(loadAccess.loadCards(file));
        return new ActionSpace2(cards);
    }

    public ActionSpace2 loadChanceCards(File file) throws IOException {
        CardMapperInteractor cardMapperInteractor = new CardMapperInteractor();
        HashMap<String, List<Card>> cards = cardMapperInteractor.cardMapperChanceCards(loadAccess.loadCards(file));
        return new ActionSpace2(cards);
    }

    public ActionSpace2 loadComChestCards(File file) throws IOException {
        CardMapperInteractor cardMapperInteractor = new CardMapperInteractor();
        HashMap<String, List<Card>> cards = cardMapperInteractor.cardMapperComChest(loadAccess.loadCards(file));
        return new ActionSpace2(cards);
    }
}