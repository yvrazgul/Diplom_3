package src.main.java.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import static src.main.java.config.URL.REGISTER_PAGE_URL;

public class ProfilePage {
    private final WebDriver driver;
    private final By nameField = By.xpath(".//label[text() = 'Имя']/../input[contains(@name, 'name')]");
    private final By emailField = By.xpath(".//label[text() = 'Email']/../input[contains(@name, 'name')]");
    private final By passwordField = By.xpath(".//label[text() = 'Пароль']/../input[contains(@type, 'password')]");
    private final By registerButton = By.xpath("//button[text()='Зарегистрироваться']");
    private final By loginButton = By.xpath(".//a[text()='Войти']");
    private final By wrongPassword = By.xpath(".//*[text()='Некорректный пароль']");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(REGISTER_PAGE_URL);
    }

    @Step("Клик по кнопке «Зарегистрироваться»")
    public void clickRegister() {
        driver.findElement(registerButton).click();
    }

    @Step("Заполнение полей email, пароль, имя")
    public void registerUser(User user) {
        driver.findElement(emailField).click();
        driver.findElement(emailField).sendKeys(user.getEmail());
        driver.findElement(passwordField).click();
        driver.findElement(passwordField).sendKeys(user.getPassword());
        driver.findElement(nameField).click();
        driver.findElement(nameField).sendKeys(user.getName());
    }

    @Step("Проверка наличия сообщения при неправильном пароле")
    public boolean isWrongPasswordDisplayed() {
        return driver.findElement(wrongPassword).isDisplayed();
    }

    @Step("Клик по кнопке «Войти»")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public void clickLogoutButton() {
    }
}