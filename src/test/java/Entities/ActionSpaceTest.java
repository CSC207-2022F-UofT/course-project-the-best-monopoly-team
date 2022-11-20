package Entities;

import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

public class ActionSpaceTest {

    @Test
    void testGetType() throws IOException {
        ActionSpace actionSpace1 = new ActionSpace("Jail");
        ActionSpace actionSpace2 = new ActionSpace("comchest");
        ActionSpace actionSpace3 = new ActionSpace("chance");
        assert Objects.equals(actionSpace1.getType(), "Jail");
        assert Objects.equals(actionSpace2.getType(), "comchest");
        assert Objects.equals(actionSpace3.getType(), "chance");
    }

    @Test
    void testGetCard() throws IOException {
        ActionSpace actionSpace = new ActionSpace("Jail");
        assert actionSpace.getJailCards().containsKey("Pay child support($100)");
//        assert actionSpace.getJailCards().containsValue(actionSpace.getCard("Pay child support($100)"));
    }

    @Test
    void testLoadFile() throws IOException {
        ActionSpace actionSpace = new ActionSpace("jail");

        HashMap<String, ArrayList<Object>> actual = new HashMap<>();
        ArrayList<Object> objectList1 = new ArrayList<>();
        ArrayList<Object> objectList2 = new ArrayList<>();
        ArrayList<Object> objectList3 = new ArrayList<>();
        ArrayList<Object> objectList4 = new ArrayList<>();
        ArrayList<Object> objectList5 = new ArrayList<>();
        objectList1.add(Arrays.asList("pay", 50));
        objectList2.add(Arrays.asList("pay", 20));
        objectList3.add(Arrays.asList("pay", 100));
        objectList4.add(Arrays.asList("pay", 100));
        objectList5.add(Arrays.asList("pay", 25));
        actual.put("Pay child support($100)", objectList1);
        actual.put("Pay the guards($20)", objectList2);
        actual.put("Pay the warden($50)", objectList3);
        actual.put("Pay for your mom's living expenses($100)", objectList4);
        actual.put("Pay for extra benefits in jail($25)", objectList5);
        assert actionSpace.getJailCards().keySet().equals(actual.keySet());
    }

}
