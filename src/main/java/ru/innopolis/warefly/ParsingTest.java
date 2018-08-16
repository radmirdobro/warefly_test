package ru.innopolis.warefly;

import org.apache.log4j.Logger;
import ru.innopolis.warefly.parsing.WebParser;
import ru.innopolis.warefly.parsing.WebParserImpl;

import java.util.List;

public class ParsingTest {
    static final Logger loggerConsoleInf = Logger.getLogger("consoleinf");

    public static void main(String[] args) {
        WebParser wp = new WebParserImpl();

        List<String> shopList = wp.getShopsList("kazan");

        /*5ka: 0
        magnit-univer: 1
        rusalka-market: 2
        tkalpari_2: 3
        tatspirtprom: 4
        dobropek: 5
        iola_market: 6
        magnit-cosmo: 7
        globus-market: 8
        bristol: 9
        verno: 10
        krasnoeibeloe: 11
        bahetle: 12
        metropolkzn: 13
        zmk: 14
        perekrestok: 15
        tkvprok: 16
        detmir: 17
        kroha-kazan: 18
        zolotaia_kruzhka: 19
        lenta-giper: 20
        beregitechudo: 21
        magnit-giper: 22
        1b: 23
        auchan: 24
        karusel:25
        dochkisinochki: 26
        myfasol: 27
        metro-cc: 28
        officemag: 29
        vetna:30
        selgros: 31
        spar: 32
        bethowen: 33
        eurospar:34
        galamart: 35
        essen-retail-giper: 36
        winekazan: 37
        edelveis:38
        arysh_mae: 39
        faitro: 40
        akkond: 41
        alko-duty: 42
        petshop_ru: 43
        detskii_magazin: 44
        fabrika_kachestva: 45
        alcostore16: 46
        radugavkusa:47
        posudatsentr: 48
        7-ya: 49
        podrygka: 50
        podguznikoff_astrakhan:51
        maxidom: 52*/

        loggerConsoleInf.info("Запускаем парсинг акций в магазине:");


        int shopNumber = 0;
        loggerConsoleInf.info("Идём в магазин " + wp.getShopNameFromUrl((String) shopList.get(shopNumber)) + ": " + shopList.get(shopNumber));


        /*JSON here*/
        loggerConsoleInf.info(wp.infoToJson(wp.getActionsInShop(wp.getShopNameFromUrl((String) shopList.get(shopNumber)), 15)));

    }
}
