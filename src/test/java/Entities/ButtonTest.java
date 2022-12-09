package Entities;

import Buttons.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ButtonTest {
    @Test
    public void buttonOutputTest(){
        ArrayList<ButtonMapping> buttonMappings = new ArrayList<>();
        Button1 b1 = new Button1();
        buttonMappings.add(b1);
        Button2 b2 = new Button2();
        buttonMappings.add(b2);
        Button3 b3 = new Button3();
        buttonMappings.add(b3);
        Button4 b4 = new Button4();
        buttonMappings.add(b4);
        Button5 b5 = new Button5();
        buttonMappings.add(b5);
        Button6 b6 = new Button6();
        buttonMappings.add(b6);
        Button7 b7 = new Button7();
        buttonMappings.add(b7);
        Button8 b8 = new Button8();
        buttonMappings.add(b8);
        Button9 b9 = new Button9();
        buttonMappings.add(b9);
        Button10 b10 = new Button10();
        buttonMappings.add(b10);
        Button11 b11 = new Button11();
        buttonMappings.add(b11);
        Button12 b12 = new Button12();
        buttonMappings.add(b12);
        Button13 b13 = new Button13();
        buttonMappings.add(b13);
        Button14 b14 = new Button14();
        buttonMappings.add(b14);
        Button15 b15 = new Button15();
        buttonMappings.add(b15);
        Button16 b16 = new Button16();
        buttonMappings.add(b16);

        for (int i = 0; i < buttonMappings.size(); i++){
            assertEquals("B" + String.valueOf(i + 1), buttonMappings.get(i).pressedButton());
        }
    }
}
