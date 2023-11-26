package src.main.java.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static src.main.java.config.URL.BASE_URL;
public class MainPage {
    private final WebDriver driver;

    private final By currentMenu = By.xpath("//div[contains(@class,'tab_tab__1SPyG tab_tab_type_current__2BEPc')]");
    private final By loginButton = By.xpath(".//button[text()='Войти в аккаунт']");
    private final By accountButton = By.xpath(".//p[text()='Личный Кабинет']");
    private final By orderButton = By.xpath(".//button[text()='Оформить заказ']");
    private final By bunsButton = By.xpath(".//span[contains(text(),'Булки')]");
    private final By saucesButton = By.xpath(".//span[contains(text(),'Соусы')]");
    private final By fillingButton = By.xpath(".//span[contains(text(),'Начинки')]");
    private final By assembleBurger = By.xpath(".//*[text()='Соберите бургер']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открыть главную страницу")
    public void open() {//открыть страницу сайта stellar burgers
        driver.get(BASE_URL);
    }

    @Step("Клик по кнопке «Войти в аккаунт»")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Проверка, что отображается надпись «Соберите бургер» на главной странице")
    public boolean isAssembleBurgerDisplayed() {
        return driver.findElement(assembleBurger).isDisplayed();
    }

    @Step("Проверка, что отображается кнопка «Оформить заказ» на главной странице")
    public boolean isOrderButtonDisplayed() {
        return driver.findElement(orderButton).isDisplayed();
    }

    @Step("Клик по кнопке «Личный кабинет»")
    public void clickAccountButton() {
        driver.findElement(accountButton).click();
    }

    @Step("Клик по «Булки»")
    public void clickBunsButton() {
        driver.findElement(bunsButton).click();
    }

    @Step("Клик по «Соусы»")
    public void clickSaucesButton() {
        driver.findElement(saucesButton).click();
    }

    @Step("Клик по «Начинки»")
    public void clickFillingsButton() {
        driver.findElement(fillingButton).click();
    }

    @Step("Проверка текста текущего меню")
    public String getTextFromCurrentMenu() {
        return driver.findElement(currentMenu).getText();
    }
}
