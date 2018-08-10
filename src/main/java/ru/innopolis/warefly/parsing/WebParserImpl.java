package ru.innopolis.warefly.parsing;

import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.innopolis.warefly.parsing.web_driver.ChromeHeadlessWebDriver;
import ru.innopolis.warefly.parsing.web_driver.ChromeHeadlessWebDriverImpl;
import ru.innopolis.warefly.pojo.Action;
import ru.innopolis.warefly.pojo.Shop;

import java.util.ArrayList;
import java.util.List;

/**
 * WebParserImpl is used to parsing data from edadeal.ru
 */
public class WebParserImpl implements WebParser {
    static final Logger loggerConsoleInf = Logger.getLogger("consoleinf");
    private static ChromeHeadlessWebDriver chromeHeadlessWebDriver = ChromeHeadlessWebDriverImpl.getInstance();

    /**
     * method getShopsList return the List of retailers from website 'https://edadeal.ru'
     * для получения данных с веб старницы используется библиотека org.openqa.selenium
     *
     * @return collection of shops URL's
     */

    @Override
    public List<String> getShopsList(String location) {
        WebDriver webDriver = chromeHeadlessWebDriver.getWebDriver();
        List<String> links = new ArrayList<>();
        String url = "https://edadeal.ru/" + location + "/retailers";
        webDriver.get(url);
        List<WebElement> refList = ((ChromeDriver) webDriver).findElements(By.className("p-retailers__retailer"));
        if (refList != null) {
            for (WebElement we : refList) {
                links.add(we.getAttribute("href"));
            }
        }
        webDriver.quit();
        return links;
    }

    /**
     * get shopName from URL
     *
     * @param url
     * @return shopName
     */
    @Override
    public String getShopNameFromUrl(String url) {
        int lastPos = url.lastIndexOf('/') + 1;
        return url.substring(lastPos);
    }

    /**
     * get Shop object which contains shopName and ActionsList with each Action parameters
     * Action params: badge, dates, description, quantity, priceNew, priceOld
     */

    @Override
    public Shop getActionsInShop(String shopName, int actionsCount) {
        List<Action> actions = new ArrayList<>();
        if (actionsCount < 30) {
            actionsCount = 30;
        }

        int pagesCounter = 1;
        int pages = actionsCount / 30;
        if (shopName != null) {
            while (pages >= pagesCounter) {
                WebDriver webDriver = chromeHeadlessWebDriver.getWebDriver();

                webDriver.get("https://edadeal.ru/kazan/retailers/" + shopName + "?pages=" + pagesCounter);
                List<WebElement> refList = ((ChromeDriver) webDriver).findElements(By.className("b-offer"));
                for (WebElement we : refList) {

                    String badge = "";
                    try {
                        badge = we.findElement(By.className("b-offer__offer-info")).findElement(By.className("b-offer__badge")).getText();
                    } catch (Exception e) {
                        loggerConsoleInf.info("badge not found");
                    }

                    String dates = "";
                    try {
                        dates = we.findElement(By.className("b-offer__offer-info")).findElement(By.className("b-offer__dates")).getText();
                    } catch (Exception e) {
                        loggerConsoleInf.info("dates not found");
                    }

                    String description = "";
                    try {
                        description = we.findElement(By.className("b-offer__product-info")).findElement(By.className("b-offer__description")).getText();
                    } catch (Exception e) {
                        loggerConsoleInf.info("description not found");
                    }

                    String quantity = "";
                    try {
                        quantity = we.findElement(By.className("b-offer__product-info")).findElement(By.className("b-offer__quantity")).getText();
                    } catch (Exception e) {
                        loggerConsoleInf.info("quantity not found");
                    }

                    String priceNew = "";
                    try {
                        priceNew = we.findElement(By.className("b-offer__footer")).findElement(By.className("b-offer__prices")).findElement(By.className("b-offer__price-new")).getText();
                    } catch (Exception e) {
                        loggerConsoleInf.info(" priceNew not found");
                    }

                    String priceOld = "";
                    try {
                        priceOld = we.findElement(By.className("b-offer__footer")).findElement(By.className("b-offer__prices")).findElement(By.className("b-offer__price-old")).getText();
                    } catch (Exception e) {
                        loggerConsoleInf.info("priceOld not found");
                    }


                    Action action = new Action();
                    action.setBadge(badge);
                    action.setDates(dates);
                    action.setDescription(description);
                    action.setQuantity(quantity);
                    action.setPriceNew(priceNew);
                    action.setPriceOld(priceOld);
                    actions.add(action);

                }
                webDriver.quit();
                pagesCounter++;
            }
        }
        return new Shop(shopName, actions);
    }

    /**
     * converts object to GSON
     *
     * @param shopInfo
     * @return String of gson
     */
    @Override
    public String infoToJson(Shop shopInfo) {
        Gson gson = new Gson();
        return gson.toJson(shopInfo);
    }
}
