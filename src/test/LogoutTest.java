package src.test;
import src.main.java.api.User;
import src.main.java.api.UserSteps;
import config.WebDriverFactory;
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
import static config.RandomData.*;
public class LogoutTest {
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
    @DisplayName("Проверка выхода по кнопке «Выйти» в личном кабинете")
    public void checkLogoutSuccess() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmailAndPassword(user);
        loginPage.clickLoginButton();
        mainPage.clickAccountButton();
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.clickLogoutButton();
        Assert.assertTrue(loginPage.isLoginTitleDisplayed());
    }
    @After
    public void close() {
        userSteps.deletingUsersAfterTests(accessToken);
        driver.quit();
    }
}
