package src.main.java.pageobject;
import src.main.java.api.User;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static src.main.java.config.URL.LOGIN_PAGE_URL;

public class LoginPage {
    private final WebDriver driver;
    private final By loginTitle = By.xpath(".//*[text()='Вход']");
    private final By registerButton = By.xpath(".//a[(@class = 'Auth_link__1fOlj' and text()= 'Зарегистрироваться')]");
    private final By loginButton = By.xpath(".//button[text()='Войти']");
    private final By emailField = By.xpath(".//label[text()='Email']/following-sibling::input");
    private final By passwordField = By.xpath(".//*[text()='Пароль']/following-sibling::input");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открытие страницы авторизации")
    public void open() {
        driver.get(LOGIN_PAGE_URL);
    }

    @Step("Проверка, что отображается надпись «Вход» на страницы авторизации")
    public boolean isLoginTitleDisplayed() {
        return driver.findElement(loginTitle).isDisplayed();
    }

    @Step("Клик по кнопке «Зарегистрироваться»")
    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    @Step("Заполнение полей email и пароль")
    public void enterEmailAndPassword(User user) {
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.urlToBe(LOGIN_PAGE_URL));
        driver.findElement(emailField).click();
        driver.findElement(emailField).sendKeys(user.getEmail());
        driver.findElement(passwordField).click();
        driver.findElement(passwordField).sendKeys(user.getPassword());
    }

    @Step("Клик по кнопке «Войти»")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }
}
