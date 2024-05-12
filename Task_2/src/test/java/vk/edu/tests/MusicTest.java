package vk.edu.tests;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import vk.edu.elements.HeaderElement;
import vk.edu.page.MusicPage;

public class MusicTest extends BaseTest {
    @Test
    public void checkMusic() {
        MusicPage musicPage = new MusicPage();
        musicPage.setPlayButton();
        Assertions.assertAll("Group test play music",
                () -> Assertions.assertTrue(musicPage.checkPauseButton(), "Pause button should be visible on display"),
                () -> Assertions.assertNotEquals(0.0, musicPage.checkTimeForPlay(), "Time can'wt be zero")
        );
    }
}