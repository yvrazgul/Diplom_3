package src.main.java.config;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.concurrent.TimeUnit;

public class WebDriverFactory {
    public static WebDriver get(String browserName) throws RuntimeException {
        switch (browserName) {
            case "chrome":
                ChromeDriver chromeDriver = new ChromeDriver();
                chromeDriver.manage().window().maximize();
                chromeDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                return chromeDriver;
            case "yandex":
                System.setProperty("webdriver.chrome.driver", "src/main/resources/yandexdriver.exe");
                ChromeDriver yandexDriver = new ChromeDriver();
                yandexDriver.manage().window().maximize();
                yandexDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                return yandexDriver;
            default:
                throw new RuntimeException("Браузер не выбран");
        }
    }
}
