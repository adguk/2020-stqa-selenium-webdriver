package ru.stqa.homework07;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Сделайте сценарий, который проверяет, не появляются ли в логе браузера сообщения при открытии страниц в учебном приложении, а именно -- страниц товаров в каталоге в административной панели.
 *
 * Сценарий должен состоять из следующих частей:
 *
 * 1) зайти в админку
 * 2) открыть каталог, категорию, которая содержит товары (страница http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1)
 * 3) последовательно открывать страницы товаров и проверять, не появляются ли в логе браузера сообщения (любого уровня)
 */
public class Task17 {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    void beforeEach() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @AfterEach
    void afterEach() {
        driver.quit();
    }

    @Test
    void browser_logs_in_products() {
        // Login as admin
        driver.navigate().to("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[title=Logout]")));
        // Navigate to products list
        driver.navigate().to("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//tr[.//input[contains(@name, 'products')]]/td[3]/a"), 0));

        // Get list of products
        List<WebElement> products = driver.findElements(By.xpath("//tr[.//input[contains(@name, 'products')]]/td[3]/a"));
        Assertions.assertThat(products).isNotEmpty();
        int productsSize = products.size();
        for (int i = 0; i < productsSize; i++) {
            products = driver.findElements(By.xpath("//tr[.//input[contains(@name, 'products')]]/td[3]/a"));
            products.get(i).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#tab-general")));

            List<LogEntry> logs = driver.manage().logs().get("browser").getAll();
            if (!logs.isEmpty()) {
                System.out.println("Logs were found on page " + driver.getCurrentUrl());
                System.out.println(logs);
            }
            else {
                System.out.println("No logs were found on page " + driver.getCurrentUrl());
            }

            driver.navigate().back();
            wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//tr[.//input[contains(@name, 'products')]]/td[3]/a"), 0));
        }
    }
}
