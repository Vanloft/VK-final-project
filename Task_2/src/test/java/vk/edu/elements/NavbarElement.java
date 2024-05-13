package vk.edu.elements;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class NavbarElement {

    private final static HeaderElement HEADER = new HeaderElement();
    private final static SelenideElement UPLOAD_PHOTO = $x(".//input[@class='html5-upload-link']");
    private final static SelenideElement POST_BUTTON = $x(".//button[@class='dropdown-button__qx55h']");
    private final static SelenideElement NAVBAR_MAIN_PAGE = $x("//nav[@role='navigation']");


    public NavbarElement() {
        checkMainPage();
    }

    public void goTo(SelenideElement el) {
        HEADER.goTo(el);
    }

    public void checkMainPage() {
        UPLOAD_PHOTO.shouldBe(exist.because("Нет блока ссылки на загрузку фото с главной страницы"));
        POST_BUTTON.shouldBe(exist.because("Нет кнопки публикации на главной странице"));
        NAVBAR_MAIN_PAGE.shouldBe(visible.because("Нет навбара на главной странице"));
    }
}