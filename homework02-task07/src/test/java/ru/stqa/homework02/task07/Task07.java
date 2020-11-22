package ru.stqa.homework02.task07;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class Task07 {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    void beforeEach() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 60);
    }

    @AfterEach
    void afterEach() {
        driver.quit();
    }

    @Test
    public void admin_should_have_leftSideMenuWithHeadersAfterSuccessfulLogin() throws InterruptedException {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        Thread.sleep(1000);
        int mainMenuSize = driver.findElements(By.cssSelector("li#app-")).size();
        List<WebElement> mainMenuList;
        for (int i = 0; i < mainMenuSize; i++){
            mainMenuList = driver.findElements(By.cssSelector("li#app-"));
            mainMenuList.get(i).click();
            Thread.sleep(200);
            int subMenuSize = driver.findElements(By.cssSelector("[id |= doc]")).size();
            List<WebElement> subMenuList;
            for (int j = 0; j < subMenuSize; j++){
                subMenuList = driver.findElements(By.cssSelector("[id |= doc]"));
                subMenuList.get(j).click();
                Thread.sleep(200);
                isElementPresent(By.cssSelector("h1"));
            }
            isElementPresent(By.cssSelector("h1"));
        }
    }

    private boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        }
        catch (NoSuchElementException e) {
            return false;
        }
    }
}
