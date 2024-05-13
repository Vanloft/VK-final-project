package vk.edu.elements;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class HeaderElement {
    private static final SelenideElement TOOLBAR = $x("//div[@class ='toolbar_decor']");
    private final static SelenideElement PROFILE_FUNCTIONS = $x("//button[@aria-label='Настройки профиля']");
    private final static SelenideElement FIRST_LOG_OUT_BUTTON = $x(".//div/a[@data-l='t,logout']");
    private final static SelenideElement SECOND_LOG_OUT_BUTTON = $x("//input[@data-l='t,logout']");
    private static final SelenideElement MESSAGE_BUTTON = $x("//li[@data-l='t,messages']");
    private static final SelenideElement OK_LOGO = $x("//div[@id ='topPanelLeftCorner']");


    public HeaderElement() {
        checkHeaderElement();
    }

    public void goTo (SelenideElement headerButton){
        headerButton.click();
    }

    public void logOut() {
        PROFILE_FUNCTIONS.shouldBe(visible).click();
        FIRST_LOG_OUT_BUTTON.shouldBe(visible).click();
        SECOND_LOG_OUT_BUTTON.shouldBe(visible).click();
    }


    public void checkHeaderElement() {
        TOOLBAR.shouldBe(exist.because("Нет тулбара"));
        MESSAGE_BUTTON.shouldBe(exist.because("Нет кнопки перехода к сообщениям в тулбаре"));
        OK_LOGO.shouldBe(visible.because("Нет лого OK в хедере"));
    }
}