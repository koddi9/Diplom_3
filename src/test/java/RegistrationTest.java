import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import page_object.PageMain;
import page_object.PageRegistration;

import java.util.UUID;

public class RegistrationTest {

    WebDriver driver;

    @Before
    public void init() {
        // Запускаем браузер
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        // Переходим на сайт
        driver.get("https://stellarburgers.nomoreparties.site/");
    }

    @Test
    @DisplayName("Регистрация пользователя")
    @Description("Поля валидны, регистрация проходит без ошибок")
    public void registrationPositiveTest() {
        PageMain pageMainObj = new PageMain(driver);
        pageMainObj.toRegistrationPage();

        PageRegistration pageRegistrationObj = new PageRegistration(driver);
        String email = UUID.randomUUID().toString() + "@mail.com";
        User user = new User(email, "123456", "nick");
        boolean result = pageRegistrationObj.registerUser(user);
        Assert.assertTrue(result);
    }

    @Test(expected = org.openqa.selenium.TimeoutException.class)
    @DisplayName("Регистрация пользователя")
    @Description("Поле пароля невалидно, регистрация не проходит")
    public void registrationInvalidPasswordTest() {
        PageMain pageMainObj = new PageMain(driver);
        pageMainObj.toRegistrationPage();

        PageRegistration pageRegistrationObj = new PageRegistration(driver);
        String email = UUID.randomUUID().toString() + "@mail.com";
        String TooShortPass = "123";
        User user = new User(email, TooShortPass, "nick");

        pageRegistrationObj.registerUser(user);
    }
}
