package vk.edu.tests;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.logging.LocalLogs;
import vk.edu.elements.HeaderElement;
import vk.edu.page.LoginPage;

public class LogOutTest extends BaseTest{
  @Test
  public void logOutTest(){
    loginPage.authenticate();
    HeaderElement headerElement = new HeaderElement();
    headerElement.logOut();
    loginPage.checkLoginPage();
  }
}
