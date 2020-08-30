package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class PageBase {

    protected WebDriver driver;
    public WebDriverWait wait;

    public int webDriverWaitTimeout = 10;
    public int implicitlyWaitTimeout = 10;
    public int pageLoadTimeout = 10;

    public PageBase(WebDriver driver) {
        this.driver = driver;

        wait = new WebDriverWait(driver, webDriverWaitTimeout);

        driver.manage().timeouts().pageLoadTimeout(pageLoadTimeout, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(implicitlyWaitTimeout, TimeUnit.SECONDS);
    }

    public void waitReadyStateToComplete() {
        wait.until(driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
    }

}
