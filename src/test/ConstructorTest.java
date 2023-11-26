package src.test;
import src.main.java.config.WebDriverFactory;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import src.main.java.pageobject.MainPage;
public class ConstructorTest {
    private WebDriver driver;
    private MainPage mainPage;
    @Before
    public void setUp() {
        String browserName = System.getProperty("browserName");
        driver = WebDriverFactory.get(browserName);
    }

    @Test
    @DisplayName("Переход к разделу «Булки»")
    public void checkMenuBunIsActiveSuccess() {
        mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickFillingsButton();
        mainPage.clickBunsButton();
        String expected = "Булки";
        Assert.assertEquals(expected, mainPage.getTextFromCurrentMenu());
    }
    @Test
    @DisplayName("Переход к разделу «Соусы»")
    public void checkMenuSaucesIsActiveSuccess() {
        mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickFillingsButton();
        mainPage.clickSaucesButton();
        String expected = "Соусы";
        Assert.assertEquals(expected, mainPage.getTextFromCurrentMenu());
    }
    @Test
    @DisplayName("Переход к разделу «Начинки»")
    public void checkMenuFillingIsActiveSuccess() {
        mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickSaucesButton();
        mainPage.clickFillingsButton();
        String expected = "Начинки";
        Assert.assertEquals(expected, mainPage.getTextFromCurrentMenu());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
