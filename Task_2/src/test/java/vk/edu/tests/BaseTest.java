package vk.edu.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import org.openqa.selenium.chrome.ChromeOptions;
import vk.edu.elements.HeaderElement;
import vk.edu.page.LoginPage;

abstract public class BaseTest {
    private static final String BASE_URL = "https://ok.ru/";
    protected static final LoginPage loginPage = new LoginPage(BASE_URL);

    @BeforeAll
    public static void setUp() {
        try {
            System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--headless");
            chromeOptions.addArguments("--no-sandbox");
            chromeOptions.addArguments("--single-process");
            chromeOptions.addArguments("--disable-dev-shm-usage");
            chromeOptions.addArguments("--remote-debugging-port=9222");
            Configuration.browserCapabilities = chromeOptions;

            Configuration.baseUrl = BASE_URL;
            Configuration.browser = "chrome";
            System.out.println("WebDriver успешно настроен и запущен.");
        } catch (Exception e) {
            System.err.println("Ошибка при настройке WebDriver: " + e.getMessage());
            throw e;
        }
        LoginPage logPage = new LoginPage(BASE_URL);
    }

    @AfterEach
    public void goToLoginPage() {
        if (!loginPage.unsuccessLogin()) {
            HeaderElement header = new HeaderElement();
            header.logOut();
        }
    }

    @AfterAll
    public static void tearDown() {
        Selenide.closeWebDriver();
    }
}
