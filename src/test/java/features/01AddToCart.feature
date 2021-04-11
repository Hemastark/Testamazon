@Amazon
Feature: Amazon Gift Card purchase
   
   @AddToCart @ViewCart @deleteCart
   Scenario: Amazon Home Page navigation
      Given Navigating to Amazon Home page

   @AddToCart
   Scenario: Adding Gift cards
      When Searching for Gift cards
      Then Adding the Gift cards to Cart
      
   @ViewCart @deleteCart  
   Scenario: View Cart
      When Navigating to Cart
   
   @deleteCart   
   Scenario: Removing Cart Items
      Then Removing the Items from Cart