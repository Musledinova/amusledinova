package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class Hooks {

    final static String URL = "https://market.yandex.ru/";

    @Before
    public void openURL() {
        open(URL);
    }

    @After
    public void killDriver() {
        closeWebDriver();
    }

}
