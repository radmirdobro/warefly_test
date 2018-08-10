package ru.innopolis.warefly;

import org.apache.log4j.Logger;
import ru.innopolis.warefly.parsing.WebParser;
import ru.innopolis.warefly.parsing.WebParserImpl;

import java.util.List;

public class Multithreading extends Thread {
    static final Logger loggerConsoleInf = Logger.getLogger("consoleinf");
    static WebParser wp = new WebParserImpl();
    private List<String> shopList;

    Multithreading(List<String> shopList) {
        this.shopList = shopList;
    }

    @Override
    public void run() {

        for (int i = 0; i < shopList.size(); i++) {
            loggerConsoleInf.info("Идём в магазин " + wp.getShopNameFromUrl((String) shopList.get(i)) + ": " + shopList.get(i));

            /*JSON here*/
            loggerConsoleInf.info(wp.infoToJson(wp.getActionsInShop(wp.getShopNameFromUrl((String) shopList.get(i)), 60)));

            try {
                sleep(100);
            } catch (Exception e) {
            }
        }

    }


}
