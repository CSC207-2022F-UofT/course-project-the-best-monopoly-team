package Entities;

import Interactors.ActionSpaceCreationInteractor;
import Persistence.LoadAccess;
import Persistence.LoadFile;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class ActionSpaceTest {

    @Test
    void testGetActionType() throws IOException {
        LoadAccess loadAccess = new LoadFile(new File("src/save/cards.txt"));
        ActionSpaceCreationInteractor actionSpaceCreationInteractor = new ActionSpaceCreationInteractor(loadAccess);
        ActionSpace communityChest = actionSpaceCreationInteractor.loadComChestCards();
        ActionSpace chance = actionSpaceCreationInteractor.loadChanceCards();
        ActionSpace jail = actionSpaceCreationInteractor.loadJailCards();
        assert Objects.equals(communityChest.getActionType(), "comchest");
        assert Objects.equals(chance.getActionType(), "chance");
        assert Objects.equals(jail.getActionType(), "jail");
    }

    @Test
    void testGetCard() throws IOException {
        LoadAccess loadAccess = new LoadFile(new File("src/save/cards.txt"));
        ActionSpaceCreationInteractor actionSpaceCreationInteractor = new ActionSpaceCreationInteractor(loadAccess);
        ActionSpace communityChest = actionSpaceCreationInteractor.loadComChestCards();
        List<Card> comChestCards = communityChest.getCards();

        // Checking if all the cards are of the same type
        for (Card card: comChestCards) {
            assert Objects.equals(card.getType(), "comchest");
        }

        assert !comChestCards.isEmpty();
    }

    @Test
    void testGetType() throws IOException {
        LoadAccess loadAccess = new LoadFile(new File("src/save/cards.txt"));
        ActionSpaceCreationInteractor actionSpaceCreationInteractor = new ActionSpaceCreationInteractor(loadAccess);
        ActionSpace communityChest = actionSpaceCreationInteractor.loadComChestCards();
        assert Objects.equals(communityChest.getType(), "Action Space");
    }

}
