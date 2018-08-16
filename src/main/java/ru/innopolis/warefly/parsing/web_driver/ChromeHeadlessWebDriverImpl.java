package ru.innopolis.warefly.parsing.web_driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * ChromeHeadlessWebDriverImpl (singleton)
 */

public class ChromeHeadlessWebDriverImpl implements ChromeHeadlessWebDriver {

    private static ChromeHeadlessWebDriver chromeHeadlessWebDriver;

    private ChromeHeadlessWebDriverImpl() {
    }


    public static ChromeHeadlessWebDriver getInstance() {
        if (chromeHeadlessWebDriver == null) {
            chromeHeadlessWebDriver = new ChromeHeadlessWebDriverImpl();
        }
        return chromeHeadlessWebDriver;
    }

    /**
     * getWebDriver method start chromedriver.exe with the options described in it
     *
     * @return webDriver (instance)
     *
     * chrome options here:
     * https://peter.sh/experiments/chromium-command-line-switches/#window-size
     */
    @Override
    public WebDriver getWebDriver() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedrv\\chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        // chromeOptions.setExperimentalOption("useAutomationExtension", false);
        // chromeOptions.setExperimentalOption("excludeSwitches","ignore-certificate-errors");
        //chromeOptions.addArguments("start-maximized");
        //chromeOptions.addArguments("--disable-extensions");
        //chromeOptions.addArguments("--disable-web-security");
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("--window-size=1920,1080");


        //обычный режим
        //return new ChromeDriver();

        //режим без открытия окна браузера
        return new ChromeDriver(chromeOptions);
    }
}

