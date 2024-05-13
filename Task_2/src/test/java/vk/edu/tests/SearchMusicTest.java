package vk.edu.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import vk.edu.page.MusicPage;


public class SearchMusicTest extends BaseTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/music.csv")
    public void checkSearchMusic(String nameSong, String artist) {
        loginPage.authenticate();
        MusicPage musicPage = new MusicPage();
        Assertions.assertAll("Check searching music",
                () -> Assertions.assertEquals(artist, musicPage.searchMusic(nameSong, artist), "The artist of the song should be displayed when playing"),
                () -> Assertions.assertTrue(musicPage.checkPauseButton(), "Pause button should be visible on display"),
                () -> Assertions.assertNotEquals(0.0, musicPage.checkTimeForPlay(), "Time can'wt be zero")
        );
    }
}