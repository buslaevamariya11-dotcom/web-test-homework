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
        options.addArguments("--disable-dev-shm-usage", "--no-sandbox", "--headless");
        driver = new ChromeDriver(options);

        driver.get("http://localhost:9999");
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void shouldSendSuccessfulForm() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Иван Иванов");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79991234567");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();

        String text = driver.findElement(By.cssSelector("[data-test-id='order-success']")).getText().trim();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text);
    }

    @Test
    void shouldShowErrorWhenPhoneIsInvalid() {

        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Иван Иванов");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("7999123"); // Номер без плюса
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.className("button")).click();

        String expected = "Телефон указан неверно. Должно быть 11 цифр, начиная с +7.";
        String actual = driver.findElement(By.cssSelector("[data-test-id='phone'].input_invalid .input__sub")).getText().trim();
        assertEquals(expected, actual);
    }
}