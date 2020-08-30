package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MainPage extends PageBase {

    @FindBy(css = "h2 a.entry_title")
    private List<WebElement> postTitles;

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /** Главная страница. Заголовок записи. Посчитать */
    public int countPostTitles() {
        return postTitles.size();
    }

    /** Главная страница. Заголовок записи. Получить текст */
    public String getValuePostTitles(int element) {
        return postTitles.get(element).getText();
    }

}
