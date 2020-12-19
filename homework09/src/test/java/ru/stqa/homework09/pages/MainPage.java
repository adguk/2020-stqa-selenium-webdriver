package ru.stqa.homework09.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage extends Page {
    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.navigate().to("http://localhost/litecart/en/");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#box-most-popular")));
    }

    public void openProductPageOfFirstProductFromMostPopularSection() {
        driver.findElement(By.cssSelector("div#box-most-popular li:nth-child(1)")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[name=add_cart_product]")));
    }

    public void clickCheckoutLink() {
        driver.findElement(By.cssSelector("div#cart a.link")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#box-checkout-cart")));
        driver.findElement(By.cssSelector("div#box-checkout-cart")).click();
    }
}
