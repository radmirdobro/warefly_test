package ru.innopolis.warefly;

import org.apache.log4j.Logger;
import ru.innopolis.warefly.parsing.WebParser;
import ru.innopolis.warefly.parsing.WebParserImpl;

import java.util.List;

/**
 * Multithreading class
 */
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

            /*JSON here

             навзвание магазина
            {"shopName":"dochkisinochki",
             акция
              "actions":[{
             "badge":"-20%", //скидка
             "dates":"По 19 августа", //дата окончания акции
             "description":"Стиральный порошок для детского белья 1кг, Кислородный…", //описание акции
             "quantity":"", //количество за кг
             "priceNew":"От 149,00 ₽", //новая цена
             "priceOld":"", //старая цена
             "actions":[]}
             ]}
             */

            loggerConsoleInf.info(wp.infoToJson(wp.getActionsInShop(wp.getShopNameFromUrl((String) shopList.get(i)), 60)));

            try {
                sleep(100);
            } catch (Exception e) {
            }
        }

    }


}
