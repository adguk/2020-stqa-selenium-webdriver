Feature: Interaction with cart

  Scenario: Adding and removing items to cart
    When we add first product from main page to cart 3 times
    And we navigate to cart and delete all items from it
    Then the cart is empty