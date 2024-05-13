package vk.edu.page;

import com.codeborne.selenide.SelenideElement;
import vk.edu.elements.HeaderElement;

import java.util.Objects;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.sleep;

public class MusicPage {
    private static final SelenideElement PLAY_BUTTON = $x("//button[@class='play __active']");
    private static final SelenideElement PAUSE_BUTTON = $x("//button[@class='pause __active']");
    private static final SelenideElement MUSIC_BUTTON = $x("//*[@id='music_toolbar_button']");
    private static final SelenideElement FIELD_SEARCH_MUSIC = $x(".//input[@data-tsid='inner_input']");
    private static final SelenideElement MUSIC_MODAL = $x(".//music-layer[@id='music_layer']");
    private static final SelenideElement NAVIGATION_MUSIC_MENU = $x(".//div[@class='content-container']");
    private static final SelenideElement PLAY_PROGRESS = $x(".//wm-player-duration[@data-l='t,player']");
    private static final SelenideElement SEARCH_FIELD = $x(".//input[@data-tsid='inner_input']");
    private static final SelenideElement FIRST_PLAYBUTTON_ON_A_LIST = $x(".//button[@data-tsid='track_name']");
    private static final SelenideElement CHECK_SPAN_ARTIST = $x(".//span[@class='artist' and @slot='info']");

    public MusicPage() {
        HeaderElement header = new HeaderElement();
        header.goTo(MUSIC_BUTTON);
        checkMusicPage();
    }

    public void setPlayButton() {
        PLAY_BUTTON.click();
    }

    public boolean checkPauseButton(){
        sleep(1000);
        return PAUSE_BUTTON.isDisplayed();
    }

    public double checkTimeForPlay() {
        return Double.parseDouble(Objects.requireNonNull(PLAY_PROGRESS.getAttribute("current-time")));
    }

    public String searchMusic(String nameSong, String artist) {
        SEARCH_FIELD.shouldBe(visible).click();
        SEARCH_FIELD.setValue(nameSong + ' ' + artist);
        SEARCH_FIELD.pressEnter();
        FIRST_PLAYBUTTON_ON_A_LIST.shouldBe(exist).click();
        return CHECK_SPAN_ARTIST.getText();
    }

    public void checkMusicPage() {
        FIELD_SEARCH_MUSIC.shouldBe(visible.because("Отсутствует поле для поиска музыки"));
        MUSIC_MODAL.shouldBe(exist.because("Нет панели управления мультимедией"));
        NAVIGATION_MUSIC_MENU.shouldBe(exist.because("Отсутствует навигационная панель раздела музыки"));
    }
}
