package Entities;

import org.junit.jupiter.api.Test;

import java.util.Objects;

public class CardTest {

    @Test
    void testGetType() {
        Card card = new Card("chance", "Advance", "advance", 0);
        assert Objects.equals(card.getType(), "chance");
    }

    @Test
    void testGetAction() {
        Card card = new Card("chance", "Advance", "advance", 0);
        assert Objects.equals(card.getAction(), "Advance");
    }

    @Test
    void testGetActionType() {
        Card card = new Card("chance", "Advance", "advance", 0);
        assert Objects.equals(card.getActionType(), "advance");
    }

    @Test
    void testGetAmount() {
        Card card = new Card("chance", "Advance", "advance", 0);
        assert card.getAmount() == 0;
    }

}
