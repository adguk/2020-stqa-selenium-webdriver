package ru.stqa.homework09.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CheckoutPage extends Page {
    @FindBy(css = "div#box-checkout-cart")
    private WebElement checkoutProductArea;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void removeAllProductsFromCart() {
        wait.until(ExpectedConditions.visibilityOf(checkoutProductArea));
        checkoutProductArea.click();
        int numberOfItemsInCart = driver.findElements(By.cssSelector("div#box-checkout-cart li.shortcut")).size();
        for (int i = 0; i < numberOfItemsInCart; i++) {
            try { Thread.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }
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

    public boolean cartIsEmpty() {
        WebElement cartIsEmptyElement = driver.findElement(By.cssSelector("div#checkout-cart-wrapper > p"));
        String cartIsEmptyText = cartIsEmptyElement.getAttribute("textContent");
        return cartIsEmptyText.equals("There are no items in your cart.");
    }
}
