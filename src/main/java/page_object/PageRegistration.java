package page_object;

import model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageRegistration {

    private final WebDriver driver;

    private final By nameField = By.xpath("/html/body/div/div/main/div/form/fieldset[1]/div/div/input");
    private final By emailField = By.xpath("/html/body/div/div/main/div/form/fieldset[2]/div/div/input");
    private final By passwordField = By.xpath("/html/body/div/div/main/div/form/fieldset[3]/div/div/input");
    private final By registerButton = By.xpath("/html/body/div/div/main/div/form/button");
    private final By authButton = By.xpath("/html/body/div/div/main/div/div/p/a");
    private final By authPageEmailField = By.xpath("/html/body/div/div/main/div/form/fieldset[1]/div/div/input");
    private final By enterLabel = By.xpath("/html/body/div/div/main/div/h2");


    public PageRegistration(WebDriver driver) {
        this.driver = driver;
    }

    public boolean registerUser(User user) {
        setUserDataRegisterPageField(user);

        driver.findElement(registerButton).click();
        new WebDriverWait(driver, 2)
                .until(ExpectedConditions.textToBePresentInElementLocated(enterLabel, "Вход"));

        return registrationPageClosed();
    }

    public void toAuth() {
        driver.findElement(authButton).click();
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(authPageEmailField));
    }

    private void setUserDataRegisterPageField(User user) {
        driver.findElement(nameField).sendKeys(user.getName());
        driver.findElement(emailField).sendKeys(user.getEmail());
        driver.findElement(passwordField).sendKeys(user.getPassword());
    }

    private boolean registrationPageClosed() {
        return driver.findElement(enterLabel).isDisplayed();
    }
}
