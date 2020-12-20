package ru.stqa.homework09.app;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.stqa.homework09.pages.CheckoutPage;
import ru.stqa.homework09.pages.MainPage;
import ru.stqa.homework09.pages.ProductPage;

public class Application {
    private WebDriver driver;

    private MainPage mainPage;
    private ProductPage productPage;
    private CheckoutPage checkoutPage;

    public Application() {
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        productPage = new ProductPage(driver);
        checkoutPage = new CheckoutPage(driver);
    }

    public void quit() {
        driver.quit();
    }

    public void addProductsToCart(int productsNumber) {
        mainPage.open();
        for (int i = 0; i < productsNumber; i++) {
            mainPage.openProductPageOfFirstProductFromMostPopularSection();
            productPage.selectSizeIfPresent();
            productPage.addProductToCart(i);
            mainPage.open();
        }
    }

    public void removeAllProductsFromCart() {
        mainPage.clickCheckoutLink();
        checkoutPage.removeAllProductsFromCart();
    }

    public boolean cartIsEmpty() {
        return checkoutPage.cartIsEmpty();
    }

    public int getNumberOfProductsInCart() {
        return mainPage.getNumberOfProductsInCart();
    }
}
