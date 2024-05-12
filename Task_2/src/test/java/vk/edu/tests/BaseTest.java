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
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            options.addArguments("--disable-gpu");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-setuid-sandbox");
            options.addArguments("--remote-debugging-port=9222");

            Configuration.baseUrl = BASE_URL;
            Configuration.browser = "chrome";
            Configuration.browserCapabilities.setCapability(ChromeOptions.CAPABILITY, options);

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
