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
import src.main.java.pageobject.PasswordRecoveryPage;
import src.main.java.pageobject.RegistrationPage;
import static config.RandomData.*;
public class LoginTest {
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
    @DisplayName("Проверка входа по кнопке «Войти в аккаунт» на главной странице")
    public void checkLoginButtonMainPageSuccess() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmailAndPassword(user);
        loginPage.clickLoginButton();
        Assert.assertTrue(mainPage.isOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Проверка входа через кнопку «Личный кабинет» на главной странице")
    public void checkAccountButtonMainPageSuccess() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmailAndPassword(user);
        loginPage.clickLoginButton();
        Assert.assertTrue(mainPage.isOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Проверка входа через кнопку «Войти» на странице регистрации")
    public void checkLoginButtonRegistrationPageSuccess() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.open();
        registrationPage.registerUser(user);
        registrationPage.clickLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmailAndPassword(user);
        loginPage.clickLoginButton();
        MainPage mainPage = new MainPage(driver);
        Assert.assertTrue(mainPage.isOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Проверка входа через кнопку «Войти» на странице восстановление пароля")
    public void checkLoginButtonPasswordRecoveryPageSuccess() {
        PasswordRecoveryPage passRecoveryPage = new PasswordRecoveryPage(driver);
        passRecoveryPage.open();
        passRecoveryPage.clickLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmailAndPassword(user);
        loginPage.clickLoginButton();
        MainPage mainPage = new MainPage(driver);
        Assert.assertTrue(mainPage.isOrderButtonDisplayed());

    }
    @After
    public void close() {
        userSteps.deletingUsersAfterTests(accessToken);
        driver.quit();
    }
}
