package ru.stqa.homework09.tests;

import io.cucumber.junit.platform.engine.Cucumber;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Сделайте спецификацию на языке Gherkin, которая описывает поведение "добавление товаров в корзину и удаление товаров из корзины". Можно описать это в виде одного сценария. Сделайте этот сценарий исполняемым с помощью инструмента Cucumber. Используйте для реализации шагов сценария код, который был создан при разработке теста в задании 19.
 */
@Cucumber
public class Task20 extends CucumberTestBase {

    public Task20() {
        When("we add first product from main page to cart {int} times", (Integer productsNumber) -> {
            app.addProductsToCart(3);
        });
        And("we navigate to cart and delete all items from it", () -> {
            app.removeAllProductsFromCart();
        });
        Then("the cart is empty", () -> {
            assertThat(app.cartIsEmpty()).isTrue();
        });
    }
}
