package page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PagePersonalArea {

    private final WebDriver driver;

    private final By createOrderButton = By.xpath("/html/body/div/div/main/section[2]/div/button");
    private final By stellarBurgersLogo = By.xpath("/html/body/div/div/header/nav/div/a");
    private final By constructorButton = By.className("AppHeader_header__linkText__3q_va");
    private final By exitButton = By.xpath("/html/body/div/div/main/div/nav/ul/li[3]/button");
    private final By enterLabel = By.xpath("/html/body/div/div/main/div/h2");

    public PagePersonalArea(WebDriver driver) {
        this.driver = driver;
    }

    public void toMainPageByLogo() {
        driver.findElement(stellarBurgersLogo).click();
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(createOrderButton));
    }

    public void toMainPageByConstructor() {
        driver.findElement(constructorButton).click();
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(createOrderButton));
    }

    public void exitFromAccount() {
        driver.findElement(exitButton).click();
        new WebDriverWait(driver, 2)
                .until(ExpectedConditions.textToBePresentInElementLocated(enterLabel, "Вход"));
    }
}
