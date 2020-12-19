package ru.stqa.homework09.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class ProductPage extends Page {
    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void selectSizeIfPresent() {
        if (driver.findElements(By.cssSelector("select[name='options[Size]']")).size() > 0) {
            Select size = new Select(driver.findElement(By.cssSelector("select[name='options[Size]']")));
            size.selectByVisibleText("Small");
        }
    }

    public void addProductToCart(int productNumber) {
        WebElement addToCartButton = driver.findElement(By.cssSelector("button[name=add_cart_product]"));
        addToCartButton.click();
        // Wait until counter of items in cart is updated
        wait.until(ExpectedConditions.textToBe(By.cssSelector("div#cart span.quantity"), String.valueOf(productNumber + 1)));
    }
}
