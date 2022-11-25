package Interactors;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import Entities.ActionSpace2;
import Entities.Card;
import Persistence.DataAccess;
import UseCases.ActionSpaceCreationUseCase;

/**
 * LoadCardsUseCase
 */
public class ActionSpaceCreationInteractor implements ActionSpaceCreationUseCase {

    private final DataAccess dataAccess;

    /**
     *
     * @param dataAccess the CardAccess object
     */
    public ActionSpaceCreationInteractor(DataAccess dataAccess) {
        this.dataAccess = dataAccess;

    }

    /**
     * 
     * @return the action space
     */
    public ActionSpace2 loadJailCards(File file) throws IOException {
        CardMapperInteractor cardMapperInteractor = new CardMapperInteractor();
        HashMap<String, List<Card>> cards = cardMapperInteractor.cardMapperJailCards(dataAccess.loadCards(file));
        return new ActionSpace2(cards);
    }

    public ActionSpace2 loadChanceCards(File file) throws IOException {
        CardMapperInteractor cardMapperInteractor = new CardMapperInteractor();
        HashMap<String, List<Card>> cards = cardMapperInteractor.cardMapperChanceCards(dataAccess.loadCards(file));
        return new ActionSpace2(cards);
    }

    public ActionSpace2 loadComChestCards(File file) throws IOException {
        CardMapperInteractor cardMapperInteractor = new CardMapperInteractor();
        HashMap<String, List<Card>> cards = cardMapperInteractor.cardMapperComChest(dataAccess.loadCards(file));
        return new ActionSpace2(cards);
    }
}