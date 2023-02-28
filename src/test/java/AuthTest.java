import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import page_object.PageAuth;
import page_object.PageMain;
import page_object.PagePasswordRecovery;
import page_object.PageRegistration;

public class AuthTest {

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
    @DisplayName("Авторизация пользователя через кнопку на главной странице")
    @Description("Данные валидны, авторизация проходит успешно")
    public void authMainPageButtonTest() {
        PageMain pageMainObj = new PageMain(driver);
        pageMainObj.toAuthPage();

        PageAuth pageAuthObj = new PageAuth(driver);
        User user = new User("nick111@mail.com", "123456", "nikita");
        boolean result = pageAuthObj.authUser(user);
        Assert.assertTrue(result);
    }

    @Test
    @DisplayName("Авторизация пользователя через кнопку на в личном кабинете")
    @Description("Данные валидны, авторизация проходит успешно")
    public void authPersonalAreaButtonTest() {
        PageMain pageMainObj = new PageMain(driver);
        pageMainObj.toPersonalArea(false);

        PageAuth pageAuthObj = new PageAuth(driver);
        User user = new User("nick111@mail.com", "123456", "nikita");
        boolean result = pageAuthObj.authUser(user);
        Assert.assertTrue(result);
    }

    @Test
    @DisplayName("Авторизация пользователя через кнопку в форме регистрации")
    @Description("Данные валидны, авторизация проходит успешно")
    public void authRegistrationPageTest() {
        PageMain pageMainObj = new PageMain(driver);
        pageMainObj.toRegistrationPage();
        PageRegistration pageRegistrationObj = new PageRegistration(driver);
        pageRegistrationObj.toAuth();
        PageAuth pageAuthObj = new PageAuth(driver);
        User user = new User("nick111@mail.com", "123456", "nikita");
        boolean result = pageAuthObj.authUser(user);
        Assert.assertTrue(result);
    }

    @Test
    @DisplayName("Авторизация пользователя через кнопку в форме восстановления пароля")
    @Description("Данные валидны, авторизация проходит успешно")
    public void authPasswordRecoveryPageTest() {
        PageMain pageMainObj = new PageMain(driver);
        pageMainObj.toAuthPage();
        PageAuth pageAuthObj = new PageAuth(driver);
        pageAuthObj.toPasswordRecovery();
        PagePasswordRecovery pagePasswordRecovery = new PagePasswordRecovery(driver);
        pagePasswordRecovery.toAuth();
        User user = new User("nick111@mail.com", "123456", "nikita");
        boolean result = pageAuthObj.authUser(user);
        Assert.assertTrue(result);
    }
}
