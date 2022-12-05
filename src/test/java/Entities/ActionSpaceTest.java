package Entities;

import Interactors.ActionSpaceCreationInteractor;
import Persistence.LoadAccess;
import Persistence.LoadFile;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class ActionSpaceTest {

    @Test
    void testGetType() throws IOException {
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
        ActionSpace jail = actionSpaceCreationInteractor.loadJailCards();
        Card card = new Card("jail", "Pay child support($100)", "pay", 100);
        String action = card.getAction();
        assert Objects.equals(action, "Pay child support($100)");
    }

}
