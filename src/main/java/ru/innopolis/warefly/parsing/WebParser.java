package ru.innopolis.warefly.parsing;

import ru.innopolis.warefly.pojo.Shop;

import java.util.List;

public interface WebParser {
    List<String> getShopsList(String location);

    String getShopNameFromUrl(String url);

    Shop getActionsInShop(String shopName, int actionsCount);

    String infoToJson(Shop shopInfo);
}
