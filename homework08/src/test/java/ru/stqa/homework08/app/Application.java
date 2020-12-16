package ru.stqa.homework08.app;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.stqa.homework08.pages.CheckoutPage;
import ru.stqa.homework08.pages.MainPage;
import ru.stqa.homework08.pages.ProductPage;

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
        for (int i = 0; i < productsNumber; i++) {
            mainPage.open();
            mainPage.openProductPageOfFirstProductFromMostPopularSection();
            productPage.selectSizeIfPresent();
            productPage.addProductToCart(i);
        }
    }

    public void removeAllProductsFromCart() {
        mainPage.clickCheckoutLink();
        checkoutPage.removeAllProductsFromCart();
    }
}
