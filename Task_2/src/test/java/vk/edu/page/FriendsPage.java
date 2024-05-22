package vk.edu.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import vk.edu.elements.NavbarElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;


public class FriendsPage {
  private static final SelenideElement USERS_FRIENDS_PAGE = $x(".//a[@data-l='t,userFriend']");
  private static final SelenideElement FRIENDS_MENU = $x(".//nav[@aria-label='Меню в средней колонке']");
  private static final SelenideElement SCHOOL_FRIENDS = $x(".//a[@aria-label='Школьные друзья']");
  private static final SelenideElement VK_FRIENDS = $x(".//a[@aria-label='Импорт из ВКонтакте']");
  private static final SelenideElement SEARCH_BAR = $x(".//input[@type = 'text']");
  private static final SelenideElement USER_NAME = $x(".//a[contains(@data-l, 'USER_CARD')]");
  private static final SelenideElement ADD_TO_FRIENDS_BUTTON = $x(".//a[@aria-label='Добавить в друзья']");
  private static final SelenideElement REQUEST_SENT_BUTTON = $x(".//button[@aria-label='Запрос отправлен']");
  private static final SelenideElement CANCEL_REQUEST_BUTTON = $x(".//a[@aria-label='Отменить запрос']");

  public FriendsPage() {
    NavbarElement navbar = new NavbarElement();
    navbar.goTo(USERS_FRIENDS_PAGE);
    checkPage();
  }

  public void checkPage() {
    $(FRIENDS_MENU).shouldBe(visible);
    $(SCHOOL_FRIENDS).shouldBe(visible);
    $(VK_FRIENDS).shouldBe(visible);
  }

  public void addFriend(String friendName) {
    $(SEARCH_BAR)
            .setValue(friendName)
            .shouldHave(Condition.value(friendName))
            .pressEnter();
    $(USER_NAME)
            .shouldBe(visible)
            .click();
    $(ADD_TO_FRIENDS_BUTTON)
            .shouldBe(visible)
            .click();
  }

  public void cancelFriendRequest() {
    $(REQUEST_SENT_BUTTON)
            .shouldBe(visible)
            .click();
    $(CANCEL_REQUEST_BUTTON)
            .shouldBe(visible)
            .click();
  }

  public String getResultText() {
    return $(REQUEST_SENT_BUTTON)
            .shouldBe(visible)
            .innerText();
  }
}
