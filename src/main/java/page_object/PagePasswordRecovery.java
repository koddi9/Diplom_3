package page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PagePasswordRecovery {

    WebDriver driver;

    private final By enterLabel = By.xpath("/html/body/div/div/main/div/h2");
    private final By authButton = By.xpath("/html/body/div/div/main/div/div/p/a");

    public PagePasswordRecovery(WebDriver driver) {
        this.driver = driver;
    }

    public void toAuth() {
        driver.findElement(authButton).click();
        new WebDriverWait(driver, 2)
                .until(ExpectedConditions.textToBePresentInElementLocated(enterLabel, "Вход"));
    }

}
