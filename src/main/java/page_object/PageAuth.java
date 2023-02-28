package page_object;

import model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageAuth {

    WebDriver driver;

    private final By registrationButton = By.xpath("/html/body/div/div/main/div/div/p[1]/a");
    private final By authButton = By.xpath("/html/body/div/div/main/div/form/button");
    private final By registrationEmailField = By.xpath("/html/body/div/div/main/div/form/fieldset[2]/div/div/input");
    private final By emailField = By.xpath("/html/body/div/div/main/div/form/fieldset[1]/div/div/input");
    private final By passwordField = By.xpath("/html/body/div/div/main/div/form/fieldset[2]/div/div/input");
    private final By createOrderButton = By.xpath("/html/body/div/div/main/section[2]/div/button");
    private final By recoveryPasswordButton = By.xpath("/html/body/div/div/main/div/div/p[2]/a");
    private final By enterLabel = By.xpath("/html/body/div/div/main/div/h2");

    public PageAuth(WebDriver driver) {
        this.driver = driver;
    }

    public void toRegistration() {
        driver.findElement(registrationButton).click();
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(registrationEmailField));
    }

    public boolean authUser(User user) {
        setUserDataAuthPageField(user);
        driver.findElement(authButton).click();
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(createOrderButton));
        return driver.findElement(createOrderButton).isDisplayed();
    }

    public void toPasswordRecovery() {
        driver.findElement(recoveryPasswordButton).click();
        new WebDriverWait(driver, 2)
                .until(ExpectedConditions.textToBePresentInElementLocated(enterLabel, "Восстановление пароля"));
    }

    private void setUserDataAuthPageField(User user) {
//        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.findElement(emailField).sendKeys(user.getEmail());
        driver.findElement(passwordField).sendKeys(user.getPassword());
    }
}
