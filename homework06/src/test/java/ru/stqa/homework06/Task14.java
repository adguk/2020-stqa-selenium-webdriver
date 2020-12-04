package ru.stqa.homework06;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Сделайте сценарий, который проверяет, что ссылки на странице редактирования страны открываются в новом окне.
 *
 * Сценарий должен состоять из следующих частей:
 *
 * 1) зайти в админку
 * 2) открыть пункт меню Countries (или страницу http://localhost/litecart/admin/?app=countries&doc=countries)
 * 3) открыть на редактирование какую-нибудь страну или начать создание новой
 * 4) возле некоторых полей есть ссылки с иконкой в виде квадратика со стрелкой -- они ведут на внешние страницы и открываются в новом окне, именно это и нужно проверить.
 *
 * Конечно, можно просто убедиться в том, что у ссылки есть атрибут target="_blank". Но в этом упражнении требуется именно кликнуть по ссылке, чтобы она открылась в новом окне, потом переключиться в новое окно, закрыть его, вернуться обратно, и повторить эти действия для всех таких ссылок.
 *
 * Не забудьте, что новое окно открывается не мгновенно, поэтому требуется ожидание открытия окна.
 *
 * Можно оформить сценарий либо как тест, либо как отдельный исполняемый файл.
 */
public class Task14 {
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
    void edit_country_links_should_open_new_windows() {
        // Login as admin
        driver.navigate().to("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[title=Logout]")));
        // Navigate to countries list
        driver.navigate().to("http://localhost/litecart/admin/?app=countries&doc=countries");
        wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("i.fa.fa-pencil"), 238));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("i.fa.fa-pencil")));
        // Click edit country
        driver.findElement(By.cssSelector("i.fa.fa-pencil")).click();
        wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("i.fa.fa-external-link"), 7));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("i.fa.fa-external-link")));

        // Save current window
        String mainWindowHandle = driver.getWindowHandle();
        List<WebElement> externalLinks = driver.findElements(By.cssSelector("i.fa.fa-external-link"));
        assertThat(externalLinks).isNotEmpty().hasSize(7);
        for (int i = 0; i < externalLinks.size(); i++) {
            externalLinks.get(i).click();
            wait.until(ExpectedConditions.numberOfWindowsToBe(2));

            Set<String> windowHandles = driver.getWindowHandles();
            assertThat(windowHandles).isNotEmpty().hasSize(2);
            windowHandles.remove(mainWindowHandle);
            Iterator<String> iterator = windowHandles.iterator();
            String newWindowHandle = iterator.hasNext() ? iterator.next() : null;
            assertThat(newWindowHandle).isNotNull();

            driver.switchTo().window(newWindowHandle);
            driver.close();
            driver.switchTo().window(mainWindowHandle);
        }
    }
}
