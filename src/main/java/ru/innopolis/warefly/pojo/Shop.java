package ru.innopolis.warefly.pojo;

import java.util.ArrayList;
import java.util.List;

public class Shop {
    private String shopName;
    private List<Action> actions = new ArrayList<>();

    public Shop() {
    }

    public Shop(String shopName, List<Action> actions) {
        this.shopName = shopName;
        this.actions = actions;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }


    @Override
    public String toString() {
        return shopName + ": [actions=" + actions + "]";
    }
}