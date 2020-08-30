package app;

import io.qameta.allure.Step;
import models.PostData;
import models.UserData;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.AdminPage;
import pages.MainPage;

public class Application {

    private final WebDriver driver;

    private final AdminPage adminPage;
    private final MainPage mainPage;

    public Application() {
        String browser = System.getProperty("browser");
        browser = (browser == null || browser.equals("chrome")) ? "chrome" : browser;

        if (browser.equals("firefox")) {
            driver = new FirefoxDriver();
        } else {
            ChromeOptions options = new ChromeOptions();

            options.addArguments("--host-resolver-rules=MAP *.linkedin.com 127.0.0.1");

            options.setExperimentalOption("w3c", false);
            options.setExperimentalOption("useAutomationExtension", false);

            driver = new ChromeDriver(options);
        }

        adminPage = new AdminPage(driver);
        mainPage = new MainPage(driver);
    }

    public void quit() {
        driver.quit();
    }

    @Step("Переход по URL {url}")
    public void openURL(String url) {
        driver.get(url);
        adminPage.waitReadyStateToComplete();
    }

    @Step("Заполнение формы входа данными {user.login} / {user.password} и авторизация")
    public void fillAuthFormAndLogin(UserData user) {
        adminPage.waitReadyStateToComplete();

        adminPage.setValueLoginInput(user.login);
        adminPage.setValuePasswordInput(user.password);

        adminPage.clickAuthSubmit();
        adminPage.waitReadyStateToComplete();
    }

    @Step("Проверка заголовка страницы (ожидаемое значение: {expectedTitle})")
    public void checkPageTitle(String expectedTitle) {
        adminPage.waitReadyStateToComplete();

        Assert.assertTrue("Заголовок страницы некорректный!",
                adminPage.getValuePageTitle().contains(expectedTitle));
    }

    @Step("Добавление новой записи (заголовк: {post.title})")
    public void addNewPost(PostData post) {
        adminPage.waitReadyStateToComplete();

        adminPage.clickAddNewEntryButton();
        adminPage.waitReadyStateToComplete();

        adminPage.setValueTitleInput(post.title);
        adminPage.setValueSlugInput(post.slug);
        adminPage.setValueMarkdownInput(post.markdown);
        adminPage.setValueTextInput(post.text);

        adminPage.clickSaveButton();
        adminPage.waitReadyStateToComplete();
    }

    @Step("Поиск записи с заголовком {post.title} на странице")
    public void findPost(PostData post) {
        mainPage.waitReadyStateToComplete();

        int postsNumber = mainPage.countPostTitles();

        for (int i = 0; i < postsNumber; i++) {
            if (mainPage.getValuePostTitles(i).contains(post.title)) return;
        }

        Assert.fail("Ранее добавленная запись не найдена на главной странице!");
    }

    @Step("Удаление записи с заголовком {post.title}")
    public void removePost(PostData post) {
        adminPage.waitReadyStateToComplete();

        adminPage.clickEntriesLink();
        adminPage.waitReadyStateToComplete();

        int postsNumber = adminPage.countPosts();

        for (int i = 0; i < postsNumber; i++) {
            if (adminPage.getValuePosts(i).contains(post.title)) {
                adminPage.clickPostsCheckbox(i);

                adminPage.setValueActionSelect("delete_selected");
                adminPage.clickActionProceed();
                adminPage.waitReadyStateToComplete();

                adminPage.clickSureButton();
                adminPage.waitReadyStateToComplete();

                return;
            }
        }

        Assert.fail("Не удалось удалить ранее добавленную запись!");
    }

}