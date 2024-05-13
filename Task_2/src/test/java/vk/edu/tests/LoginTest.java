package vk.edu.tests;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import vk.edu.elements.HeaderElement;

public class LoginTest extends BaseTest {
    @ParameterizedTest
    @CsvFileSource(resources = "/logpass.csv", numLinesToSkip = 1)
    public void successfulLogin(String login, String password) {
        loginPage.authenticate(login, password);
        if (loginPage.unsuccessLogin())
            Assertions.assertTrue(loginPage.unsuccessLogin(), "Проверка неверных данных");
        else
            Assertions.assertFalse(loginPage.unsuccessLogin(), "Проверка верных данных");

    }
}