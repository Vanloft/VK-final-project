package vk.edu.page;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {
    private static final String BASE_LOGIN = "technopol41";
    private static final String BASE_PASSWORD = "technopolisPassword";
    private static final SelenideElement FIELD_EMAIL = $x(".//input[@name='st.email']");
    private static final SelenideElement FIELD_PASSWD = $x(".//input[@name='st.password']");
    private static final SelenideElement BUTTON_LOGIN = $x(".//input[@value='Войти в Одноклассники']");
    private static final SelenideElement RED_SUBSCRIPT = $x(".//div[@class='input-e login_error']");

    public LoginPage(String url) {
        Selenide.open(url);
        checkLoginPage();
    }

    public LoginPage() {
        checkLoginPage();
        authenticate(BASE_LOGIN, BASE_PASSWORD);
    }

    public boolean unsuccessLogin(){
        return RED_SUBSCRIPT.isDisplayed();
    }

    public void authenticate(String email, String password) {
        FIELD_EMAIL.shouldBe(visible).click();
        FIELD_EMAIL.setValue(email);
        FIELD_PASSWD.shouldBe(visible).click();
        FIELD_PASSWD.setValue(password);
        BUTTON_LOGIN.shouldBe(visible).click();
    }
    
    public void checkLoginPage() {
        FIELD_EMAIL.shouldBe(exist.because("Не найдено поле email"));
        FIELD_PASSWD.shouldBe(exist.because("Не найдено поле password"));
        BUTTON_LOGIN.shouldBe(exist.because("Нет кнопки входа на страничку"));
    }

}