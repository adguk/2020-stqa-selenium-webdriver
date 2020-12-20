Feature: Interaction with cart

  Scenario: Adding products to cart
    When we add first product from main page to cart 3 times
    Then the cart has 3 products

  Scenario: Removing products from cart
    Given the cart is not empty
    When we navigate to cart and delete all items from it
    Then the cart is empty