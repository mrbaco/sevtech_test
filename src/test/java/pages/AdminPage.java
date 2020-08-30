package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class AdminPage extends PageBase {

    @FindBy(css = "input#id_username")
    private WebElement loginInput;

    @FindBy(css = "input#id_password")
    private WebElement passwordInput;

    @FindBy(css = "input[type='submit']")
    private WebElement authSubmit;

    @FindBy(css = "h1.dashboard-title")
    private WebElement pageTitle;

    @FindBy(css = "a[href*='entry/add']")
    private WebElement addNewEntryButton;

    @FindBy(css = "input#id_title")
    private WebElement titleInput;

    @FindBy(css = "input#id_slug")
    private WebElement slugInput;

    @FindBy(css = "textarea#id_text_markdown")
    private WebElement markdownInput;

    @FindBy(css = "textarea#id_text")
    private WebElement textInput;

    @FindBy(css = "input[name='_save']")
    private WebElement saveButton;

    @FindBy(css = "#module_2 a[href='/admin/blog/entry/']")
    private WebElement entriesLink;

    @FindBy(css = "table#result_list tbody tr")
    private List<WebElement> posts;

    @FindBy(css = "select[name='action']")
    private WebElement actionSelect;

    @FindBy(css = "div.actions button[type='submit']")
    private WebElement actionProceed;

    @FindBy(css = "input[type='submit']")
    private WebElement sureButton;

    public AdminPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /** Панель управления. Поле ввода Логин. Установить значение */
    public void setValueLoginInput(String login) {
        loginInput.sendKeys(login);
    }

    /** Панель управления. Поле ввода Пароль. Установить значение */
    public void setValuePasswordInput(String password) {
        passwordInput.sendKeys(password);
    }

    /** Панель управления. Кнопка Войти. Нажать */
    public void clickAuthSubmit() {
        authSubmit.click();
    }

    /** Панель управления. Заголовок. Получить значение */
    public String getValuePageTitle() {
        return pageTitle.getText();
    }

    /** Панель управления. Ссылка Добавить новую запись. Нажать */
    public void clickAddNewEntryButton() {
        addNewEntryButton.click();
    }

    /** Панель управления. Поле ввода Title. Установить значение */
    public void setValueTitleInput(String value) {
        titleInput.sendKeys(value);
    }

    /** Панель управления. Поле ввода Slug. Установить значение */
    public void setValueSlugInput(String value) {
        slugInput.sendKeys(value);
    }

    /** Панель управления. Поле ввода Markdown. Установить значение */
    public void setValueMarkdownInput(String value) {
        markdownInput.sendKeys(value);
    }

    /** Панель управления. Поле ввода Text. Установить значение */
    public void setValueTextInput(String value) {
        textInput.sendKeys(value);
    }

    /** Панель управления. Кнопка Сохранить. Нажать */
    public void clickSaveButton() {
        saveButton.click();
    }

    /** Панель управления. Ссылка перехода ко всем записям. Нажать */
    public void clickEntriesLink() {
        entriesLink.click();
    }

    /** Панель управления. Записи. Посчитать */
    public int countPosts() {
        return posts.size();
    }

    /** Панель управления. Записи. Получить значение */
    public String getValuePosts(int element) {
        return posts.get(element).getText();
    }

    /** Панель управления. Записи. Чекбокс. Нажать */
    public void clickPostsCheckbox(int element) {
        posts.get(element).findElement(By.cssSelector("input.action-select")).click();
    }

    /** Панель управления. Выпадающий список действий над записями. Установить значение */
    public void setValueActionSelect(String action) {
        Select select = new Select(actionSelect);
        select.selectByValue(action);
    }

    /** Панель управления. Кнопка Выполнить. Нажать */
    public void clickActionProceed() {
        actionProceed.click();
    }

    /** Панель управления. Кнопка Да, я уверен. Нажать */
    public void clickSureButton() {
        sureButton.click();
    }

}
