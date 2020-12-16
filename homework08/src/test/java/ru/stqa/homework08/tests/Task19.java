package ru.stqa.homework08.tests;

import org.junit.jupiter.api.Test;

public class Task19 extends TestBase {
    @Test
    void items_in_cart() {
        app.addProductsToCart(3);
        app.removeAllProductsFromCart();
    }
}
