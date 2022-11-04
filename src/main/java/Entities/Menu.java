package Entities;

import java.lang.reflect.Array;

public class Menu {
    private String[][] options;
    private int menuType;

    public Menu(){
        this.options = new String[][]{{"new game", "load game"}, {"roll dice", "steal", "trade", "manage property"}};
        this.menuType = 0;
    }

    public String[] getOptions() {
        return this.options[this.menuType];
    }

    public void setMenuType(int menuType) {
        this.menuType = menuType;
    }
}
