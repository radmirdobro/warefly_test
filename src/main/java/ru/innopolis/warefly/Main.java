package ru.innopolis.warefly;

import org.apache.log4j.Logger;
import ru.innopolis.warefly.parsing.WebParser;
import ru.innopolis.warefly.parsing.WebParserImpl;

import java.util.List;

public class Main {
    static final Logger loggerConsoleInf = Logger.getLogger("consoleinf");


    public static void main(String[] args) {
        WebParser wp = new WebParserImpl();
         /*
        Transcriptions  of locations, that are used in the URL
        String locations[] = {'moskva', 'ekaterinburg', 'ufa', 'sevastopol', 'omsk', 'sankt-peterburg',' perm', 'volgograd', 'kazan', 'voronezh'};
        */
        List<String> shopList = wp.getShopsList("kazan");

        loggerConsoleInf.info("Запускаем парсинг акций в магазинах:");
        int countOfThreads = 2;
        int partOfList = shopList.size() / countOfThreads;
        for (int i = 1; i <= countOfThreads; i++) {
            int endOfList = partOfList * i;
            if (i == countOfThreads) {
                endOfList = shopList.size(); //при последнем проходе, не теряем 'хвост' коллекции
            }
            Multithreading thread = new Multithreading(shopList.subList(partOfList * (i - 1), endOfList));
            thread.start();
        }
    }
}
