import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import page_object.PageAuth;
import page_object.PageMain;
import page_object.PagePersonalArea;

public class TransitionTest {

    WebDriver driver;

    private final By bunText = By.xpath("/html/body/div/div/main/section[1]/div[2]/h2[1]");
    private final By sauceText = By.xpath("/html/body/div/div/main/section[1]/div[2]/h2[2]");
    private final By ingredientsText = By.xpath("/html/body/div/div/main/section[1]/div[2]/h2[3]");

    @Before
    public void init() {
        // Запускаем браузер
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        // Переходим на сайт
        driver.get("https://stellarburgers.nomoreparties.site/");
    }

    @Test
    @DisplayName("Переход в личный кабинет")
    @Description("Пользовател авторизирован, в личном кабинете отображается персональаня информация")
    public void toPersonalAreaTest() {
        PageMain pageMainObj = new PageMain(driver);
        pageMainObj.toAuthPage();
        PageAuth pageAuthObj = new PageAuth(driver);
        User user = new User("nick111@mail.com", "123456", "nikita");
        pageAuthObj.authUser(user);
        pageMainObj.toPersonalArea(true);

        String personalAreaText = driver.findElement(By.className("Account_text__fZAIn")).getText();
        Assert.assertEquals("В этом разделе вы можете изменить свои персональные данные", personalAreaText);
    }

    @Test
    @DisplayName("Переход в конструктор из Личного кабинета через кнопку")
    @Description("Пользовател авторизирован, кнопка для создания заказа отображается")
    public void toMainPageFromPersonalAreaWithConstructorButtonTest() {
        PageMain pageMainObj = new PageMain(driver);
        pageMainObj.toAuthPage();
        PageAuth pageAuthObj = new PageAuth(driver);
        User user = new User("nick111@mail.com", "123456", "nikita");
        pageAuthObj.authUser(user);
        pageMainObj.toPersonalArea(true);

        PagePersonalArea pagePersonalAreaObj = new PagePersonalArea(driver);
        pagePersonalAreaObj.toMainPageByConstructor();

        String mainPageText = driver.findElement(By.className("text_type_main-large")).getText();
        Assert.assertEquals("Соберите бургер", mainPageText);
    }

    @Test
    @DisplayName("Переход в конструктор из Личного кабинета через логотип Stellar Burgers")
    @Description("Пользовател авторизирован, кнопка для создания заказа отображается")
    public void toMainPageFromPersonalAreaWithLogoImageTest() {
        PageMain pageMainObj = new PageMain(driver);
        pageMainObj.toAuthPage();
        PageAuth pageAuthObj = new PageAuth(driver);
        User user = new User("nick111@mail.com", "123456", "nikita");
        pageAuthObj.authUser(user);
        pageMainObj.toPersonalArea(true);

        PagePersonalArea pagePersonalAreaObj = new PagePersonalArea(driver);
        pagePersonalAreaObj.toMainPageByLogo();

        String mainPageText = driver.findElement(By.className("text_type_main-large")).getText();
        Assert.assertEquals("Соберите бургер", mainPageText);
    }

    @Test
    @DisplayName("Переход в раздел 'Булки' наглавной странице")
    @Description("Пользовател авторизирован, скролл бар сдвигается к продуктам 'Булки'")
    public void toBunTabTest() {
        PageMain pageMainObj = new PageMain(driver);
        pageMainObj.toAuthPage();
        PageAuth pageAuthObj = new PageAuth(driver);
        User user = new User("nick111@mail.com", "123456", "nikita");
        pageAuthObj.authUser(user);

        pageMainObj.toBunTab();

        boolean result = driver.findElement(bunText).isDisplayed();
        Assert.assertTrue(result);
    }

    @Test
    @DisplayName("Переход в раздел 'Соусы' наглавной странице")
    @Description("Пользовател авторизирован, скролл бар сдвигается к продуктам 'Соусы'")
    public void toSauceTabTest() {
        PageMain pageMainObj = new PageMain(driver);
        pageMainObj.toAuthPage();
        PageAuth pageAuthObj = new PageAuth(driver);
        User user = new User("nick111@mail.com", "123456", "nikita");
        pageAuthObj.authUser(user);

        pageMainObj.toSauceTab();

        boolean result = driver.findElement(sauceText).isDisplayed();
        Assert.assertTrue(result);
    }

    @Test
    @DisplayName("Переход в раздел 'Начинки' наглавной странице")
    @Description("Пользовател авторизирован, скролл бар сдвигается к продуктам 'Начинки'")
    public void toIngredientsTabTest() {
        PageMain pageMainObj = new PageMain(driver);
        pageMainObj.toAuthPage();
        PageAuth pageAuthObj = new PageAuth(driver);
        User user = new User("nick111@mail.com", "123456", "nikita");
        pageAuthObj.authUser(user);

        pageMainObj.toIngredientsTab();

        boolean result = driver.findElement(ingredientsText).isDisplayed();
        Assert.assertTrue(result);
    }

    @Test
    @DisplayName("Выход по кнопке «Выйти» в личном кабинете")
    @Description("Пользовател авторизирован, выход из личного кабинета на страницу авторизации")
    public void exitButtonTest() {
        PageMain pageMainObj = new PageMain(driver);
        pageMainObj.toAuthPage();
        PageAuth pageAuthObj = new PageAuth(driver);
        User user = new User("nick111@mail.com", "123456", "nikita");
        pageAuthObj.authUser(user);

        pageMainObj.toPersonalArea(true);
        PagePersonalArea pagePersonalAreaObj = new PagePersonalArea(driver);
        pagePersonalAreaObj.exitFromAccount();
        By authPageEmailField = By.xpath("/html/body/div/div/main/div/form/fieldset[1]/div/div/input");
        boolean result = driver.findElement(authPageEmailField).isDisplayed();
        Assert.assertTrue(result);
    }
}
