package ru.netology;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AfterCardOrderTest {
    private WebDriver driver;

    @BeforeAll
    static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless"); // Работа без графического интерфейса
        driver = new ChromeDriver(options);
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void shouldSendSuccessfulForm() {
        driver.get("http://localhost:9999");

        // Заполняем имя и фамилию
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Иван Иванов-Петров");

        // Заполняем телефон
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79991234567");

        // Кликаем чекбокс (согласие на обработку данных)
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();

        // Нажимаем кнопку
        driver.findElement(By.cssSelector("button")).click();

        // Проверяем текст сообщения об успехе
        String text = driver.findElement(By.cssSelector("[data-test-id='order-success']")).getText().trim();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text);
    }
}