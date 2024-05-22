package vk.edu.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import vk.edu.page.FriendsPage;

public class FriendsTest extends BaseTest{
  private final String friendName = "technopol63";
  @Test
  public void addToFriendsListTest() {
    loginPage.authenticate();

    FriendsPage friendsPage = new FriendsPage();
    friendsPage.addFriend(friendName);
    Assertions.assertEquals("Запрос отправлен", friendsPage.getResultText().trim(), "Неверный статус");
    friendsPage.cancelFriendRequest();
  }
}
