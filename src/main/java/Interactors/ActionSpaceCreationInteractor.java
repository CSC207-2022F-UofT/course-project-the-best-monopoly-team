package Interactors;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import Entities.ActionSpace;
import Entities.Card;
import Persistence.LoadAccess;
import UseCases.ActionSpaceCreationUseCase;

/**
 * This class loads all the action cards.
 */
public class ActionSpaceCreationInteractor implements ActionSpaceCreationUseCase {

    private final LoadAccess loadAccess;

    /**
     * Constructor for ActionSpaceCreationInteractor
     * @param loadAccess the CardAccess object
     */
    public ActionSpaceCreationInteractor(LoadAccess loadAccess) {
        this.loadAccess = loadAccess;

    }

    /**
     * This method loads all the jail cards.
     * @return an ActionSpace object containing the jail cards.
     * @throws IOException from CardMapperInteractor
     */
    public ActionSpace loadJailCards() throws IOException {
        CardMapperInteractor cardMapperInteractor = new CardMapperInteractor();
        HashMap<String, List<Card>> cards = cardMapperInteractor.cardMapperJailCards(loadAccess.loadCards());
        return new ActionSpace(cards);

    }

    /**
     * This method loads all the chance cards.
     * @return an ActionSpace object containing the chance cards.
     * @throws IOException from CardMapperInteractor
     */
    public ActionSpace loadChanceCards() throws IOException {
        CardMapperInteractor cardMapperInteractor = new CardMapperInteractor();
        HashMap<String, List<Card>> cards = cardMapperInteractor.cardMapperChanceCards(loadAccess.loadCards());
        return new ActionSpace(cards);
    }

    /**
     * This method loads all the community chest cards.
     * @return an ActionSpace object containing the community chest cards.
     * @throws IOException from CardMapperInteractor
     */
    public ActionSpace loadComChestCards() throws IOException {
        CardMapperInteractor cardMapperInteractor = new CardMapperInteractor();
        HashMap<String, List<Card>> cards = cardMapperInteractor.cardMapperComChest(loadAccess.loadCards());
        return new ActionSpace(cards);
    }
}