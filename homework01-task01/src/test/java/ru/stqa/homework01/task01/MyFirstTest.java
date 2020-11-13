package ru.stqa.homework01.task01;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class MyFirstTest {
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
    void myFirstTest() {
        driver.navigate().to("http://www.google.com");
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("webdriver");
        element.sendKeys(Keys.ENTER);
        wait.until(titleIs("webdriver - Google Search"));
    }
}
