package ru.stqa.homework05;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Сделайте сценарий для добавления товаров в корзину и удаления товаров из корзины.
 *
 * 1) открыть главную страницу
 * 2) открыть первый товар из списка
 * 2) добавить его в корзину (при этом может случайно добавиться товар, который там уже есть, ничего страшного)
 * 3) подождать, пока счётчик товаров в корзине обновится
 * 4) вернуться на главную страницу, повторить предыдущие шаги ещё два раза, чтобы в общей сложности в корзине было 3 единицы товара
 * 5) открыть корзину (в правом верхнем углу кликнуть по ссылке Checkout)
 * 6) удалить все товары из корзины один за другим, после каждого удаления подождать, пока внизу обновится таблица
 */
public class Task13 {
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
    void items_in_cart() throws InterruptedException {
        // Navigate to main page
        driver.navigate().to("http://localhost/litecart/en/");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#box-most-popular")));

        // Add first item from Most Popular section 3 times
        for (int i = 0; i < 3; i++) {
            driver.findElement(By.cssSelector("div#box-most-popular li:nth-child(1)")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[name=add_cart_product]")));
            if (driver.findElements(By.cssSelector("select[name='options[Size]']")).size() > 0) {
                Select size = new Select(driver.findElement(By.cssSelector("select[name='options[Size]']")));
                size.selectByVisibleText("Small");
            }
            WebElement addToCartButton = driver.findElement(By.cssSelector("button[name=add_cart_product]"));
            addToCartButton.click();
            // Wait until counter of items in cart is updated
            wait.until(ExpectedConditions.textToBe(By.cssSelector("div#cart span.quantity"), String.valueOf(i + 1)));
            // Navigate back
            driver.navigate().back();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#box-most-popular")));
        }

        // Click Checkout and remove all items from cart
        driver.findElement(By.cssSelector("div#cart a.link")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#box-checkout-cart")));
        int numberOfItemsInCart = driver.findElements(By.cssSelector("div#box-checkout-cart li.shortcut")).size();
        for (int i = 0; i < numberOfItemsInCart; i++) {
            Thread.sleep(500);
            List<WebElement> tableItems = driver.findElements(By.cssSelector("div#order_confirmation-wrapper td.item"));
            if (driver.findElements(By.cssSelector("div#box-checkout-cart li.shortcut")).size() > 0) {
                List<WebElement> shortcuts = driver.findElements(By.cssSelector("div#box-checkout-cart li.shortcut > a"));
                shortcuts.get(0).click();
            }
            List<WebElement> removeButtons = driver.findElements(By.cssSelector("button[name=remove_cart_item]"));
            removeButtons.get(0).click();
            wait.until(ExpectedConditions.stalenessOf(tableItems.get(0)));
        }
    }
}
