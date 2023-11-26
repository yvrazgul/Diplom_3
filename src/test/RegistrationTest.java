package src.test;

import src.main.java.api.Login;
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
import src.main.java.pageobject.RegistrationPage;

import static config.RandomData.*;
public class RegistrationTest<ValidatableResponse> {
    private WebDriver driver;
    private User user;
    @Before
    public void setUp() {
        String browserName = System.getProperty("browserName");
        driver = WebDriverFactory.get(browserName);
    }
    @Test
    @DisplayName("Проверка регистрации со страницы регистрации")
    public void checkRegistrationFromRegistrationPageSuccess() {
        user = new User(RANDOM_EMAIL, RANDOM_PASSWORD, RANDOM_NAME);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.open();
        registrationPage.registerUser(user);
        registrationPage.clickRegister();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmailAndPassword(user);
        loginPage.clickLoginButton();
        MainPage mainPage = new MainPage(driver);
        Assert.assertTrue(mainPage.isOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Проверка регистриции со страницы авторизации")
    public void checkRegistrationFromLoginPageSuccess() {
        user = new User(RANDOM_EMAIL, RANDOM_PASSWORD, RANDOM_NAME);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.clickRegisterButton();
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.registerUser(user);
        registrationPage.clickRegister();
        loginPage.enterEmailAndPassword(user);
        loginPage.clickLoginButton();
        MainPage mainPage = new MainPage(driver);
        Assert.assertTrue(mainPage.isOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Проверка регистрации с неправильным паролем")
    public void checkRegistrationWithWrongPassError() {
        user = new User(RANDOM_EMAIL, RANDOM_PASSWORD_WRONG, RANDOM_NAME);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.open();
        registrationPage.registerUser(user);
        Assert.assertTrue(registrationPage.isWrongPasswordDisplayed());
    }
    @After
    public void tearDown() {
        UserSteps userSteps = new UserSteps();
        Login credentials = new Login(user.getClass(), user.getClass());
        ValidatableResponse responseLogin = userSteps.login(credentials);
        String accessToken = userSteps.getAccessToken(responseLogin);
        userSteps.deletingUsersAfterTests(accessToken);
        driver.quit();
    }
}
