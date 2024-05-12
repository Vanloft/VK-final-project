package vk.edu.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import vk.edu.page.ProfilePage;


public class ProfileTest extends BaseTest {
    @Test
    public void checkProfilePage() {
        ProfilePage profilePage = new ProfilePage();
        Assertions.assertTrue(profilePage.checkUser());
    }
}