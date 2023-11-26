package src.test;
import src.main.java.api.User;
import src.main.java.api.UserSteps;
import src.main.java.config.WebDriverFactory;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import src.main.java.pageobject.LoginPage;
import src.main.java.pageobject.MainPage;
import src.main.java.pageobject.ProfilePage;
import static config.RandomData;
public class SwitchTest {
    public WebDriver driver;
    public UserSteps userSteps;
    public String accessToken;
    public User user;
    @Before
    public void setUp() {
        String browserName = System.getProperty("browserName");
        driver = WebDriverFactory.get(browserName);
        user = new User(RANDOM_EMAIL, RANDOM_PASSWORD, RANDOM_NAME);
        userSteps = new UserSteps();
        ValidatableResponse validatableResponse = userSteps.createUser(user);
        accessToken = userSteps.getAccessToken(validatableResponse);
    }
    @Test
    @DisplayName("Проверка перехода по клику на «Личный кабинет»")
    public void checkClickAccountButtonSuccess() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isLoginTitleDisplayed());
    }

    @Test
    @DisplayName("Проверка перехода по клику на «Конструктор» из личного кабинета")
    public void checkClickConstructorButtonSuccess() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmailAndPassword(user);
        loginPage.clickLoginButton();
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.clickLogoutButton();
        Assert.assertTrue(mainPage.isAssembleBurgerDisplayed());
    }

    @Test
    @DisplayName("Проверка перехода по клику на логотип из личного кабинета")
    public void checkClickLogoButtonSuccess() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmailAndPassword(user);
        loginPage.clickLoginButton();
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.clickLoginButton();
        Assert.assertTrue(mainPage.isAssembleBurgerDisplayed());
    }
    @After
    public void close() {
        userSteps.deletingUsersAfterTests(accessToken);
        driver.quit();
    }
}
