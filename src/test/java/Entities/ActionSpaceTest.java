package Entities;

import Interactors.ActionSpaceCreationInteractor;
import Persistence.DataAccess;
import Persistence.TextFileTranslator;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

public class ActionSpaceTest {

    @Test
    void testGetType() throws IOException {
        DataAccess textFileTranslator = new TextFileTranslator(new File(""));
        ActionSpaceCreationInteractor actionSpaceCreationInteractor = new ActionSpaceCreationInteractor(textFileTranslator);
        ActionSpace communityChest = actionSpaceCreationInteractor.loadComChestCards(new File("src/save/cards.txt"));
        ActionSpace chance = actionSpaceCreationInteractor.loadChanceCards(new File("src/save/cards.txt"));
        ActionSpace jail = actionSpaceCreationInteractor.loadJailCards(new File("src/save/cards.txt"));
        assert Objects.equals(communityChest.getActionType(), "comchest");
        assert Objects.equals(chance.getActionType(), "chance");
        assert Objects.equals(jail.getActionType(), "jail");
    }

    @Test
    void testGetCard() throws IOException {
        DataAccess textFileTranslator = new TextFileTranslator(new File(""));
        ActionSpaceCreationInteractor actionSpaceCreationInteractor = new ActionSpaceCreationInteractor(textFileTranslator);
        ActionSpace jail = actionSpaceCreationInteractor.loadJailCards(new File("src/save/cards.txt"));
        Card card = new Card("jail", "Pay child support($100)", "pay", 100);
        String action = card.getAction();
        assert Objects.equals(action, "Pay child support($100)");
    }

}
