package page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageMain {

    private final WebDriver driver;

    private final By personalAreaButton = By.xpath("/html/body/div/div/header/nav/a/p");
    private final By authPageEmailField = By.xpath("/html/body/div/div/main/div/form/fieldset[1]/div/div/input");
    private final By saveButton = By.xpath("/html/body/div/div/main/div/div/div/div/button[2]");
    private final By enterAccountButton = By.xpath("/html/body/div/div/main/section[2]/div/button");
    private final By bunText = By.xpath("/html/body/div/div/main/section[1]/div[2]/h2[1]");
    private final By bunTabButton = By.xpath("/html/body/div/div/main/section[1]/div[1]/div[1]");
    private final By sauceText = By.xpath("/html/body/div/div/main/section[1]/div[2]/h2[2]");
    private final By sauceTabButton = By.xpath("/html/body/div/div/main/section[1]/div[1]/div[2]");
    private final By ingredientsText = By.xpath("/html/body/div/div/main/section[1]/div[2]/h2[3]");
    private final By ingredientsTabButton = By.xpath("/html/body/div/div/main/section[1]/div[1]/div[3]");

    public PageMain(WebDriver driver) {
        this.driver = driver;
    }

    public void toPersonalArea(boolean isAuthorized){
        driver.findElement(personalAreaButton).click();
        if (isAuthorized) {
            new WebDriverWait(driver, 3)
                    .until(ExpectedConditions.visibilityOfElementLocated(saveButton));
        } else {
            new WebDriverWait(driver, 3)
                    .until(ExpectedConditions.visibilityOfElementLocated(authPageEmailField));
        }
    }

    public void toRegistrationPage(){
        toAuthPage();
        PageAuth pageAuthObj = new PageAuth(driver);
        pageAuthObj.toRegistration();
    }

    public void toAuthPage() {
        driver.findElement(enterAccountButton).click();
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(authPageEmailField));
    }

    public void toBunTab(){
        WebElement element= driver.findElement(bunTabButton);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click()", element);
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(bunText));
    }

    public void toSauceTab() {
        WebElement element= driver.findElement(sauceTabButton);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click()", element);
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(sauceText));
    }

    public void toIngredientsTab() {
        WebElement element= driver.findElement(ingredientsTabButton);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click()", element);
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(ingredientsText));
    }
}
