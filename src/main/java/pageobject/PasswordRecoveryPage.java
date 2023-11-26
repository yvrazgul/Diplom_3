package src.main.java.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static src.main.java.config.URL.RECOVERY_PASSWORD_URL;

public class PasswordRecoveryPage {
    private final By loginButton = By.xpath(".//a[text()='Войти']");
    private final WebDriver driver;

    public PasswordRecoveryPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открытие страницы восстановления пароля")
    public void open() {
        driver.get(RECOVERY_PASSWORD_URL);
    }

    @Step("Клик по кнопке «Войти»")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }
}

